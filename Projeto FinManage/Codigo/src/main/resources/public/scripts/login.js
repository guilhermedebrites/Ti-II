document.getElementById("login-form").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita o envio do formulário

    // Obtém os valores de nome de usuário e senha inseridos pelo usuário
    var email = document.getElementById("username").value;
    var senha = document.getElementById("password").value;

    // Constrói o objeto JSON com os dados do formulário
    var usuarioLogin = {
        email: email,
        senha: senha
    };

    axios.post("http://localhost:6789/usuario/authenticate", JSON.stringify(usuarioLogin), {
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => {
            localStorage.setItem("isLoggedIn", "true"); 

            localStorage.setItem("nomeUsuario", response.data.nomeCompleto);
            localStorage.setItem("idUsuario", response.data.id);
            window.location.href = "home2.html";
        })
        .catch((error) => {
            exibirMensagemErro("Nome de usuário ou senha incorretos");
        });
});

function exibirMensagemErro(mensagem) {
    var mensagemErro = document.getElementById("mensagemErro");
    mensagemErro.textContent = mensagem;
    mensagemErro.style.display = "block";
}
