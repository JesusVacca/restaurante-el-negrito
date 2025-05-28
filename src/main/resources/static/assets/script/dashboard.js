const salesCtx = document.getElementById('salesChart').getContext('2d');
new Chart(salesCtx, {
    type: 'bar',
    data: {
        labels: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun'],
        datasets: [{
            label: 'Ventas',
            data: [50, 75, 60, 90, 110, 95],
            backgroundColor: '#89aac3ff'
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Gráfico de pastel: Categorías de productos
const categoryCtx = document.getElementById('categoryChart').getContext('2d');
new Chart(categoryCtx, {
    type: 'doughnut',
    data: {
        labels: ['Bebidas', 'Comidas', 'Snacks', 'Postres'],
        datasets: [{
            label: 'Productos',
            data: [20, 35, 25, 10],
            backgroundColor: ['#0f1939ff', '#c8b6a6ff', '#e7d8c9ff', '#89aac3ff']
        }]
    },
    options: {
        responsive: true,
        cutout: '60%'
    }
});