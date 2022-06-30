window.onload = function () {

    let alertPlaceholder = document.getElementById('alertPosition')

    let priceTable = document.getElementById('priceTable');

    let price14kInput = document.getElementById('14kPrice');
    let price14kStoreInput = document.getElementById('14kStorePrice');
    let mount14kInput = document.getElementById('14kMount');
    let mount14kInput2 = document.getElementById('14kMount2');

    let price18kInput = document.getElementById('18kPrice');
    let price18kStoreInput = document.getElementById('18kStorePrice');
    let mount18kInput = document.getElementById('18kMount');
    let mount18kInput2 = document.getElementById('18kMount2');

    let price24kInput = document.getElementById('24kPrice');
    let price24kStoreInput = document.getElementById('24kStorePrice');
    let mount24kInput = document.getElementById('24kMount');
    let mount24kInput2 = document.getElementById('24kMount2');

    let tr14k = document.getElementById('14kTr');
    let tr18k = document.getElementById('18kTr');
    let tr24k = document.getElementById('24kTr');



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

        let inputList = ['goldMarketValue', 'goldMountValue', 'goldMarginValue', 'goldWageValue']
        let alertList = [];
        let valueList = [];

        inputList.forEach(function (value) {
            let inputValue = getValue(value).replaceAll(',', '');

            switch (value) {
                case 'goldMarketValue' :
                    value = '시세';
                    break;
                case 'goldMountValue' :
                    value = '중량';
                    break;
                case 'goldMarginValue' :
                    value = '마진';
                    break;
                case 'goldWageValue' :
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


    //--------------------계산 ----------------------

    let calculateButton = document.getElementById("calculate");

    calculateButton.onclick = function () {
        let valueCheck = checkNull();
        if (valueCheck.check === false) return

        let goldKind = getButtonChecked();
        let goldMarket = valueCheck.valueList[0];
        let goldMount = valueCheck.valueList[1];
        let goldWage = valueCheck.valueList[2];
        let goldMargin = valueCheck.valueList[3];

        let standardPrice = calculate(goldKind, goldMarket, goldMount, goldWage, goldMargin);
        let standardStorePrice = 0;
        let convertPrice = 0;
        let convertStorePrice = 0;




        priceTable.classList.remove('d-none');
        priceTable.classList.add('d-flex');

        if (goldKind === '14') {

            convertPrice = calculate('18', goldMarket, goldMount * 1.15, goldWage, goldMargin);
            standardStorePrice = storePriceCalculate(standardPrice);
            convertStorePrice = storePriceCalculate(convertPrice);

            tr14k.style.display = 'table-row';
            tr18k.style.display = 'table-row';
            tr24k.style.display = 'none';

            price14kInput.textContent = standardPrice.toLocaleString("ko-KR");
            price14kStoreInput.textContent = standardStorePrice.toLocaleString("ko-KR");
            mount14kInput.textContent = goldMount;
            mount14kInput2.textContent = (Math.ceil((goldMount/3.75) * 100)/100).toString();

            price18kInput.textContent = convertPrice.toLocaleString("ko-KR");
            price18kStoreInput.textContent = convertStorePrice.toLocaleString("ko-KR");
            mount18kInput.textContent =(Math.ceil(goldMount * 1.15 * 100)/100).toString();
            mount18kInput2.textContent =(Math.ceil((goldMount * 1.15/3.75) * 100)/100).toString();
        }

        if (goldKind === '18') {

            convertPrice = calculate('14', goldMarket, goldMount / 1.15, goldWage, goldMargin);
            standardStorePrice = storePriceCalculate(standardPrice);
            convertStorePrice = storePriceCalculate(convertPrice);

            tr14k.style.display = 'table-row';
            tr18k.style.display = 'table-row';
            tr24k.style.display = 'none';

            price14kInput.textContent = convertPrice.toLocaleString("ko-KR");
            price14kStoreInput.textContent = convertStorePrice.toLocaleString("ko-KR");
            mount14kInput.textContent = (Math.ceil(goldMount/1.15*100)/100).toString();
            mount14kInput2.textContent = (Math.ceil((goldMount/1.15/3.75)*100)/100).toString();

            price18kInput.textContent = standardPrice.toLocaleString("ko-KR");
            price18kStoreInput.textContent = standardStorePrice.toLocaleString("ko-KR");
            mount18kInput.textContent = goldMount;
            mount18kInput2.textContent = (Math.ceil((goldMount/3.75)*100)/100).toString();
        }

        if (goldKind === '24') {

            standardStorePrice = storePriceCalculate(standardPrice);

            tr14k.style.display = 'none';
            tr18k.style.display = 'none';
            tr24k.style.display = 'table-row';

            price24kInput.textContent = standardPrice.toLocaleString("ko-KR");
            price24kStoreInput.textContent = standardStorePrice.toLocaleString("ko-KR");
            mount24kInput.textContent = goldMount;
            mount24kInput2.textContent = (Math.ceil((goldMount/3.75) * 100)/100).toString();
        }


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

        return Math.ceil((goldPrice/100))*100;
    }

    function storePriceCalculate(price) {
        if(price>=1000000){
            return Math.ceil(price * 1.15/100)*100;
        }
        return Math.ceil(price * 1.14/100)*100;
    }

    //--------------------입력 제한 ----------------------
    function inputControl(inputId) {
        findInput(inputId).addEventListener("focusin", function (ev) {
            let value = getValue(inputId);
            pointCheck(ev, value);
            if (ev.key === ',') {
                ev.preventDefault();
                return false;
            }

            findInput(inputId).value = value.replaceAll(',', '');
            findInput(inputId).setAttribute('type', 'number');
        })

        findInput(inputId).addEventListener("focusout", function (ev) {
            let value = getValue(inputId);
            pointCheck(ev, value);

            let numberCheckValue = numberCheck(value);
            findInput(inputId).setAttribute('type', 'text');
            findInput(inputId).value = numberCheckValue;
        })
    }


    function numberCheck(value) {
        value = value.replaceAll(',', '');
        value = value.replace(/[^.,0-9]/g, '');
        let splitValue = value.split('.');
        splitValue[0] = splitValue[0].replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        return splitValue.join('.')
    }

    function pointCheck(ev, value) {
        if (ev.key === '.' && value.includes('.')) {
            ev.defaultPrevented;
            ev.preventDefault();
            return false;
        }
    }

    inputControl('goldMarketValue');
    inputControl('goldMountValue');
    inputControl('goldWageValue');
    inputControl('goldMarginValue');

    // 초기화

    document.getElementById('reset').addEventListener('click', function () {
        resetInput('goldMountValue');
        resetInput('goldWageValue');
        resetInput('goldMarginValue');

        priceTable.classList.remove('d-flex');
        priceTable.classList.add('d-none');
    })

    function resetInput(inputId) {
        findInput(inputId).value = '';
    }

}

