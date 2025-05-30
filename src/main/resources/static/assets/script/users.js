
const table = document.querySelector("#tablaRoles");
const tbody = table.querySelector("#tbodyRoles");
const form = document.querySelector("#create-member");

form?.addEventListener('submit',function (e) {
    e.preventDefault();
    const data = {
        member:{
            id: null,
            name: e.target.name.value,
            email: e.target.email.value,
            phone: e.target.phone.value,
            address: e.target.address.value,
            password: e.target.password.value
        },
        roles:[]
    }
    const rows = tbody.querySelectorAll("tr");
    if(rows.length === 0){
        alert("Debes seleccionar almenos un rol");
        return;
    }
    rows.forEach((row)=>{
            const rol = row.dataset.rolName;
            data.roles.push(rol);
    })
    sendData(data);
})

function sendData(data) {
    fetch('/dashboard/users/create-user',{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(data)
    })
    .then(res=>{
        if (!res.ok){
            res.text()
                .then(errorText=>{
                    const error = document.getElementById('error-alert');
                    error.innerHTML = `
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Uup!</strong> ${errorText}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                          </div>
                    `;
                    throw new Error(`Error status: ${res.status}`);
                })
        }
        return res.json();
    })
    .then(data=>{
        window.location.href = '/dashboard/users';
    })
    .catch(error=>{console.error('Error',error)})
}
function addElementToTable(checkbox) {
    const name = checkbox.dataset.name;
    checkbox.disabled = true;
    addRowToTable(name);
}

function addRowToTable(name) {
    if (!name)return;
    const row = document.createElement('tr');
    row.setAttribute('data-rol-name',name);
    row.innerHTML = `
        <td>${name}</td>
        <td><button type="button" class="btn btn-danger" data-name="${name}" onclick="removeRowToTable(this)">Eliminar</button></td>
    `;
    tbody.append(row);
}

function removeRowToTable(button) {
    const id = button.dataset.name;
    const checkbox = document.getElementById(id);
    checkbox.disabled = false;
    checkbox.checked = false;
    button.closest('tr').remove();
}

