
window.onload= function () {
   let priceShow = document.getElementsByClassName('priceShow');

   // function viewPrice() {
   //    let buttonId = priceShow.getAttribute("id");
   //    console.log("buttonId : " + buttonId)
   //    let button = document.getElementById(buttonId);
   //    console.log("button : " + button)
   //    console.log("button.parent : " + button.parentElement)
   //    console.log("button.parentElement.firstChild : " +button.parentElement.firstChild)
   //    let priceDiv = button.parentElement.firstChild;
   //
   //    priceDiv.style.display = 'block';
   // }

   console.log(priceShow.length)
   for (let priceShowElement of priceShow) {
      let myItemId = priceShowElement.getAttribute("myItemId");
      priceShowElement.addEventListener("mouseover",function () {
         document.getElementById(myItemId+"hoverPriceDiv").style.display = 'block';
      })

      priceShowElement.addEventListener("mouseout",function () {
         document.getElementById(myItemId+"hoverPriceDiv").style.display = 'none';
      })
   }
}
