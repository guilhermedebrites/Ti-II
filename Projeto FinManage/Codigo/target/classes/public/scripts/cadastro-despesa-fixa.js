var contaLogada = localStorage.getItem("idUsuario");
let DespesasArray = [];
var despesaAtualId = null;
// Adicionar despesa à tabela
function adicionarDespesa(despesa) {
    const tabela = document.querySelector('table tbody');
    const linha = document.createElement('tr');


    const recorrenciaCapitalizada = despesa.recorrencia.charAt(0).toUpperCase() + despesa.recorrencia.slice(1);

    const valorNumerico = parseFloat(despesa.valor);
    let valorFinal;
    if (!isNaN(valorNumerico)) {
        const valorFormatado = valorNumerico.toFixed(2);
        valorFinal = `R$${valorFormatado}`;
    }

    linha.innerHTML = `
        <td>${valorFinal}</td>
        <td>${despesa.nome}</td>
        <td>${recorrenciaCapitalizada}</td>
        <td><button class="editar">Editar</button></td>
        <td><button class="excluir" onclick="deleteDespesa(${despesa.id}, this.closest('tr'))">Excluir</button></td>
    `;

    tabela.appendChild(linha);

    linha.querySelector('.editar').addEventListener('click', function() {
        editarDespesa(despesa.id, despesa);
    });


}

axios.get(`http://localhost:6789/despesa-fix/get/${contaLogada}`)
        .then((response) => {
            DespesasArray = response.data;
            DespesasArray.forEach(adicionarDespesa);
            console.log(response.data);
        })
        .catch((error) => {
            console.error("Erro ao enviar requisição:", error);
        });
    
document.querySelector('form').addEventListener('submit', function (event) {
    event.preventDefault(); 

    var preco = document.getElementById("preco").value;
    var nome = document.getElementById("despesa").value;
    var recorrencia = document.getElementById("recorrencia").value;
    var idUsuario = contaLogada;
    

    if (preco && nome && recorrencia) {
        var despesaFixa = {
            nome: nome,
            valor: preco,
            id_usuario: idUsuario,
            recorrencia: recorrencia
        };

        if (despesaAtualId) {
            axios.put(`http://localhost:6789/despesa-fix/update/${despesaAtualId}`, despesaFixa)
                .then(response => {
                    console.log(response.data);
                    location.reload();
                    despesaAtualId = null;
                })
                .catch(error => {
                    console.error("Erro ao enviar requisição:", error);
                });
        }
        else {
            axios.post("http://localhost:6789/despesa-fix/insert", despesaFixa)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error("Erro ao enviar requisição:", error);
            });
        
            adicionarDespesa(despesaFixa);
        }
        

        document.querySelector('form').reset();
    } else {
        alert('Todos os campos são obrigatórios!');
    }
});

function deleteDespesa(idDespesa, linha) {
    const tabela = document.querySelector('table tbody');
    tabela.removeChild(linha);
    axios.delete(`http://localhost:6789/despesa-fix/delete/${idDespesa}`)
    .then((response) => {
        console.log(response.data);
    })
    .catch((error) => {
        console.error("Erro ao enviar requisição:", error);
    });
};

function editarDespesa(idDespesa, despesa) {
    despesaAtualId = idDespesa;

    document.getElementById("preco").value = despesa.valor;
    document.getElementById("despesa").value = despesa.nome;
    document.getElementById("recorrencia").value = despesa.recorrencia;
}

