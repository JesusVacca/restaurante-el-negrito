window.addEventListener('DOMContentLoaded',function(event){
    const $element =(elementId)=> this.document.getElementById(elementId);
    const buttonMenu = $element('menu');
    const sidebar = $element('sidebar');
    const root = $element('root');

    buttonMenu.addEventListener('click',function(e){
        sidebar.classList.toggle('open');
    })

    root.addEventListener("scroll", (e) => {
        const scrollCurrent = (100 * root.scrollTop) / (root.scrollHeight - root.clientHeight);
        if(scrollCurrent >= 10){
            if(sidebar.classList.contains('open')){sidebar.classList.remove('open')}
        }
    });
})