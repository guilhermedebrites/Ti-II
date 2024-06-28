function realizarRequisicao(dadosUsuario, tentativas = 100) {
    axios.post('http://localhost:6789/ia', dadosUsuario)
        .then((response) => {
            if (response.status === 200) {
                var investimento = response.data.Investimento;
                var gestaoDeDivida = response.data.gestaoDeDividas;
                var planejamentoFinanceiro = response.data.planejamentoFinanceiro;

                localStorage.setItem('investimento', investimento);
                localStorage.setItem('gestaoDeDivida', gestaoDeDivida);
                localStorage.setItem('planejamentoFinanceiro', JSON.stringify(planejamentoFinanceiro));

                window.location.href = 'fin-assistant-recomendation.html';
            } else {
                console.error('Resposta inválida:', response);
            }
        })
        .catch((error) => {
            console.error('Erro na requisição:', error);
            if (tentativas > 0) {
                console.log(`Tentando novamente (${tentativas} tentativas restantes)`);
                setTimeout(() => {
                    realizarRequisicao(dadosUsuario, tentativas - 1);
                }, 1000); 
            } else {
                console.error('Número máximo de tentativas atingido. A requisição falhou.');
            }
        });
}

function validarFormulario() {
    var renda = document.getElementById('renda').value;
    var capital = document.getElementById('capital').value;
    var divida = document.getElementById('divida').value;

    if (isNaN(renda) || isNaN(capital) || isNaN(divida)) {
        alert('Por favor, insira valores válidos para renda, capital e dívida.');
        return;
    }

    renda = parseFloat(renda);
    capital = parseFloat(capital);
    divida = parseFloat(divida);

    var dadosUsuario = {
        "rendaMensal": renda,
        "investimentos": capital,
        "dividas": divida,
        "objetivoFinanceiro": document.getElementById('objetivo').value
    };

    realizarRequisicao(dadosUsuario);
}

function redirecionar() {
    window.location.href = './fin-assistant-recomendation.html';
}

document.addEventListener("DOMContentLoaded", () => {
    const investimento = localStorage.getItem('investimento');
    const gestaoDeDivida = localStorage.getItem('gestaoDeDivida');
    const planejamentoFinanceiro = localStorage.getItem('planejamentoFinanceiro');

    const botao = document.getElementById("ultimasRecomendacoes");

    if (investimento && gestaoDeDivida && planejamentoFinanceiro) {
        botao.style.display = "block";
    } else {
        botao.style.display = "none";
    }
});