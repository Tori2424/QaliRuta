document.addEventListener('DOMContentLoaded', () => {
    // seleccionar todas las tarjetas y el texto del contador
    const tarjetasIngredientes = document.querySelectorAll('.ingredient-card');
    const contadorTexto = document.getElementById('contador-ingredientes');

    // funcion que cuenta cuantos estan selecionados y actualiza el texto
    function actualizarContador() {
        const cantidadSeleccionados = document.querySelectorAll('.ingredient-card.selected').length;
        contadorTexto.textContent = `LISTA DE INGREDIENTES (${cantidadSeleccionados} SELECCIONADOS)`;
    }

    // reaccion de cada tarjeta al hacer clickj
    tarjetasIngredientes.forEach(tarjeta => {
        tarjeta.addEventListener('click', () => {
            tarjeta.classList.toggle('selected');
            actualizarContador();                 
        });
    });

    // ejecuta el contador al cargar la pag por si hay ingredinetes preseleccionados
});