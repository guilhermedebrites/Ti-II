document.addEventListener('DOMContentLoaded', (event) => {
    var investimento = localStorage.getItem('investimento');
    var gestaoDeDivida = localStorage.getItem('gestaoDeDivida');
    
    document.getElementById('investimento').innerText = investimento;
    document.getElementById('gestaoDeDividas').innerText = gestaoDeDivida;
});
