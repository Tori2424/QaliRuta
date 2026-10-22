document.addEventListener('DOMContentLoaded', () => {
    const tarjetasIngredientes = document.querySelectorAll('.ingredient-card');
    const contadorTexto = document.getElementById('contador-ingredientes');
    const inputHierro = document.getElementById('inputHierro');

    function actualizarCalculo() {
        const seleccionados = document.querySelectorAll('.ingredient-card.selected');
        if (contadorTexto) {
            contadorTexto.textContent = `SUGERENCIAS RICAS EN HIERRO (${seleccionados.length} SELECCIONADOS)`;
        }

        if (inputHierro) {
            if (seleccionados.length > 0) {
                let totalHierro = 0;
                seleccionados.forEach(card => {
                    const h = parseFloat(card.dataset.hierro || '0');
                    totalHierro += h;
                });
                inputHierro.value = totalHierro.toFixed(1);
            } else {
                inputHierro.value = '';
            }
        }
    }

    // Inicializar contador al cargar la página (para modo edición)
    const seleccionadosIniciales = document.querySelectorAll('.ingredient-card.selected');
    if (contadorTexto && seleccionadosIniciales.length > 0) {
        contadorTexto.textContent = `SUGERENCIAS RICAS EN HIERRO (${seleccionadosIniciales.length} SELECCIONADOS)`;
    }

    tarjetasIngredientes.forEach(tarjeta => {
        tarjeta.addEventListener('click', () => {
            tarjeta.classList.toggle('selected');
            const checkbox = tarjeta.querySelector('.ingredient-checkbox');
            if (checkbox) {
                checkbox.checked = tarjeta.classList.contains('selected');
            }
            actualizarCalculo();                 
        });
    });
});
