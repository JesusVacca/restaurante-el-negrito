const form = document.querySelector('#form-create-recipe');
const tbodyProducts = document.querySelector('#tbodyProducts');

form?.addEventListener('submit',function (e){
    e.preventDefault();
    const data = {
        recipeId:0,
        price:parseInt(e.target.price.value),
        creationDate:null,
        isActive:true,
        name:e.target.name.value,
        recipeProductsDto:[]
    }
    console.log(data.price)
    tbodyProducts.querySelectorAll('tr').forEach((row)=>{
        const input = row.querySelector('input[type="number"]');
        const product = {
            id: 0,
            unitOfMeasure: "",
            quantity: parseInt(input.value),
            productId: parseInt(input.dataset.productId),
            price:null,
            productName: ""
        };
        data.recipeProductsDto.push(product);

    })

    sendData(data);
})

function addProductToTable(checkbox) {
    const name = checkbox.dataset.name;
    const stock = checkbox.dataset.stock;
    const productId = checkbox.id;
    addToRow(name, parseInt(stock), parseInt(productId));
    checkbox.disabled = true;
}

function addToRow(name, stock, productId) {
    const row = document.createElement('tr');
    console.log(productId)
    row.innerHTML = `
        <td><input type="text" class="form-control" name="${name}" value="${name}" required></td>
        <td><input type="number" class="form-control" name="${name}" min="1" value="1" required max="${stock}" id="input-${productId}" data-product-id="${productId}"></td>
        <td><button type="button" class="btn btn-danger btn-sm" data-product-id="${productId}" onclick="removeRow(this)">Eliminar</button></td>
    `;
    tbodyProducts?.appendChild(row);
    document.getElementById(`input-${productId}`).focus();
}
function removeRow(button) {
    const productId = button.dataset.productId;
    button.closest('tr').remove();
    const checkbox = document.getElementById(productId);
    checkbox.disabled = false;
    checkbox.checked = false;
}

function sendData(data){
    fetch(
        'http://localhost:8000/dashboard/recipes/create-recipe',
        {
            method:'POST',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(data)
        }
    )
    .then(res=>{
        if(!res.ok){
            throw new Error(`Error ${res.status}`)
        }
        return res.json();

    }).then(data=>{
        window.location.href = '.';

    }).catch(error=>{
        console.log(error);
    })
}