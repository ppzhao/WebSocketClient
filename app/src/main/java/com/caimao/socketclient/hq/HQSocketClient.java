package com.caimao.socketclient.hq;


import android.util.Log;

import com.caimao.android.socket.client.WebSocketClient;
import com.caimao.android.socket.drafts.Draft;
import com.caimao.android.socket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by ppz on 2016/8/17.
 */
public class HQSocketClient extends WebSocketClient {
    private SocketListener socketListener;

    public HQSocketClient(URI serverUri, Draft draft, SocketListener listener) {
        super(serverUri, draft);
        socketListener = listener;
    }

    public HQSocketClient(URI serverUri,SocketListener listener) {
        super(serverUri);
        socketListener = listener;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("new connection opened");
        socketListener.onOpen(handshakedata);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
        Log.e("SOCKET","SOCKET MESSAGE: "+message);
        socketListener.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
        socketListener.onClose(code,reason,remote);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
        socketListener.onError(ex);
    }
}
