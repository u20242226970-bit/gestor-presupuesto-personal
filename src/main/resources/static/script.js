// Cargar gastos al abrir la página
function cargarGastos() {
    fetch('http://localhost:8080/gastos')
        .then(response => response.json())
        .then(gastos => {
            const tabla = document.getElementById('tablaGastos');
            tabla.innerHTML = '';
            gastos.forEach(gasto => {
                tabla.innerHTML += `
                    <tr>
                        <td>${gasto.id}</td>
                        <td>${gasto.descripcion}</td>
                        <td>$${gasto.monto}</td>
                        <td>${gasto.categoria}</td>
                        <td>
                            <button class="btn-eliminar" 
                                onclick="eliminarGasto(${gasto.id})">
                                Eliminar
                            </button>
                        </td>
                    </tr>
                `;
            });
        });
}

// Agregar gasto nuevo
document.getElementById('formularioGasto')
    .addEventListener('submit', function(e) {
        e.preventDefault();
        
        const gasto = {
            descripcion: document.getElementById('descripcion').value,
            monto: document.getElementById('monto').value,
            categoria: document.getElementById('categoria').value
        };

        fetch('http://localhost:8080/gastos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(gasto)
        })
        .then(response => response.json())
        .then(() => {
            cargarGastos();
            document.getElementById('formularioGasto').reset();
        });
});

// Eliminar gasto
function eliminarGasto(id) {
    fetch(`http://localhost:8080/gastos/${id}`, {
        method: 'DELETE'
    })
    .then(() => cargarGastos());
}

// Ejecutar al cargar la página
cargarGastos();