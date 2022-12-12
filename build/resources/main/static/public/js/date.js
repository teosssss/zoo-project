const dateControl = document.querySelector('input[type="datetime-local"]');
dateControl.min = new Date().toLocaleDateString('en-ca')
dateControl.max= nextweek()

function nextweek(){
    var today = new Date();
    return new Date(today.getFullYear(), today.getMonth(), today.getDate() + 7);
}

