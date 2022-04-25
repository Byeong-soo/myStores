window.onload = function() {

    let inputAlert = document.getElementById("inputAlert");
    let closeBtn = document.getElementById('closeBtn');

    closeBtn.onclick = function () {
        fadeAlert();
    }

    function fadeAlert() {
        inputAlert.classList.remove("show");
    }

    function getValue(inputId) {
        return document.getElementById(inputId).value;
    }

    function getButtonChecked() {
        return document.querySelector('input[name="btnradio"]:checked').value;
    }

    function checkNull() {

        let inputList = ['goldMarketValue','goldMount','goldMargin','goldWage']
        let alertList = [];

        inputList.forEach(function (value) {
            let inputValue = getValue(value);

            switch (value){
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
            if(inputValue === ''){
                alertList.push(value);
            }
        })


        let alertString = alertList.join(',');

        if(alertList.length >0) {
            inputAlert.classList.add('show');
            let alertText = document.getElementById("alertText");
            alertText.textContent = alertString + "을(를) 입력해주세요";
            return false
        }
    }


    let calculateButton = document.getElementById("calculate");

    calculateButton.onclick = function () {
        let valueCheck = checkNull();
        if(valueCheck === false) return

        let goldKind = getButtonChecked();

    }

    function createAlert(position) {
        let alertDiv = document.createElement('div');
        alertDiv.setAttribute("class", "alert alert-warning d-flex align-items-center alert-dismissible");
        alertDiv.setAttribute("role", "alert");
        alertDiv.setAttribute("id", "alertDiv");

        let alertSvg = document.createElementNS('http://www.w3.org/2000/svg','svg');
        let alertUse = document.createElementNS('http://www.w3.org/2000/svg','use');
        alertUse.setAttributeNS('https://www.w3.org/2000/xlink',"href","#exclamation-triangle-fill");
        // let alertUse = document.createElement('use');
        alertSvg.setAttribute("class", "bi flex-shrink-0 me-2");
        alertSvg.setAttribute("width", "24");
        alertSvg.setAttribute("height", "24");
        alertSvg.setAttribute("role", "img");
        alertSvg.setAttribute("aria-label", "Warning:");

        alertSvg.appendChild(alertUse);

        // let alertPicture = document.getElementById("exclamation-triangle-fill");

        // alertSvg.setAttribute("fill","currentColor");
        // alertSvg.setAttribute("viewBox","0 0 16 16");
        //
        // let alertPath = document.createElement('path');
        // alertPath.setAttribute("d","M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z");

        // let alertUse = document.createElement('use');
        // alertUse.setAttribute("xlink:href", "");
        // alertUse.setAttributeNS('xlink:href', "#exclamation-triangle-fill");

        let alertSpan = document.createElement('span');
        alertSpan.setAttribute("id", "alertText");

        let alertButton = document.createElement('button');
        alertButton.setAttribute("type", 'button');
        alertButton.setAttribute("class", "btn-close");
        alertButton.setAttribute("data-bs-dismiss", "alert");
        alertButton.setAttribute("aria-label", "Close")

        alertDiv.appendChild(alertSvg);
        // alertSvg.appendChild(alertUse);
        alertDiv.appendChild(alertSpan);
        alertDiv.appendChild(alertButton);

        position.appendChild(alertDiv);
    }
}