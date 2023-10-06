// Seleciona o formulário e adiciona um ouvinte de evento para o evento submit
document.querySelector('form').addEventListener('submit', function (event) {
    event.preventDefault(); // Previne o comportamento padrão do evento

    // Obtém os valores dos campos do formulário
    const titulo = document.querySelector('#titulo').value;
    const valor = document.querySelector('#valor').value;
    const data = document.querySelector('#data').value;
    const categoria = document.querySelector('#categoria').value;

    // Validação simples dos campos (verifica se todos foram preenchidos)
    if (titulo && valor && data && categoria) {
        // Cria um objeto com os valores dos campos
        const despesa = { titulo, valor, data, categoria };

        // Adiciona a despesa à tabela
        adicionarDespesa(despesa);

        // Limpa os campos do formulário
        document.querySelector('form').reset();
    } else {
        // Caso algum campo esteja vazio, exibe uma mensagem de erro
        alert('Todos os campos são obrigatórios!');
    }
});



let despesaSelecionada;


// Função para adicionar uma nova despesa à tabela
function adicionarDespesa(despesa) {

    // Obtém o ID da conta logada do Local Storage
    const contaLogadaId = localStorage.getItem("contaLogada");

    // Associa o ID da conta logada à despesa
    despesa.contaId = contaLogadaId;
    const tabela = document.querySelector('table tbody');

    // Verifica se a despesa já existe na tabela
    const linhas = tabela.querySelectorAll('tr');
    let despesaExiste = false;
    linhas.forEach(linha => {
        if (
            linha.children[0].textContent === despesa.titulo &&
            linha.children[1].textContent === despesa.valor.toString() &&
            linha.children[2].textContent === despesa.data &&
            linha.children[3].textContent === despesa.categoria
        ) {
            despesaExiste = true;
            return;
        }
    });

    if (despesaExiste) {
        return;
    }

    // Cria uma nova linha na tabela
    const linha = document.createElement('tr');

    // Adiciona as células com os valores da despesa
    linha.innerHTML = `
        <td>${despesa.titulo}</td>
        <td>${despesa.valor}</td>
        <td>${despesa.data}</td>
        <td>${despesa.categoria}</td>
        <td><button class="editar">Editar</button></td>
        <td><button class="excluir">Excluir</button></td>
    `;

    // Adiciona a linha à tabela
    tabela.appendChild(linha);

    // Adiciona um ouvinte de evento para o botão de editar
    linha.querySelector('.editar').addEventListener('click', function () {
        // Armazena a despesa selecionada na variável global
        despesaSelecionada = despesa;

        // Remove a linha da tabela
        tabela.removeChild(linha);

        // Remove a despesa do array de despesas
        const index = despesasLocalStorage.findIndex((d) => {
            return (
                d.titulo === despesa.titulo &&
                d.valor === despesa.valor &&
                d.data === despesa.data &&
                d.categoria === despesa.categoria
            );
        });
        if (index !== -1) {
            despesasLocalStorage.splice(index, 1);
        }

        // Salva o novo array na Local Storage
        localStorage.setItem('despesas', JSON.stringify(despesasLocalStorage));

        // Preenche os campos de formulário com os valores da despesa selecionada
        document.querySelector('#titulo').value = despesa.titulo;
        document.querySelector('#valor').value = despesa.valor;
        document.querySelector('#data').value = despesa.data;
        document.querySelector('#categoria').value = despesa.categoria;


    });


    // Adiciona um ouvinte de evento para o botão de excluir
    linha.querySelector('.excluir').addEventListener('click', function () {
        // Remove a linha da tabela
        tabela.removeChild(linha);

        // Remove a despesa do array de despesas
        const index = despesasLocalStorage.findIndex((d) => {
            return (
                d.titulo === despesa.titulo &&
                d.valor === despesa.valor &&
                d.data === despesa.data &&
                d.categoria === despesa.categoria
            );
        });
        if (index !== -1) {
            despesasLocalStorage.splice(index, 1);
        }

        // Salva o novo array na Local Storage
        localStorage.setItem('despesas', JSON.stringify(despesasLocalStorage));
    });




    // Obtém os dados de despesas já cadastrados na Local Storage
    const despesasLocalStorage = JSON.parse(localStorage.getItem('despesas')) || [];

    // Verifica se a despesa já existe na Local Storage
    const despesaLocalStorageExiste = despesasLocalStorage.some((d) => {
        return (
            d.titulo === despesa.titulo &&
            d.valor === despesa.valor &&
            d.data === despesa.data &&
            d.categoria === despesa.categoria
        );
    });

    if (!despesaLocalStorageExiste) {
        // Adiciona a nova despesa ao array de despesas
        despesasLocalStorage.push(despesa);

        // Converte o array de despesas para o formato JSON e salva na Local Storage
        localStorage.setItem('despesas', JSON.stringify(despesasLocalStorage));
    }
}


let primeiraCarga = true;

// Carrega os dados da Local Storage na tabela
function carregarDespesas() {
    const tabela = document.querySelector('table tbody');

    if (primeiraCarga) {
        tabela.innerHTML = '';
    }

    // Obtém o ID da conta logada do Local Storage
    contaLogadaId = localStorage.getItem("contaLogada");

    const despesasLocalStorage = JSON.parse(localStorage.getItem('despesas')) || [];

    despesasLocalStorage.forEach(function (despesa) {
        // Verifica se a despesa pertence à conta logada
        if (despesa.contaId === contaLogadaId) {
            adicionarDespesa(despesa);
        }
    });

    primeiraCarga = false;
}

carregarDespesas();
