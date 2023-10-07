// Função para adicionar uma nova receita no Local Storage e na tabela
function adicionarReceita(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores do formulário
    var valor = document.getElementById("valor").value;
    var fonte = document.getElementById("fonte").value;
    var data = document.getElementById("data").value;

    // Verifica se todos os campos foram preenchidos
    if (valor === "" || fonte === "" || data === "") {
        alert("Por favor, preencha todos os campos do formulário.");
        return; // Retorna e não executa o restante da função
    }

    // Obtém o ID da conta logada do Local Storage
    var contaLogada = localStorage.getItem("contaLogada");

    // Cria um objeto com os valores da receita
    var receita = {
        valor: valor,
        fonte: fonte,
        data: data,
        contaId: contaLogada
    };

    // Verifica se já existem receitas armazenadas no Local Storage
    if (localStorage.getItem("receitas") === null) {
        // Se não existir, cria um novo array e adiciona a receita
        var receitas = [];
        receitas.push(receita);
        localStorage.setItem("receitas", JSON.stringify(receitas));
    } else {
        // Se existir, obtém as receitas existentes, adiciona a nova receita e atualiza o Local Storage
        var receitas = JSON.parse(localStorage.getItem("receitas"));
        receitas.push(receita);
        localStorage.setItem("receitas", JSON.stringify(receitas));
    }

    // Limpa os campos do formulário após a receita ser adicionada
    document.getElementById("valor").value = "";
    document.getElementById("fonte").value = "";
    document.getElementById("data").value = "";

    // Atualiza a tabela
    atualizarTabela();

    // Exibe uma mensagem de sucesso
    alert("Receita adicionada com sucesso!");
}

// Função para remover uma receita do Local Storage e da tabela
function removerReceita(index) {
    // Obtém as receitas do Local Storage
    var receitas = JSON.parse(localStorage.getItem("receitas"));

    // Remove a receita com o índice especificado
    receitas.splice(index, 1);

    // Atualiza o Local Storage com as receitas atualizadas
    localStorage.setItem("receitas", JSON.stringify(receitas));

    // Atualiza a tabela
    atualizarTabela();
}

// Função para atualizar a tabela com as receitas do Local Storage
function atualizarTabela() {
    var tabela = document.getElementById("tabela-receitas");
    while (tabela.rows.length > 1) {
        tabela.deleteRow(1);
    }

    var receitas = JSON.parse(localStorage.getItem("receitas"));
    var contaLogada = localStorage.getItem("contaLogada");

    if (receitas !== null) {
        receitas.forEach(function (receita, index) {
            if (receita.contaId === contaLogada) { // Verifica se o ID da conta da receita é igual ao ID da conta logada
                var valor = receita.valor;
                var fonte = receita.fonte;
                var data = receita.data;

                var row = tabela.insertRow();
                var cellValor = row.insertCell();
                var cellFonte = row.insertCell();
                var cellData = row.insertCell();
                var cellRemover = row.insertCell();

                cellValor.innerHTML = valor;
                cellFonte.innerHTML = fonte;
                cellData.innerHTML = data;
                cellRemover.innerHTML = "<button onclick=\"removerReceita(" + index + ")\">Remover</button>";
            }
        });
    }
}

// Adiciona um evento de clique ao botão "Adicionar Receita"
var adicionarButton = document.querySelector('#form-receitas button[type="submit"]');
adicionarButton.addEventListener("click", adicionarReceita);

// Atualiza a tabela quando a página é carregada
window.addEventListener("load", atualizarTabela);
