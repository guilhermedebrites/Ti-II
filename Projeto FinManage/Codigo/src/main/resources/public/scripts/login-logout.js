const deslogarButton = document.getElementById("deslogar");
if (deslogarButton) {
    deslogarButton.addEventListener("click", () => {
        localStorage.clear();
        window.location.href = "login.html";
    });
}

const inicioButton = document.getElementById("inicio");
if (inicioButton) {
    inicioButton.addEventListener("click", () => {
        const verificaLogin = localStorage.getItem("isLoggedIn");
        if (verificaLogin) {
            window.location.href = "home2.html";
        } else {
            window.location.href = "home.html";
        }
    });
}
