// URL base de la API
const API = 'http://localhost:8080';

// ===== AUTENTICACION =====

function getAuth() {
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');
    return 'Basic ' + btoa(username + ':' + password);
}

function getUsuarioId() {
    return localStorage.getItem('usuarioId');
}

function verificarSesion() {
    if (!localStorage.getItem('username')) {
        window.location.href = 'index.html';
    }
}

function cerrarSesion() {
    localStorage.clear();
    window.location.href = 'index.html';
}

// ===== LOGIN =====

async function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        mostrarError('alertaError', 'Completa todos los campos');
        return;
    }

    try {
        const response = await fetch(`${API}/api/usuarios/me`, {
            headers: {
                'Authorization': 'Basic ' + btoa(username + ':' + password)
            }
        });

        if (response.ok) {
            const usuario = await response.json();
            localStorage.setItem('username', username);
            localStorage.setItem('password', password);
            localStorage.setItem('usuarioId', usuario.id);
            localStorage.setItem('usuarioNombre', usuario.nombre);
            localStorage.setItem('usuarioRol', usuario.rol);
            window.location.href = 'dashboard.html';
        } else {
            mostrarError('alertaError', 'Usuario o contrasena incorrectos');
        }
    } catch (error) {
        mostrarError('alertaError', 'Error de conexion con el servidor');
    }
}

// ===== REGISTRO =====

async function registrar() {
    const nombre = document.getElementById('nombre').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const ingresoMensual = document.getElementById('ingresoMensual').value;

    if (porcentajeElegido === 0) {
        mostrarError('alertaError', 'Selecciona tu porcentaje de ahorro actual');
        document.getElementById('alertaError').classList.remove('d-none');
        return;
    }

    const ahorroMensual = (parseFloat(ingresoMensual) * porcentajeElegido) / 100;

    try {
        const response = await fetch(`${API}/api/auth/registro`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                nombre,
                username,
                password,
                ingresoMensual: parseFloat(ingresoMensual),
                ahorroMensual,
                porcentajeAhorroInicial: porcentajeElegido,
                rol: 'USER'
            })
        });

        if (response.ok) {
            document.getElementById('alertaExito').classList.remove('d-none');
            document.getElementById('alertaExito').textContent = 'Cuenta creada exitosamente! Redirigiendo...';
            setTimeout(() => window.location.href = 'index.html', 2000);
        } else {
            mostrarError('alertaError', 'Error al crear la cuenta');
        }
    } catch (error) {
        mostrarError('alertaError', 'Error de conexion con el servidor');
    }
}

// ===== SISTEMA DE RANGOS =====

function calcularRango(ingresoMensual, totalAhorrado) {
    if (!ingresoMensual || ingresoMensual === 0) return getRangoInfo('piedra');
    const porcentaje = (totalAhorrado / ingresoMensual) * 100;

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
// ===== UTILIDADES =====

function mostrarError(id, mensaje) {
    const el = document.getElementById(id);
    el.classList.remove('d-none');
    el.textContent = mensaje;
}

function ocultarError(id) {
    document.getElementById(id).classList.add('d-none');
}