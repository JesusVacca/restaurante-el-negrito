const form = document.querySelector('#form-create-menu');
const tbodyRecipe = document.querySelector('#tbodyRecipe');


form?.addEventListener("submit",function (e) {
    e.preventDefault();
    const data = {
        menuId:0,
        name:e.target.name.value,
        creationDate:null,
        recipes:[]
    };
    const row = tbodyRecipe.querySelectorAll('tr');
    row?.forEach(row=>{
        const recipe = {
            recipeId:parseInt(row.dataset.recipeId),
            name:null,
            creationDate:null,
            isActive:null,
            price:null,
            recipeProductsDto:null
        }
        data.recipes.push(recipe);
    })
    fetch(
        "http://localhost:8000/dashboard/menu/create-menu",
        {
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        }
    ).then(res=>{
        if(!res.ok){
            res.text()
                .then(error=>{
                    console.log(error)
                })
            throw new Error(`Error => status: ${res.status}`);
        }
        return res.json();
    })
    .then(data=>{
        window.location.href = './'
    })
    .catch(error=>{
        console.error(error)
    })
})

function addRecipeToTable(checkbox) {
    const name = checkbox.dataset.name;
    const price = checkbox.dataset.price;
    const id = checkbox.id;
    checkbox.disabled = true;
    addToRowToTableRecipe(name, parseInt(price), parseInt(id));
}

function addToRowToTableRecipe(name, price, id) {
    const row = document.createElement('tr');
    row.setAttribute("data-recipe-id",id);
    row.innerHTML = `
        <td><input type="text" class="form-control" name="${name}" value="${name}" readonly></td>
        <td><input type="number" class="form-control" value="${price}" readonly id="input-${id}"></td>
        <td><button type="button" class="btn btn-danger btn-sm" data-recipe-id="${id}" onclick="removeRow(this)">Eliminar</button></td>
    `;
    tbodyRecipe?.appendChild(row);
    document.getElementById(`input-${id}`).focus();
}
function removeRow(button) {
    const recipeId = button.dataset.recipeId;
    button.closest('tr').remove();
    const checkbox = document.getElementById(recipeId);
    checkbox.disabled = false;
    checkbox.checked = false;
}

