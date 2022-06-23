
window.onload= function () {

   let priceShow = document.getElementsByClassName('priceShow');
   let priceModal = new bootstrap.Modal(document.getElementById('priceModal'), {
      backdrop: false
   })

   let priceModalLabel = document.getElementById("priceModalLabel");
   let modelId = document.getElementById("modelId");
   let basicWage = document.getElementById("basicWage");
   let mainWage = document.getElementById("mainWage");
   let supportWage = document.getElementById("supportWage");
   let margin = document.getElementById("margin");

   for (let priceShowElement of priceShow) {
      let myItemId = priceShowElement.getAttribute("myItemId");
      priceShowElement.addEventListener("mouseover",function () {
         document.getElementById(myItemId+"hoverPriceDiv").style.display = 'block';
      })

      priceShowElement.addEventListener("mouseout",function () {
         document.getElementById(myItemId+"hoverPriceDiv").style.display = 'none';
      })

      priceShowElement.addEventListener("click",function () {
         getItemInfo(myItemId).then(setInput);
      })




   }

   let priceChangeButton = document.getElementById("priceChangeButton");
   priceChangeButton.addEventListener("click",function () {
      let modelId = document.getElementById("modelId").value;

      //XMLHttpRequest 객체 생성
      let xhr = new XMLHttpRequest();

      //요청을 보낼 방식, url, 비동기여부 설정
      xhr.open('put', '/api/item/'+modelId, true);

      //HTTP 요청 헤더 설정
      xhr.setRequestHeader('Content-type', 'application/json');

      let data = {
         "basicWage":basicWage.value,
         "mainWage":mainWage.value,
         "supportWage":supportWage.value,
         "margin":margin.value}

      //요청 전송
      xhr.send(JSON.stringify(data));

      //Callback
      xhr.onload = () => {
         if (xhr.status == 200) {
            priceModal.hide();
            window.location.reload();
         } else {
            alert("오류! 개발자에게 문의해주세요")
         }
      }

   })


   let copyPhrases = document.getElementsByClassName('copyPhrases');

   for (let copyPhrase of copyPhrases) {
      copyPhrase.addEventListener("click",function () {
         let myItemId = copyPhrase.getAttribute("myItemIdCopy");
         getItemInfoAndGoldPrice(myItemId).then(copy);
      })
   }



   function getItemInfo(myItemId) {
      return new Promise(function (resolve, reject){

         //XMLHttpRequest 객체 생성
         let xhr = new XMLHttpRequest();
         let response;

         //요청을 보낼 방식, url, 비동기여부 설정
         xhr.open('GET', '/api/item/'+myItemId, true);

         //요청 전송
         xhr.send();

         //Callback
         xhr.onload = () => {
            if (xhr.status == 200) {
               response = xhr.response;
               resolve(response);
            } else {
               alert("오류! 개발자에게 문의해주세요")
               reject(false);
            }

         }
      })
   }


   function getGoldPrice() {
      return new Promise(function (resolve, reject){

         //XMLHttpRequest 객체 생성
         let xhr = new XMLHttpRequest();
         let response;

         //요청을 보낼 방식, url, 비동기여부 설정
         xhr.open('GET', '/api/item/goldPrice', true);

         //요청 전송
         xhr.send();

         //Callback
         xhr.onload = () => {
            if (xhr.status == 200) {
               response = xhr.response;
               resolve(response);
            } else {
               alert("오류! 개발자에게 문의해주세요")
               reject(false);
            }

         }
      })
   }

    function setInput(itemInfo) {

      modelId.value = JSON.parse(itemInfo).id;
      priceModalLabel.innerHTML = JSON.parse(itemInfo).modelNumber;
      basicWage.value = JSON.parse(itemInfo).basicWage;
      mainWage.value = JSON.parse(itemInfo).mainWage;
      supportWage.value = JSON.parse(itemInfo).supportWage;
      margin.value = JSON.parse(itemInfo).margin;

      priceModal.show();
   }

  async function getItemInfoAndGoldPrice(myItemId) {
     let itemInfo = await getItemInfo(myItemId);
     let goldPrice = await getGoldPrice();

     let result = {"item":itemInfo,"goldPrice":goldPrice}
     return result;
   }

   function copy(result) {
      let goldPrice = result.goldPrice;
      let itemInfo = result.item;
      let mount = JSON.parse(itemInfo).basicMount;
      let sum = JSON.parse(itemInfo).sum;
      let margin = JSON.parse(itemInfo).margin;


      let text = `문의하신 제품 가격 입니다!
                  14K 기준 ${mount} g/돈 기준
                  카드 혜택가 ${(goldPrice*mount + sum + margin)*1.14}  금교환 등 최대 할인 ${goldPrice*mount + sum + margin}
                  
                  18K 기준 ${mount*1.13} g/돈 기준
                  카드 혜택가 ${(goldPrice*mount*1.15 + sum + margin)*1.14}  금교환 등 최대 할인 ${goldPrice*mount*1.15 + sum + margin}`


      navigator.clipboard.writeText(text).then(()=>alert("메세지가 복사되었습니다."))
   }

}
