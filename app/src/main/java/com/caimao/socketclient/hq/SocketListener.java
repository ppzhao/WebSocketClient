package com.caimao.socketclient.hq;

import com.caimao.android.socket.handshake.ServerHandshake;

/**
 * Created by ppz on 2016/8/17.
 */
public interface SocketListener {
    public void onMessage(String message);
    public void onOpen(ServerHandshake handshakedata);
    public void onClose(int code, String reason, boolean remote);
    public void onError(Exception ex);
}
