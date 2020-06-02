var stompClient = null;
var inAMatch = false;

// function setConnected(connected) {
//     // $("#connect").prop("disabled", connected);
//     // $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#userinfo").html("");
// }

function connect() {
    const socket = new SockJS('/starting-connection');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/findusers/userNameAndHostInfoResponse', function(resp) {
            let jsonObject = JSON.parse(resp.body);
            console.log(jsonObject['okay'])
        });
        stompClient.subscribe('/findusers/readytoplayresponse', function(resp) {
            let jsonObject = JSON.parse(resp.body);
            console.log(jsonObject['okay'])
        });
        stompClient.subscribe('/user/queue/matchplayers', function(resp) {
            if(inAMatch === true)
                return;
            inAMatch = true;
            let clients = JSON.parse(resp.body)['matchedClientList'];
            console.log(clients)
            clients.forEach(function(client) {
                showMessage(client['name']);
            });
        });
    });
}

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }

function sendName() {
    stompClient.send("/userNameAndHostInfo", {},
        JSON.stringify({'name': $("#name").val(), 'hostInfo':document.location.host}));
    $("#send").prop("disabled", true);
    $("#ready").prop("disabled", false);
}

function sendReadyToPlayStatus() {
    stompClient.send("/readytoplay", {}, JSON.stringify({'readyToPlay': true}));
    $("#ready").prop("disabled", true);
}

function showMessage(message) { $("#userinfo").append("<tr><td>" + message + "</td></tr>"); }

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    // $( "#connect" ).click(function() { connect(); });
    // $( "#disconnect" ).click(function() { disconnect(); });
    $("#send").click(function() { sendName(); });
    $("#ready").click(function() { sendReadyToPlayStatus(); });
    $("#ready").prop("disabled", true);
    connect();
});