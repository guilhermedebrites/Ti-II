document.addEventListener("DOMContentLoaded", function () {
    // Verifica se há um usuário logado (exemplo: obtendo o nome do usuário do Local Storage)
    var usuarioLogado = localStorage.getItem("nomeUsuario");

    if (usuarioLogado) {
        // Exibe o nome do usuário no elemento span com id "nomeUsuarioHeader" no header
        var nomeUsuarioElementHeader = document.getElementById("nomeUsuarioHeader");
        nomeUsuarioElementHeader.textContent = usuarioLogado;

        // Exibe o nome do usuário no elemento span com id "nomeUsuarioHero" na seção hero
        var nomeUsuarioElementHero = document.getElementById("nomeUsuarioHero");
        nomeUsuarioElementHero.textContent = usuarioLogado;
    } else {
        // Redireciona o usuário para a página de login caso não esteja logado
        window.location.href = "login.html";
    }
});
