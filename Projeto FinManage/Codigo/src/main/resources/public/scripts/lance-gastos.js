var contaLogada = localStorage.getItem("idUsuario");
let DespesasArray = [];
var despesaAtualId = null;
// Adicionar despesa à tabela
function adicionarDespesa(despesa) {
    const tabela = document.querySelector('table tbody');
    const linha = document.createElement('tr');

    const categoriaCapitalizada = despesa.categoria.charAt(0).toUpperCase() + despesa.categoria.slice(1);

    const valorNumerico = parseFloat(despesa.valor);
    let valorFinal;
    if (!isNaN(valorNumerico)) {
        const valorFormatado = valorNumerico.toFixed(2);
        valorFinal = `R$${valorFormatado}`;
    }

    linha.innerHTML = `
        <td>${despesa.nome}</td>
        <td>${valorFinal}</td>
        <td>${despesa.data}</td>
        <td>${categoriaCapitalizada}</td>
        <td><button class="editar">Editar</button></td>
        <td><button class="excluir" onclick="deleteDespesa(${despesa.id}, this.closest('tr'))">Excluir</button></td>
    `;

    tabela.appendChild(linha);

    linha.querySelector('.editar').addEventListener('click', function() {
        editarDespesa(despesa.id, despesa);
    });
}

axios.get(`http://localhost:6789/despesa/get/${contaLogada}`)
        .then((response) => {
            DespesasArray = response.data;
            DespesasArray.forEach(adicionarDespesa);
            console.log(response.data);
        })
        .catch((error) => {
            console.error("Erro ao enviar requisição:", error);
        });
    
// Seleciona o formulário e adiciona um ouvinte de evento para o evento submit
document.querySelector('form').addEventListener('submit', function (event) {
    event.preventDefault(); // Previne o comportamento padrão do evento

    var titulo = document.getElementById("titulo").value;
    var valor = document.getElementById("valor").value;
    var data = document.getElementById("data").value;
    var categoria = document.getElementById("categoria").value;
    var idUsuario = contaLogada;
    

    // Validação simples dos campos (verifica se todos foram preenchidos)
    if (titulo && valor && data && categoria) {
        // Cria um objeto com os valores dos campos
        var despesa = {
            categoria: categoria,
            data: data,
            valor: valor,
            nome: titulo,
            id_usuario: idUsuario
        };

        if (despesaAtualId) {
            axios.put(`http://localhost:6789/despesa/update/${despesaAtualId}`, despesa)
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
            axios.post("http://localhost:6789/despesa/insert", despesa)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error("Erro ao enviar requisição:", error);
            });
            
            adicionarDespesa(despesa);
        }
        

        // Limpa os campos do formulário
        document.querySelector('form').reset();
    } else {
        // Caso algum campo esteja vazio, exibe uma mensagem de erro
        alert('Todos os campos são obrigatórios!');
    }
});

function deleteDespesa(idDespesa, linha) {
    const tabela = document.querySelector('table tbody');
    tabela.removeChild(linha);
    axios.delete(`http://localhost:6789/despesa/delete/${idDespesa}`)
    .then((response) => {
        console.log(response.data);
    })
    .catch((error) => {
        console.error("Erro ao enviar requisição:", error);
    });
};

function editarDespesa(idDespesa, despesa) {
    despesaAtualId = idDespesa;
    
    document.getElementById("titulo").value = despesa.nome;
    document.getElementById("valor").value = despesa.valor;
    document.getElementById("data").value = despesa.data;
    document.getElementById("categoria").value = despesa.categoria;
}

