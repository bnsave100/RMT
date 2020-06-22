// web console

var ws;
var xterm;


// init websocket
const initWs = () => {
    let sec = location.protocol.indexOf("https") > -1
    if (sec) {
        ws = new WebSocket('wss://' + location.host + '/terminal')
    } else {
        ws = new WebSocket('ws://' + location.host + '/terminal')
    }
}

const initXterm = (cols, rows) => {
    xterm = new Terminal({
        cols: cols,
        rows: rows,
        screenReaderMode: true,
        rendererType: 'canvas',
        convertEol: true,
        cursorBlink: true,
        cursorStyle: "block"
    });
    xterm.open(document.getElementById('#terminal'), true);
}

const sendProxy = (message) => {
    if (ws.readyState === WebSocket.CONNECTING) {
        xterm.showOverlay("WebSocket Connecting.")
        return;
    }
    if (ws.readyState === WebSocket.CLOSING || ws.readyState === WebSocket.CLOSED) {
        xterm.showOverlay("WebSocket Connection Closed.")
        return;
    }
    ws.send(message)
}

const resize = () => {
    let {cols, rows} = getTerminalSize()
    sendProxy(action('TERMINAL_RESIZE', {cols, rows}))
    xterm.showOverlay(`${cols} x ${rows}`)
}

const initConnect = () => {
    // 1. init terminal
    let {cols, rows} = getTerminalSize()
    initXterm(cols, rows)
    // 2. init ws
    try {
        initWs()
        setTimeout(() => {
            xterm.showOverlay("WebSocket Connect Success.")
        }, 2000)
    } catch (e) {
        setTimeout(() => {
            xterm.showOverlay("WebSocket Connect Failed.")
        }, 2000)
    }
}


// 主动心跳♥
let retries = 10
const heartbeat = () => {
    const heartbeatInterval = setInterval(() => {
        if (ws.readyState !== WebSocket.OPEN) {
            if (retries < 1) {
                xterm.showOverlay("Reconnect Up Max Count,Please Check Your Service.")
                clearInterval(heartbeatInterval);
                return;
            }
            retries -= 1;
            initConnect();
        } else {
            sendProxy(action('TERMINAL_HEARTBEAT', {}))
            retries = 10;
        }
    }, 5000)
}
// -----------

$(() => {
    initConnect()

    ws.onopen = (event) => {
        xterm.toggleFullscreen(true);
        xterm.on('data', command => {
            sendProxy(
                action('TERMINAL_COMMAND', {
                    command,
                })
            );
        });

        // 3. send resize request
        resize();
        // 4.register  resize listener
        window.addEventListener("resize", function (event) {
            resize()
        })

        // heartbeat
        // heartbeat();
    }

    ws.onmessage = (event) => {
        let data = JSON.parse(event.data);
        switch (data.type) {
            case 'TERMINAL_PRINT':
                xterm.write(data.text);
                break
            case 'TERMINAL_HEARTBEAT':
                console.log(data.text) // ok
                break
            default:
                break
        }
    };

    ws.onerror = (event) => {
        console.log(event);
    };

    ws.onclose = (event) => {
        console.log("close==>");
        // term.destroy()
        // sendProxy(action("TERMINAL_CLOSE", ""));
    };
})


const action = (type, data) =>
    JSON.stringify({
        type,
        ...data,
    });

function getCharSize() {
    const tempDiv = $('<div />').attr({'role': 'listitem'});
    const tempSpan = $('<div />').html('qwertyuiopasdfghjklzxcvbnm');
    tempDiv.append(tempSpan);
    $("html body").append(tempDiv);
    const size = {
        width: tempSpan.outerWidth() / 26,
        height: tempSpan.outerHeight(),
        left: tempDiv.outerWidth() - tempSpan.outerWidth(),
        top: tempDiv.outerHeight() - tempSpan.outerHeight(),
    };
    tempDiv.remove();
    return size;
}

function getWindowSize() {
    let e = window;
    let a = 'inner';
    if (!('innerWidth' in window)) {
        a = 'client';
        e = document.documentElement || document.body;
    }
    const terminalDiv = document.getElementById("terminal");
    const terminalDivRect = terminalDiv.getBoundingClientRect();
    return {
        width: terminalDivRect.width,
        height: e[a + 'Height'] - terminalDivRect.top
    };
}

function getTerminalSize() {
    const charSize = getCharSize();
    const windowSize = getWindowSize();
    console.log('charsize');
    console.log(charSize);
    console.log('windowSize');
    console.log(windowSize);
    return {
        cols: Math.floor((windowSize.width - charSize.left) / 10),
        rows: Math.floor((windowSize.height - charSize.top) / 25)
    };
}