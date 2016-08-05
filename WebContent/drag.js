function drag(id)
{
     var oDiv=document.getElementById(id);
    
     oDiv.onmousedown=function (ev)
     {
          var OreservationDiv=document.getElementById('reservationDiv');
          var oEvent=ev||event;
          var disX=oEvent.clientX-OreservationDiv.offsetLeft;
          var disY=oEvent.clientY-OreservationDiv.offsetTop;
         
          if(oDiv.setCapture)
          {
               oDiv.onmousemove=fnMove;
               oDiv.onmouseup=fnUp;
              
               oDiv.setCapture();
          }
          else
          {
               document.onmousemove=fnMove;
               document.onmouseup=fnUp;
          }
         
          function fnMove(ev)
          {
               var oEvent=ev||event;
              
               OreservationDiv.style.left=oEvent.clientX-disX+'px';
               OreservationDiv.style.top=oEvent.clientY-disY+'px';
          }
         
          function fnUp()
          {
               this.onmousemove=null;
               this.onmouseup=null;
              
               if(this.releaseCapture)
               {
                    this.releaseCapture();
               }
          }
         
          return false;
     };
};