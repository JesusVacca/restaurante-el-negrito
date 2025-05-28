const tbody = document.getElementById('tbodyOrder');
const form = document.getElementById('create-order');

function addOrderToTable(checkbox) {
    const name = checkbox.dataset.name;
    const id = checkbox.id;
    const price = checkbox.dataset.price;
    checkbox.disabled = true;
    addRow(id, name, price);
}
function addRow(id, name, price){
    const row = document.createElement('tr');
    row.innerHTML = `
        <td><input type="text" class="form-control" name="${name}" value="${name}" readonly></td>
        <td><input type="text" class="form-control" name="price" value="${price}" readonly></td>
        <td><input type="number" class="form-control" name="quantity" min="1" value="1" required data-recipe-id="${id}"></td>
        <td><button type="button" class="btn btn-danger btn-sm" data-order-id="${id}" onclick="deleteRow(this)">Eliminar</button></td>
    `;
    tbody.appendChild(row);
}
function deleteRow(button){
    const id = button.dataset.orderId;
    const checkbox = document.getElementById(id);
    checkbox.disabled = false;
    checkbox.checked = false;
    button.closest('tr').remove();
}

form?.addEventListener('submit',function (e) {
    e.preventDefault();
    const data = {
        mesa_id:e.target.mesa_id?.value,
        menu_id:e.target.menu_id?.value,
        details:[]
    }
    const rows = tbody.querySelectorAll('tr');
    rows.forEach((row)=>{
        const quantity = row.querySelector('input[type="number"]');
        data.details.push(
            {
                quantity:parseInt(quantity.value),
                recipe_id:parseInt(quantity.dataset.recipeId)
            }
        )
    })
    console.log(data)
    fetch(
        '/dashboard/orders/create-order',
        {
            method:'POST',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(data)
        }
    )
    .then(response=>{
        if(!response.ok){
            const error = document.getElementById('error-alert');
            response.text()
                .then(errorText=>{
                    error.innerHTML = `
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Uup!</strong> ${errorText}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                          </div>
                        `;
                })
            throw new Error(`Error => status:${response.status}`);
        }
        return response.json();
    })
    .then(data=>{
        window.location = './';
    })
    .catch(error=>{
        console.error(error)
    })
})