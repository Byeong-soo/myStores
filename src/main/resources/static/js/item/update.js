window.onload = function () {

    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()


    let backButton = document.getElementsByName("backButton");

    for (let backButtonElement of backButton) {
        backButtonElement.onclick = function () {
            window.history.back();
        }
    }

    let deleteButton = document.getElementById("itemDelete");

    deleteButton.onclick = function () {
        if(window.confirm("삭제하시겠습니까? 지워진 데이터는 복구할 수 없습니다.")){
           deleteButton.parentNode.parentNode.submit();
        }
    }


}

