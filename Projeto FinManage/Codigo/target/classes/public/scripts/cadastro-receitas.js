var contaLogada = localStorage.getItem("idUsuario");
let ReceitasArray = [];
var ReceitaAtualId = null;
// Adicionar despesa à tabela
function adicionarReceita(receita) {
    const tabela = document.querySelector('table tbody');
    const linha = document.createElement('tr');

    const valorNumerico = parseFloat(receita.valor);
    let valorFinal;
    if (!isNaN(valorNumerico)) {
        const valorFormatado = valorNumerico.toFixed(2);
        valorFinal = `R$${valorFormatado}`;
    }

    linha.innerHTML = `
        <td>${valorFinal}</td>
        <td>${receita.fonte}</td>
        <td>${receita.data}</td>
        <td><button class="editar">Editar</button></td>
        <td><button class="excluir" onclick="deleteReceita(${receita.id}, this.closest('tr'))">Excluir</button></td>
    `;

    tabela.appendChild(linha);

    linha.querySelector('.editar').addEventListener('click', function() {
        editarReceita(receita.id, receita);
    });


}

axios.get(`http://localhost:6789/receita/get/${contaLogada}`)
        .then((response) => {
            ReceitasArray = response.data;
            ReceitasArray.forEach(adicionarReceita);
            console.log(response.data);
        })
        .catch((error) => {
            console.error("Erro ao enviar requisição:", error);
        });
    
// Seleciona o formulário e adiciona um ouvinte de evento para o evento submit
document.querySelector('form').addEventListener('submit', function (event) {
    event.preventDefault(); // Previne o comportamento padrão do evento

    var valor = document.getElementById("valor").value;
    var fonte = document.getElementById("fonte").value;
    var data = document.getElementById("data").value;
    var idUsuario = contaLogada;
    

    // Validação simples dos campos (verifica se todos foram preenchidos)
    if (valor && fonte && data) {
        // Cria um objeto com os valores dos campos
        var receita = {
            data: data,
            valor: valor,
            fonte: fonte,
            id_usuario: idUsuario,
        };

        if (ReceitaAtualId) {
            axios.put(`http://localhost:6789/receita/update/${ReceitaAtualId}`, receita)
                .then(response => {
                    console.log(response.data);
                    location.reload();
                    ReceitaAtualId = null;
                })
                .catch(error => {
                    console.error("Erro ao enviar requisição:", error);
                });
        }
        else {
            axios.post("http://localhost:6789/receita/insert", receita)
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.error("Erro ao enviar requisição:", error);
            });
        
            adicionarReceita(receita);
            location.reload();
        }
        

        // Limpa os campos do formulário
        document.querySelector('form').reset();
    } else {
        // Caso algum campo esteja vazio, exibe uma mensagem de erro
        alert('Todos os campos são obrigatórios!');
    }
});

function deleteReceita(idReceita, linha) {
    const tabela = document.querySelector('table tbody');
    tabela.removeChild(linha);
    axios.delete(`http://localhost:6789/receita/delete/${idReceita}`)
    .then((response) => {
        console.log(response.data);
    })
    .catch((error) => {
        console.error("Erro ao enviar requisição:", error);
    });
};

function editarReceita(idReceita, receita) {
    ReceitaAtualId = idReceita;

    document.getElementById("valor").value = receita.valor;
    document.getElementById("fonte").value = receita.fonte;
    document.getElementById("data").value = receita.data;
}

