document.addEventListener('DOMContentLoaded', () => {
    const tarjetasIngredientes = document.querySelectorAll('.ingredient-card');
    const contadorTexto = document.getElementById('contador-ingredientes');
    const inputHierro = document.getElementById('inputHierro');

    function actualizarCalculo() {
        const seleccionados = document.querySelectorAll('.ingredient-card.selected');
        if (contadorTexto) {
            contadorTexto.textContent = `SUGERENCIAS RICAS EN HIERRO (${seleccionados.length} SELECCIONADOS)`;
        }

        // Si el usuario no ha escrito manualmente un valor o quiere sumar las sugerencias seleccionadas
        if (seleccionados.length > 0 && inputHierro && (!inputHierro.value || inputHierro.dataset.autocalc === 'true')) {
            let totalHierro = 0;
            seleccionados.forEach(card => {
                const h = parseFloat(card.dataset.hierro || '0');
                totalHierro += h;
            });
            inputHierro.value = totalHierro.toFixed(1);
            inputHierro.dataset.autocalc = 'true';
        }
    }

    if (inputHierro) {
        inputHierro.addEventListener('input', () => {
            inputHierro.dataset.autocalc = 'false';
        });
    }

    tarjetasIngredientes.forEach(tarjeta => {
        tarjeta.addEventListener('click', () => {
            tarjeta.classList.toggle('selected');
            actualizarCalculo();                 
        });
    });
});
