document.addEventListener('DOMContentLoaded', (event) => {
  var planejamentoFinanceiro = JSON.parse(localStorage.getItem('planejamentoFinanceiro'));

  if (Array.isArray(planejamentoFinanceiro)) {
      const lista = document.getElementById('listaPlanejamento');

      lista.innerHTML = '';

      planejamentoFinanceiro.forEach((planejamento) => {
          const item = document.createElement('li');

          Object.values(planejamento).forEach((valor) => {
              const subItem = document.createElement('span');
              subItem.textContent = valor;
              item.appendChild(subItem);
          });

          lista.appendChild(item);
      });
  }
});
