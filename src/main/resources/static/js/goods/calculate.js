window.onload = function () {

    let alertPlaceholder = document.getElementById('alertPosition')

    function alert(message, type) {
        let wrapper = document.createElement('div')
        wrapper.innerHTML = '<div class="alert alert-' + type + ' d-flex align-items-center alert-dismissible" role="alert" id="inputAlert">   <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">\n' +
            '    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>\n' +
            '  </svg>'
            + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'

        alertPlaceholder.append(wrapper)
    }

    function findInput(inputId) {
        return document.getElementById(inputId);
    }

    function getValue(inputId) {
        return findInput(inputId).value;
    }

    function getButtonChecked() {
        return document.querySelector('input[name="btnradio"]:checked').value;
    }

    function checkNull() {

        let inputList = ['goldMarketValue', 'goldMount', 'goldMargin', 'goldWage']
        let alertList = [];
        let valueList = [];

        inputList.forEach(function (value) {
            let inputValue = getValue(value);

            switch (value) {
                case 'goldMarketValue' :
                    value = '시세';
                    break;
                case 'goldMount' :
                    value = '중량';
                    break;
                case 'goldMargin' :
                    value = '마진';
                    break;
                case 'goldWage' :
                    value = '공임';
                    break;
            }
            if (inputValue === '') {
                alertList.push(value);
            }

            if (inputValue !== '') {
                valueList.push(parseFloat(inputValue));
            }
        })


        let alertString = alertList.join(',');
        let inputAlert = document.getElementById('inputAlert');
        if (inputAlert !== null) inputAlert.remove();

        if (alertList.length > 0) {
            alert(alertString + "을(를) 입력해주세요!", 'warning')
            return {'alertList': alertList, 'check': false}
        }

        if (valueList.length > 0) {
            return {'valueList': valueList, 'check': true}
        }
    }


    let calculateButton = document.getElementById("calculate");

    calculateButton.onclick = function () {
        let valueCheck = checkNull();
        if (valueCheck.check === false) return

        let goldKind = getButtonChecked();
        let goldMarket = valueCheck.valueList[0];
        let goldMount = valueCheck.valueList[1];
        let goldWage = valueCheck.valueList[2];
        let goldMargin = valueCheck.valueList[3];

        let totalPrice = calculate(goldKind, goldMarket, goldMount, goldWage, goldMargin);

        let totalPriceInput = document.getElementById('totalPrice');
        totalPriceInput.value = totalPrice.toLocaleString("ko-KR");

    }

    function calculate(goldKind, goldMarket, goldMount, goldWage, goldMargin) {

        let goldRatio = 0;

        switch (goldKind) {
            case '14':
                goldRatio = 0.6435;
                break;
            case '18':
                goldRatio = 0.825;
                break;
            case '24':
                goldRatio = 1;
                break;
        }

        let goldPrice = (goldMarket / 3.75 * goldRatio * goldMount) + goldWage + goldMargin;

        return Math.ceil(goldPrice);
    }

    function numberCheck(value) {
        return value.replace(/[^.,0-9]/g, '');
    }

    function pointCheck(ev,value) {
        if (ev.key === '.' && value.includes('.')) {
            ev.defaultPrevented;
            ev.preventDefault();
            return false;
        }
    }

    findInput("goldMarketValue").addEventListener("keydown", function (ev) {
        let value = getValue("goldMarketValue");
        pointCheck(ev,value);

        if(ev.key === ','){
            ev.preventDefault();
            return false;
        }

        let numberCheck1 = numberCheck(value);
        findInput("goldMarketValue").value = numberCheck1;
    })

    findInput("goldMarketValue").addEventListener("focusout", function () {
        let value = getValue("goldMarketValue");

        let numberCheck1 = numberCheck(value);
        findInput("goldMarketValue").value = numberCheck1;
    })


    findInput('goldWage').addEventListener('focusin', function () {
        findInput('goldWage').value = "200,000"
    })

    findInput('goldWage').addEventListener('focusout', function () {
        findInput('goldWage').setAttribute("type", "text");
    })


}