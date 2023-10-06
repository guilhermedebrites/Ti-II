// Função para adicionar um novo cadastro no Local Storage e na tabela
function adicionarCadastro(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores do formulário
    var preco = document.getElementById("preco").value;
    var despesa = document.getElementById("despesa").value;
    var recorrencia = document.getElementById("recorrencia").value;

    // Verifica se todos os campos foram preenchidos
    if (preco === "" || despesa === "" || recorrencia === "") {
        alert("Por favor, preencha todos os campos do formulário.");
        return; // Retorna e não executa o restante da função
    }

    // Obtém o ID da conta logada do Local Storage
    var contaLogada = localStorage.getItem("contaLogada");

    // Cria um objeto com os valores do cadastro
    var cadastro = {
        preco: preco,
        despesa: despesa,
        recorrencia: recorrencia,
        contaId: contaLogada
    };

    // Verifica se já existem cadastros armazenados no Local Storage
    if (localStorage.getItem("despesaFixa") === null) {
        // Se não existir, cria um novo array e adiciona o cadastro
        var cadastros = [];
        cadastros.push(cadastro);
        localStorage.setItem("despesaFixa", JSON.stringify(cadastros));
    } else {
        // Se existir, obtém os cadastros existentes, adiciona o novo cadastro e atualiza o Local Storage
        var cadastros = JSON.parse(localStorage.getItem("despesaFixa"));
        cadastros.push(cadastro);
        localStorage.setItem("despesaFixa", JSON.stringify(cadastros));
    }

    // Limpa os campos do formulário após o cadastro ser adicionado
    document.getElementById("preco").value = "";
    document.getElementById("despesa").value = "";
    document.getElementById("recorrencia").value = "";

    // Atualiza a tabela
    atualizarTabela();

    // Exibe uma mensagem de sucesso
    alert("Cadastro adicionado com sucesso!");
}

// Função para remover um cadastro do Local Storage e da tabela
function removerCadastro(index) {
    // Obtém os cadastros do Local Storage
    var cadastros = JSON.parse(localStorage.getItem("despesaFixa"));

    // Obtém o ID da conta logada do Local Storage
    var contaLogada = localStorage.getItem("contaLogada");

    // Filtra os cadastros mantendo apenas aqueles cujo contaId é igual ao contaLogada
    var cadastrosFiltrados = cadastros.filter(function (cadastro) {
        return cadastro.contaId === contaLogada;
    });

    // Verifica se o índice está dentro do intervalo válido para os cadastros filtrados
    if (index >= 0 && index < cadastrosFiltrados.length) {
        // Encontra o índice correspondente nos cadastros originais
        var originalIndex = cadastros.indexOf(cadastrosFiltrados[index]);

        // Remove o cadastro com o índice original especificado
        cadastros.splice(originalIndex, 1);

        // Atualiza o Local Storage com os cadastros atualizados
        localStorage.setItem("despesaFixa", JSON.stringify(cadastros));

        // Atualiza a tabela
        atualizarTabela();
    }
}



// Função para atualizar a tabela com os cadastros do Local Storage
function atualizarTabela() {
    // Obtém o elemento da tabela
    var tabela = document.getElementById("tabela-cadastros");

    // Limpa a tabela, mantendo apenas a linha superior
    while (tabela.rows.length > 1) {
        tabela.deleteRow(1);
    }

    // Obtém o ID da conta logada do Local Storage
    var contaLogada = localStorage.getItem("contaLogada");

    // Obtém os cadastros do Local Storage
    var cadastros = JSON.parse(localStorage.getItem("despesaFixa"));

    // Verifica se existem cadastros
    if (cadastros !== null) {
        // Filtra os cadastros com base no ID da conta logada
        var cadastrosFiltrados = cadastros.filter(function (cadastro) {
            return cadastro.contaId === contaLogada;
        });

        // Para cada cadastro filtrado, cria uma nova linha na tabela
        cadastrosFiltrados.forEach(function (cadastro, index) {
            var preco = cadastro.preco;
            var despesa = cadastro.despesa;
            var recorrencia = cadastro.recorrencia;

            // Cria uma nova linha na tabela
            var row = tabela.insertRow();

            // Insere as células na linha
            var cellPreco = row.insertCell();
            var cellDespesa = row.insertCell();
            var cellRecorrencia = row.insertCell();
            var cellRemover = row.insertCell();

            // Preenche as células com os valores do cadastro
            cellPreco.innerHTML = preco;
            cellDespesa.innerHTML = despesa;
            cellRecorrencia.innerHTML = recorrencia;
            cellRemover.innerHTML =
                "<button onclick=\"removerCadastro(" + index + ")\">Remover</button>";
        });
    }
}

// Adiciona um evento de clique ao botão "Cadastrar"
var cadastrarButton = document.querySelector('input[type="submit"]');
cadastrarButton.addEventListener("click", adicionarCadastro);

// Atualiza a tabela quando a página é carregada
window.addEventListener("load", atualizarTabela);
