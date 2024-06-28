document.getElementById("formConta").addEventListener("submit", function (event) {
    event.preventDefault();

    var nome = document.getElementById("nome").value;
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;

    var conta = {
        nomeCompleto: nome,
        email: email,
        senha: senha
    };

    axios.post("http://localhost:6789/usuario/insert", conta)
        .then((response) => {
            alert("Conta criada com sucesso!")
        })
        .catch((error) => {
            console.error("Erro ao enviar requisição:", error);
        });
    
    exibirMensagemContaCriada();

});


function exibirMensagemContaCriada() {
    var mensagemContaCriada = document.getElementById("mensagemContaCriada");
    
    if (mensagemContaCriada) {
        mensagemContaCriada.style.display = "block";
        
        setTimeout(function () {
            window.location.href = "conta-criada-sucesso.html";
        }, 2000); 
    } else {
        console.error("Elemento mensagemContaCriada não encontrado!");
    }
}
