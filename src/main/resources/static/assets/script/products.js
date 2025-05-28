const formCreate = document.getElementById('create-product');
const loading = document.getElementById('loading');
formCreate.addEventListener('submit',function (e) {
    e.preventDefault();
    if(loading){loading.style.display='flex';}
    const data = {
        id: 0,
        name: e.target.name.value,
        description: e.target.description.value,
        unitOfMeasure: e.target.unitOfMeasure.value,
        category:e.target.category.value,
        quantity: parseInt(e.target.quantity.value),
        price: parseInt(e.target.price.value),
        creationDate: null,
        modificationDate: null,
        imageUrl: "",
        status: e.target.status.value
    }
    console.log(data)
    const formData = new FormData();
    formData.append('product',JSON.stringify(data));
    formData.append('image',e.target.imageUrl.files[0])
    fetch('http://localhost:8000/dashboard/products/create-product',
        {
            method:'POST',
            body:formData
        }
    )
        .then(response=>{
            if(!response.ok){
                const error = document.getElementById('error-alert');
                return response.text()
                    .then(errorText=>{
                        console.error('Error -> ',errorText);
                        error.innerHTML = `
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Uup!</strong> ${errorText}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                          </div>
                        `;
                    })
                    .catch(error=>{
                        console.log('Error reading the response body ',error);
                    })
            }
            return response.json()
        })
        .then(data=>{
            window.location.href='/dashboard/products/';
        })
        .catch(error=>{
            console.error(error)
        })
        .finally(()=>{
            if (loading){loading.style.display = 'none';}
        })

})