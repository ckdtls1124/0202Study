let data= {}; //sending data
let ws;  // WebSocket URL
let userId=document.querySelector('#userId');
let connectBtn=document.querySelector('#connectBtn');
let sendBtn=document.querySelector('#sendBtn');
let chatWindowCon=document.querySelector('.chatWindow-con');
let msg=document.querySelector('#msg');

let url="localhost:8099";

// Entering WebSocket

connectBtn.addEventListener("click", ()=>{

ws=new WebSocket("ws://"+url+"/chat");
    if(userId.value.length<=0 || userId.value==""){
        alert('Please write the user name');
    } 
    alert('Welcome, '+userId.value+". Nice to meet you.");

    ws.onmessage = function(msg){
        let data1=JSON.parse(msg.data);

        // create div
        let divTag=document.createElement('div');
        let className;
        if(data1.userId ==  userId.value){
            className = "yellow"
        } else {
            className = "white"
        }

        let item;
        item = "<div class='"+className+"'> <p><span>[writer]: "+ data1.userId+"</span><br/>";
        item += "<span> Text : "+ data1.msg + "</span></p></div>";

        divTag.innerHTML = item;

        chatWindowCon.append(divTag);


      
    }

});

  // Send message
  sendBtn.addEventListener("click", // Sending data 
  ()=>{
      // if(msg.value.length<=0 || msg.value==""){
      //     alert('There are no messages. Please type in messages.');
      //     msg.focus();
      //     return false;
      // }

      if(msg.value.trim().length>0){
          data.userId=userId.value;
          data.msg=msg.value; // messages
          data.date=new Date().toLocaleDateString(); // date and time

          let sendData=JSON.stringify(data);
          ws.send(sendData);

      }


  });
