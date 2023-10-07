

document.getElementById("formConta").addEventListener("submit", function (event) {
    event.preventDefault();

    // Obter os valores dos campos
    var nome = document.getElementById("nome").value;
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;

    // Verificar se o email já está cadastrado
    if (verificarEmailCadastrado(email)) {
        alert("O email já está cadastrado. Por favor, escolha outro email.");
        return;
    }

    // Verificar se o email é válido
    if (!validarEmail(email)) {
        alert("O email digitado não é válido. Por favor, insira um email válido.");
        return;
    }

    // Criar um objeto com os dados da conta
    var conta = {
        id: gerarId(),
        nome: nome,
        email: email,
        senha: senha
    };

    // Salvar a conta no repositório permanente (Local Storage)
    salvarConta(conta);

    // Limpar os campos do formulário
    document.getElementById("formConta").reset();

    // Exibir a mensagem de conta criada com sucesso
    exibirMensagemContaCriada();
});

document.getElementById("btnCancelar").addEventListener("click", function () {
    // Limpar os campos do formulário
    document.getElementById("formConta").reset();
});

function salvarConta(conta) {
    // Obter as contas existentes do repositório (Local Storage)
    var contas = obterContas();

    // Gerar um ID único para a nova conta
    var novoId = gerarId();

    // Atribuir o ID à nova conta
    conta.id = novoId;

    // Adicionar a nova conta à lista de contas
    contas.push(conta);

    // Armazenar as contas atualizadas no repositório (Local Storage)
    localStorage.setItem("contas", JSON.stringify(contas));

    // Armazenar o ID da conta logada no Local Storage
    localStorage.setItem("contaLogada", novoId);
}

function obterContas() {
    // Obter as contas do repositório (Local Storage)
    var contas = JSON.parse(localStorage.getItem("contas")) || [];
    return contas;
}

function gerarId() {
    return Math.random().toString(36).substr(2, 9);
}

function verificarEmailCadastrado(email) {
    // Obter as contas existentes do repositório (Local Storage)
    var contas = obterContas();

    // Verificar se o email já está cadastrado
    var contaExistente = contas.find(function (conta) {
        return conta.email === email;
    });

    return contaExistente !== undefined;
}

function validarEmail(email) {
    // Expressão regular para validar o email
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function exibirMensagemContaCriada() {
    // Exibe a mensagem de conta criada com sucesso
    var mensagemContaCriada = document.getElementById("mensagemContaCriada");
    mensagemContaCriada.style.display = "block";

    // Aguarda dois segundos antes de redirecionar para a página de login
    setTimeout(function () {
        window.location.href = "conta-criada-sucesso.html";
    }, 2000); // 2000 milissegundos = 2 segundos
}
