package com.caimao.socketclient.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.caimao.socketclient.hq.HQController;

/**
 * Created by ppz on 2016/8/23.
 */
public class SnifferBroadcast extends BroadcastReceiver {
    public static final String NETWORK_CONNECT_ACTION = ConnectivityManager.CONNECTIVITY_ACTION;
    private NetworkInfo.State wifiState,mobileState;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NETWORK_CONNECT_ACTION.equals(intent.getAction())){
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            HQController instance = HQController.getInstance(context);

            if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
                Toast.makeText(context, "手机网络连接成功！", Toast.LENGTH_SHORT).show();
            } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                Toast.makeText(context, "无线网络连接成功！", Toast.LENGTH_SHORT).show();
                instance.connectSocket();
            } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                Toast.makeText(context, "手机没有任何网络...", Toast.LENGTH_SHORT).show();
                instance.disConnectSocket();
            }
        }

    }
}
