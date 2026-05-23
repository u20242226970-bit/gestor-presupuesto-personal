// ============================================
// SISTEMA DE INTERNACIONALIZACION (i18n)
// Soporta espanol (es) e ingles (en)
// ============================================

const traducciones = {
    es: {
        // --- Login (index.html) ---
        login_titulo: "Bienvenido",
        login_subtitulo: "Inicia sesion en tu cuenta",
        login_usuario: "Usuario",
        login_password: "Contrasena",
        login_boton: "Iniciar Sesion",
        login_no_cuenta: "No tienes cuenta?",
        login_registrate: "Registrate aqui",
        hero_eslogan: "Controla tus gastos, define metas de ahorro y sube de rango financiero.",

        // --- Navbar / comun ---
        nav_salir: "Salir",
        nav_dashboard: "Dashboard",
        nav_gastos: "Gastos",
        nav_metas: "Metas",
        nav_perfil: "Perfil",
        nav_ranking: "Ranking",

        // --- Dashboard ---
        dash_titulo: "Panel Principal",
        dash_descargar: "Descargar Reporte",
        dash_ingreso: "Ingreso Mensual",
        dash_gastado: "Total Gastado",
        dash_ahorro: "Ahorro Mensual",
        dash_metas_activas: "Metas Activas",
        dash_actualizar_titulo: "Actualizar mis Finanzas",
        dash_actualizar_desc: "Manten tu informacion actualizada para un rango preciso",
        dash_ingreso_mensual: "Ingreso mensual",
        dash_ahorro_mensual: "Ahorro mensual",
        dash_boton_actualizar: "Actualizar",
        dash_sistema_rangos: "Sistema de Rangos",
        dash_grafica_titulo: "Gastos por categoria",
        dash_ultimos_gastos: "Ultimos Gastos",
        dash_ver_todos: "Ver todos",
        dash_sin_gastos_grafica: "Aun no hay gastos para mostrar en la grafica",
		
		// --- Rangos ---
		        rango_piedra: "Piedra",
		        rango_bronce: "Bronce",
		        rango_plata: "Plata",
		        rango_oro: "Oro",
		        rango_diamante: "Diamante",
		        rango_elite: "Elite",
    },
    en: {
        // --- Login (index.html) ---
        login_titulo: "Welcome",
        login_subtitulo: "Sign in to your account",
        login_usuario: "Username",
        login_password: "Password",
        login_boton: "Login",
        login_no_cuenta: "Don't have an account?",
        login_registrate: "Sign up here",
        hero_eslogan: "Track your expenses, set savings goals and climb the financial ranks.",

        // --- Navbar / comun ---
        nav_salir: "Logout",
        nav_dashboard: "Dashboard",
        nav_gastos: "Expenses",
        nav_metas: "Goals",
        nav_perfil: "Profile",
        nav_ranking: "Ranking",

        // --- Dashboard ---
        dash_titulo: "Main Panel",
        dash_descargar: "Download Report",
        dash_ingreso: "Monthly Income",
        dash_gastado: "Total Spent",
        dash_ahorro: "Monthly Savings",
        dash_metas_activas: "Active Goals",
        dash_actualizar_titulo: "Update my Finances",
        dash_actualizar_desc: "Keep your information updated for an accurate rank",
        dash_ingreso_mensual: "Monthly income",
        dash_ahorro_mensual: "Monthly savings",
        dash_boton_actualizar: "Update",
        dash_sistema_rangos: "Rank System",
        dash_grafica_titulo: "Expenses by category",
        dash_ultimos_gastos: "Latest Expenses",
        dash_ver_todos: "View all",
        dash_sin_gastos_grafica: "No expenses to show in the chart yet",
		
		// --- Rangos ---
		
		        rango_piedra: "Stone",
		        rango_bronce: "Bronze",
		        rango_plata: "Silver",
		        rango_oro: "Gold",
		        rango_diamante: "Diamond",
		        rango_elite: "Elite",
    }
};

/**
 * Devuelve el idioma guardado, o "es" por defecto.
 */
function getIdioma() {
    return localStorage.getItem('idioma') || 'es';
}

/**
 * Aplica las traducciones a todos los elementos con atributo data-i18n.
 */
function aplicarTraducciones() {
    const idioma = getIdioma();
    const dict = traducciones[idioma];

    // Traduce el texto de los elementos
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const clave = el.getAttribute('data-i18n');
        if (dict[clave]) {
            el.textContent = dict[clave];
        }
    });

    // Traduce los placeholders de los inputs
    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
        const clave = el.getAttribute('data-i18n-placeholder');
        if (dict[clave]) {
            el.placeholder = dict[clave];
        }
    });

    // Actualiza el atributo lang del documento
    document.documentElement.lang = idioma;

    // Marca el boton activo del selector
    document.querySelectorAll('[data-idioma]').forEach(btn => {
        btn.classList.toggle('active', btn.getAttribute('data-idioma') === idioma);
    });
}

/**
 * Cambia el idioma, lo guarda y vuelve a aplicar las traducciones.
 */
function cambiarIdioma(idioma) {
    localStorage.setItem('idioma', idioma);
    aplicarTraducciones();
    // Si la pagina tiene una funcion para reaccionar al cambio de idioma, la llama
    if (typeof alCambiarIdioma === 'function') {
        alCambiarIdioma();
    }
}

/**
 * Devuelve el nombre traducido de un rango segun el idioma actual.
 * @param clase la clase del rango (ej: 'rank-oro')
 * @return el nombre del rango en el idioma actual
 */
function traducirRango(clase) {
    const idioma = getIdioma();
    const mapa = {
        'rank-piedra': 'rango_piedra',
        'rank-bronce': 'rango_bronce',
        'rank-plata': 'rango_plata',
        'rank-oro': 'rango_oro',
        'rank-diamante': 'rango_diamante',
        'rank-elite': 'rango_elite'
    };
    const clave = mapa[clase];
    return traducciones[idioma][clave] || clase;
}

// Aplica el idioma guardado apenas carga la pagina
document.addEventListener('DOMContentLoaded', aplicarTraducciones);