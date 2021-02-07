var stompClient = null;

function connect() {
    var socket = new SockJS('/webSocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function() {
        stompClient.subscribe('/topic/message', function (message) {
            console.log("Subscribing");
            console.log(message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
    stompClient.disconnect();
    stompClient = null;
    }
    console.log("Disconnect");
}

function sendEvent(){
    if (stompClient != null) {
    stompClient.send("/app/like", {}, "Hello, STOMP");
    }
}
