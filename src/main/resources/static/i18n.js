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

        // --- Registro (register.html) ---
        reg_titulo: "Crear Cuenta",
        reg_subtitulo: "Unete a FinanceRank",
        reg_nombre: "Nombre completo",
        reg_usuario: "Usuario",
        reg_password: "Contrasena",
        reg_ingreso: "Ingreso mensual",
        reg_paso1_boton: "Continuar",
        reg_paso2_titulo: "Cual es tu porcentaje de ahorro actual?",
        reg_paso2_desc: "Esto define tu rango inicial",
        reg_boton: "Crear Cuenta",
        reg_volver: "Volver",
        reg_ya_cuenta: "Ya tienes cuenta?",
        reg_inicia: "Inicia sesion",

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

        // --- Gastos (gastos.html) ---
        gastos_titulo: "Mis Gastos",
        gastos_nuevo: "Nuevo Gasto",
        gastos_total: "Total Gastado",
        gastos_numero: "Numero de Gastos",
        gastos_promedio: "Promedio por Gasto",
        gastos_th_desc: "Descripcion",
        gastos_th_cat: "Categoria",
        gastos_th_monto: "Monto",
        gastos_th_fecha: "Fecha",
        gastos_th_acciones: "Acciones",
        gastos_modal_titulo: "Nuevo Gasto",
        gastos_form_desc: "Descripcion",
        gastos_form_monto: "Monto",
        gastos_form_cat: "Categoria",
        gastos_form_cat_select: "Selecciona una categoria",
        gastos_form_fecha: "Fecha",
        gastos_cancelar: "Cancelar",
        gastos_guardar: "Guardar",

        // --- Metas (metas.html) ---
        metas_titulo: "Mis Metas de Ahorro",
        metas_nueva: "Nueva Meta",
        metas_modal_titulo: "Nueva Meta",
        metas_form_nombre: "Nombre de la meta",
        metas_form_emoji: "Emoji",
        metas_form_emoji_help: "Copia un emoji que represente tu meta",
        metas_form_precio: "Precio objetivo",
        metas_form_fecha: "Fecha limite (opcional)",
        metas_cancelar: "Cancelar",
        metas_crear: "Crear Meta",
        metas_abonar_titulo: "Abonar a Meta",
        metas_abono_monto: "Monto a abonar",
        metas_abono_desc: "Descripcion",
        metas_abonar_boton: "Abonar",
        metas_progreso: "Progreso",
        metas_ahorrado: "Ahorrado",
        metas_objetivo: "Objetivo",
        metas_falta: "Te falta",

        // --- Perfil (perfil.html) ---
        perfil_titulo: "Mi Perfil",
        perfil_datos: "Datos de la cuenta",
        perfil_usuario: "Usuario",
        perfil_rol: "Rol",
        perfil_ingreso: "Ingreso mensual",
        perfil_ahorro: "Ahorro mensual",
        perfil_editar: "Editar perfil",
        perfil_nombre: "Nombre completo",
        perfil_guardar: "Guardar cambios",

        // --- Ranking (leaderboard.html) ---
        rank_titulo: "Ranking de Ahorradores",
        rank_subtitulo: "Clasificacion segun el porcentaje de ingreso ahorrado",
        rank_tabla_titulo: "Tabla de posiciones",
        rank_th_rango: "Rango",
        rank_th_nombre: "Nombre",
        rank_th_usuario: "Usuario",
        rank_th_porcentaje: "% Ahorro",

        // --- Rangos ---
        rango_piedra: "Piedra",
        rango_bronce: "Bronce",
        rango_plata: "Plata",
        rango_oro: "Oro",
        rango_diamante: "Diamante",
        rango_elite: "Elite"
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

        // --- Registro (register.html) ---
        reg_titulo: "Create Account",
        reg_subtitulo: "Join FinanceRank",
        reg_nombre: "Full name",
        reg_usuario: "Username",
        reg_password: "Password",
        reg_ingreso: "Monthly income",
        reg_paso1_boton: "Continue",
        reg_paso2_titulo: "What is your current savings rate?",
        reg_paso2_desc: "This sets your initial rank",
        reg_boton: "Create Account",
        reg_volver: "Back",
        reg_ya_cuenta: "Already have an account?",
        reg_inicia: "Sign in",

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

        // --- Gastos (gastos.html) ---
        gastos_titulo: "My Expenses",
        gastos_nuevo: "New Expense",
        gastos_total: "Total Spent",
        gastos_numero: "Number of Expenses",
        gastos_promedio: "Average per Expense",
        gastos_th_desc: "Description",
        gastos_th_cat: "Category",
        gastos_th_monto: "Amount",
        gastos_th_fecha: "Date",
        gastos_th_acciones: "Actions",
        gastos_modal_titulo: "New Expense",
        gastos_form_desc: "Description",
        gastos_form_monto: "Amount",
        gastos_form_cat: "Category",
        gastos_form_cat_select: "Select a category",
        gastos_form_fecha: "Date",
        gastos_cancelar: "Cancel",
        gastos_guardar: "Save",

        // --- Metas (metas.html) ---
        metas_titulo: "My Savings Goals",
        metas_nueva: "New Goal",
        metas_modal_titulo: "New Goal",
        metas_form_nombre: "Goal name",
        metas_form_emoji: "Emoji",
        metas_form_emoji_help: "Copy an emoji that represents your goal",
        metas_form_precio: "Target amount",
        metas_form_fecha: "Deadline (optional)",
        metas_cancelar: "Cancel",
        metas_crear: "Create Goal",
        metas_abonar_titulo: "Add to Goal",
        metas_abono_monto: "Amount to add",
        metas_abono_desc: "Description",
        metas_abonar_boton: "Add Funds",
        metas_progreso: "Progress",
        metas_ahorrado: "Saved",
        metas_objetivo: "Target",
        metas_falta: "Remaining",

        // --- Perfil (perfil.html) ---
        perfil_titulo: "My Profile",
        perfil_datos: "Account details",
        perfil_usuario: "Username",
        perfil_rol: "Role",
        perfil_ingreso: "Monthly income",
        perfil_ahorro: "Monthly savings",
        perfil_editar: "Edit profile",
        perfil_nombre: "Full name",
        perfil_guardar: "Save changes",

        // --- Ranking (leaderboard.html) ---
        rank_titulo: "Savers Ranking",
        rank_subtitulo: "Ranked by percentage of income saved",
        rank_tabla_titulo: "Standings",
        rank_th_rango: "Rank",
        rank_th_nombre: "Name",
        rank_th_usuario: "Username",
        rank_th_porcentaje: "% Saved",

        // --- Rangos ---
        rango_piedra: "Stone",
        rango_bronce: "Bronze",
        rango_plata: "Silver",
        rango_oro: "Gold",
        rango_diamante: "Diamond",
        rango_elite: "Elite"
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

    document.querySelectorAll('[data-i18n]').forEach(el => {
        const clave = el.getAttribute('data-i18n');
        if (dict[clave]) {
            el.textContent = dict[clave];
        }
    });

    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
        const clave = el.getAttribute('data-i18n-placeholder');
        if (dict[clave]) {
            el.placeholder = dict[clave];
        }
    });

    document.documentElement.lang = idioma;

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

/**
 * Traduce el nombre de una categoria base segun el idioma actual.
 * Si la categoria no esta en el mapa (ej: una creada por el usuario),
 * devuelve el nombre original sin traducir.
 * @param nombre el nombre de la categoria en espanol
 * @return el nombre traducido, o el original si no esta mapeado
 */
function traducirCategoria(nombre) {
    if (getIdioma() === 'es') return nombre; // en espanol se queda igual

    const mapa = {
        'Alimentacion': 'Food',
        'Alimentación': 'Food',
        'Transporte': 'Transport',
        'Salud': 'Health',
        'Educacion': 'Education',
        'Educación': 'Education',
        'Entretenimiento': 'Entertainment',
        'Vivienda': 'Housing',
        'Ropa': 'Clothing',
        'Servicios': 'Utilities',
        'Otros': 'Others',
        'Sin categoria': 'No category',
        'Sin categoría': 'No category'
    };
    return mapa[nombre] || nombre;
}
// Aplica el idioma guardado apenas carga la pagina
document.addEventListener('DOMContentLoaded', aplicarTraducciones);