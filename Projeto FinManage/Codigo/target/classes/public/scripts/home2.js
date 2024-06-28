document.addEventListener("DOMContentLoaded", function () {
    var usuarioLogado = localStorage.getItem("nomeUsuario");

    if (usuarioLogado) {
        var nomeUsuarioElementHeader = document.getElementById("nomeUsuarioHeader");
        nomeUsuarioElementHeader.textContent = usuarioLogado;

        var nomeUsuarioElementHero = document.getElementById("nomeUsuarioHero");
        nomeUsuarioElementHero.textContent = usuarioLogado;
    } else {
        window.location.href = "login.html";
    }
});
