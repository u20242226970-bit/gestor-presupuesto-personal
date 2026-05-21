// URL base de la API
const API = 'http://localhost:8080';

// ===== AUTENTICACION (JWT) =====

/**
 * Devuelve el header de autorizacion con el token JWT.
 */
function getAuth() {
    return 'Bearer ' + localStorage.getItem('token');
}

function getUsuarioId() {
    return localStorage.getItem('usuarioId');
}

/**
 * Verifica que exista un token. Si no, redirige al login.
 */
function verificarSesion() {
    if (!localStorage.getItem('token')) {
        window.location.href = 'index.html';
    }
}

function cerrarSesion() {
    localStorage.clear();
    window.location.href = 'index.html';
}

// ===== HELPER AJAX CON JQUERY =====

/**
 * Realiza una peticion AJAX al backend usando jQuery ($.ajax).
 * Agrega automaticamente el token JWT en el header Authorization.
 * Devuelve una promesa que se resuelve con los datos ya parseados.
 *
 * @param metodo el metodo HTTP (GET, POST, PUT, DELETE)
 * @param endpoint la ruta relativa de la API (ej: /api/gastos)
 * @param datos objeto opcional a enviar en el cuerpo de la peticion
 * @return promesa de jQuery con la respuesta del servidor
 */
function apiAjax(metodo, endpoint, datos) {
    return $.ajax({
        url: API + endpoint,
        method: metodo,
        contentType: 'application/json',
        headers: { 'Authorization': getAuth() },
        data: datos ? JSON.stringify(datos) : undefined
    });
}

// ===== LOGIN (JWT con jQuery) =====

function login() {
    const username = $('#username').val();
    const password = $('#password').val();

    if (!username || !password) {
        mostrarError('alertaError', 'Completa todos los campos');
        return;
    }

    $.ajax({
        url: `${API}/api/auth/login`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ username, password }),
        success: function (data) {
            // Guardamos el token y los datos del usuario (NUNCA la contrasena)
            localStorage.setItem('token', data.token);
            localStorage.setItem('usuarioId', data.idUsuario);
            localStorage.setItem('usuarioNombre', data.nombre);
            localStorage.setItem('usuarioRol', data.rol);
            window.location.href = 'dashboard.html';
        },
        error: function () {
            mostrarError('alertaError', 'Usuario o contrasena incorrectos');
        }
    });
}

// ===== REGISTRO (JWT con jQuery) =====

function registrar() {
    const nombre = $('#nombre').val();
    const username = $('#username').val();
    const password = $('#password').val();
    const ingresoMensual = $('#ingresoMensual').val();

    if (porcentajeElegido === 0) {
        mostrarError('alertaError', 'Selecciona tu porcentaje de ahorro actual');
        $('#alertaError').removeClass('d-none');
        return;
    }

    const ahorroMensual = (parseFloat(ingresoMensual) * porcentajeElegido) / 100;

    $.ajax({
        url: `${API}/api/auth/registro`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nombre,
            username,
            password,
            ingresoMensual: parseFloat(ingresoMensual),
            ahorroMensual,
            porcentajeAhorroInicial: porcentajeElegido,
            rol: 'USER'
        }),
        success: function (data) {
            // El registro devuelve un token: logueamos automaticamente
            localStorage.setItem('token', data.token);
            localStorage.setItem('usuarioId', data.idUsuario);
            localStorage.setItem('usuarioNombre', data.nombre);
            localStorage.setItem('usuarioRol', data.rol);

            $('#alertaExito').removeClass('d-none').text('Cuenta creada exitosamente! Redirigiendo...');
            setTimeout(() => window.location.href = 'dashboard.html', 1500);
        },
        error: function () {
            mostrarError('alertaError', 'Error al crear la cuenta');
        }
    });
}

// ===== SISTEMA DE RANGOS =====

function calcularRango(ingresoMensual, ahorroMensual) {
    if (!ingresoMensual || ingresoMensual === 0) return getRangoInfo('piedra');
    const porcentaje = (ahorroMensual / ingresoMensual) * 100;

    if (porcentaje >= 45) return getRangoInfo('elite');
    if (porcentaje >= 30) return getRangoInfo('diamante');
    if (porcentaje >= 20) return getRangoInfo('oro');
    if (porcentaje >= 10) return getRangoInfo('plata');
    if (porcentaje >= 5)  return getRangoInfo('bronce');
    return getRangoInfo('piedra');
}

function getRangoInfo(rango) {
    const rangos = {
        piedra:   { nombre: 'PIEDRA',   emoji: '🪨', clase: 'rank-piedra',   min: 0,  max: 5,  siguiente: 'Bronce' },
        bronce:   { nombre: 'BRONCE',   emoji: '🥉', clase: 'rank-bronce',   min: 5,  max: 10, siguiente: 'Plata' },
        plata:    { nombre: 'PLATA',    emoji: '🥈', clase: 'rank-plata',    min: 10, max: 20, siguiente: 'Oro' },
        oro:      { nombre: 'ORO',      emoji: '🥇', clase: 'rank-oro',      min: 20, max: 30, siguiente: 'Diamante' },
        diamante: { nombre: 'DIAMANTE', emoji: '💎', clase: 'rank-diamante', min: 30, max: 45, siguiente: 'Elite' },
        elite:    { nombre: 'ELITE',    emoji: '👑', clase: 'rank-elite',    min: 45, max: 100, siguiente: null }
    };
    return rangos[rango];
}

function formatearMoney(valor) {
    return new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP',
        minimumFractionDigits: 0
    }).format(valor);
}

// ===== UTILIDADES (con jQuery) =====

function mostrarError(id, mensaje) {
    $('#' + id).removeClass('d-none').text(mensaje);
}

function ocultarError(id) {
    $('#' + id).addClass('d-none');
}