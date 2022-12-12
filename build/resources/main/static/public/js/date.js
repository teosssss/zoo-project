const dateControl = document.querySelector('input[type="datetime-local"]');
dateControl.min = new Date().toLocaleDateString('en-ca')
