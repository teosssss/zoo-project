window.addEventListener("DOMContentLoaded",()=>{
    var navbarShrink = function () {
        const navFixed = document.body.querySelector('.fixed');
        if (!navFixed) {
            return;
        }
        if (window.scrollY === 0) {
            navFixed.classList.remove('bg')
        } else {
            navFixed.classList.add('bg')
        }
    
    };
    
    // Shrink the navbar 
    navbarShrink();
    
    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);
})


