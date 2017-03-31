package com.caimao.socketclient.hq;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.caimao.android.socket.drafts.Draft_17;
import com.caimao.android.socket.handshake.ServerHandshake;
import com.caimao.socketclient.entity.GjsMarketItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ppz on 2016/8/24.
 */
public class HQController implements SocketListener, Handler.Callback {
    private static HQController instance;
    private final Context mContext;
    private final Handler mHandler;
    private HQSocketClient hqSocketClient;
    private Timer heartTimer;
    private HashMap<String, JSONArray> subscribeMap = new HashMap<String,JSONArray>();//订阅集合记录
    private String SUB_TYPE = "subHq";
    private String SUB_CANCEL_TYPE = "subHq_cancel";
    private SocketMessageListener mMsgListener;

    public void initMsgReceiver(SocketMessageListener msgListener) {
        mMsgListener = msgListener;
    }

    public void endMsgReceiver(){
        if (mMsgListener!=null)
            mMsgListener = null;
            sendCancelSubscribe();
    }

    public interface SocketMessageListener{
        void responseMsg(String msg);
        void disConnect();
    }

    public HQController(Context context) {
        mContext = context;
        mHandler = new Handler(this);
        connectSocket();
    }

    public static HQController getInstance(Context context) {
        if (instance == null)
            instance = new HQController(context);
        return instance;
    }

    public void connectSocket() {
        try {
            if (hqSocketClient != null && hqSocketClient.isOpen()) {
                hqSocketClient.close();
            }
            //WebSocketClient不能重复使用,每次都需要重新创建Socket对象
//            hqSocketClient = new HQSocketClient(new URI("ws://10.202.1.110:8089/hqapi/ws"), new Draft_17(), this);
            hqSocketClient = new HQSocketClient(new URI("ws://172.32.2.204:8887/"), new Draft_17(), this);
            if (!hqSocketClient.isOpen()) {
                hqSocketClient.connect();
                Log.e("SOCKET", "连接==");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

//    public void syncConnectionSocket() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (!hqSocketClient.isOpen()) {
//                    try {
//                        boolean result = hqSocketClient.connectBlocking();
//                        Log.e("SOCKET", "连接：" + result);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();
//    }

    /**
     * 断开socket
     */
    public void disConnectSocket() {
        if (hqSocketClient.isOpen()) {
            hqSocketClient.close();
        }
    }

    /**
     * 发送心跳
     */
    private void sendPing() {
        if (heartTimer == null) {

            heartTimer = new Timer();
            heartTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (hqSocketClient.isOpen()) {
                        hqSocketClient.send("ping_p");
                    }
                }
            }, 1000 * 50, 1000 * 60);
        }
    }

    /**
     * 关闭心跳
     */
    private void closePing() {
        if (heartTimer != null) {
            heartTimer.cancel();
            heartTimer = null;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                String result = (String) msg.obj;
                if (mMsgListener!=null){
                    //TODO 重复推送订阅需要过滤取消
                    mMsgListener.responseMsg(result);
                }else {
                    sendCancelSubscribe();
                }
                break;
        }
        return false;
    }

    @Override
    public void onMessage(String message) {
        Message message1 = new Message();
        message1.obj = message;
        message1.what = 1;
        mHandler.sendMessage(message1);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("SOCKET", "onOpen");
        sendPing();
        resend();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("SOCKET", "onClose " + code);
        if (mMsgListener!=null)
            mMsgListener.disConnect();

        closePing();
        if (code == 1006) {//表示连接异常关闭的状态码
            connectSocket();
        }
    }

    @Override
    public void onError(Exception ex) {
        Log.e("SOCKET", "onError");
    }

    /**
     * 发送行情订阅
     *
     * @param list
     */
    public void sendHQ(List<GjsMarketItem> list) {
        try {
            if (hqSocketClient.isOpen()) {
                sendCancelSubscribe();
                JSONArray jsonArray = new JSONArray();
                for (GjsMarketItem gjsMarketItem : list) {
                    //行情
                    JSONObject jsonObject = new JSONObject();
//              jsonObject.put("symbol","AG.NJS");
                    jsonObject.put("symbol", gjsMarketItem.getProdCode() + "." + gjsMarketItem.getExchange());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("type", "subHq");
                    jsonObject1.put("event", "ticker");
                    jsonObject1.put("param", jsonObject);
                    jsonArray.put(jsonObject1);
                }
                subscribeMap.put("ticker", jsonArray);
                hqSocketClient.send(jsonArray.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送深度订阅
     *
     * @param list
     */
    public void sendDepth(List<GjsMarketItem> list) {
        try {
            if (hqSocketClient.isOpen()) {
                sendCancelSubscribe();
                JSONArray jsonArray = new JSONArray();
                for (GjsMarketItem gjsMarketItem : list) {
                    //行情
                    JSONObject jsonObject = new JSONObject();
//              jsonObject.put("symbol","AG.NJS");
                    jsonObject.put("symbol", gjsMarketItem.getProdCode() + "." + gjsMarketItem.getExchange());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("type", "subHq");
                    jsonObject1.put("event", "depth");
                    jsonObject1.put("param", jsonObject);
                    jsonArray.put(jsonObject1);
                }
                subscribeMap.put("depth", jsonArray);
                hqSocketClient.send(jsonArray.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送K线订阅
     *
     * @param list
     */
    public void sendKline(List<GjsMarketItem> list, String kLineType) {
        try {
            if (hqSocketClient.isOpen()) {
                sendCancelSubscribe();
                JSONArray jsonArray = new JSONArray();
                for (GjsMarketItem gjsMarketItem : list) {
                    JSONObject kLineJS1 = new JSONObject();
                    kLineJS1.put("type", "subHq");
                    kLineJS1.put("event", "kline");

                    JSONObject kLineJS = new JSONObject();
                    kLineJS.put("symbol", "AG.NJS");
                    kLineJS.put("kType", kLineType);
                    kLineJS1.put("param", kLineJS);

                    jsonArray.put(kLineJS1);
                }
                subscribeMap.put("kline", jsonArray);
                hqSocketClient.send(jsonArray.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新发送订阅
     */
    private void resend() {
        Log.e("SOCKET","resend:::::");
        if (!subscribeMap.isEmpty()) {
            for (String key : subscribeMap.keySet()) {
                JSONArray jsonArray = subscribeMap.get(key);
                hqSocketClient.send(jsonArray.toString());
                Log.e("SOCKET","resend:::::"+jsonArray.toString());
                subscribeMap.remove(key);
            }
        }
    }

    /**
     * 取消订阅
     * （有可能取消订阅失败）
     */
    private void sendCancelSubscribe() {
        Log.e("SOCKET","CANCEL:::::");
        if (!subscribeMap.isEmpty()) {
            for (String key : subscribeMap.keySet()) {
                JSONArray jsonArray = subscribeMap.get(key);
                hqSocketClient.send(jsonArray.toString().replace(SUB_TYPE, SUB_CANCEL_TYPE));
                Log.e("SOCKET","CANCEL:::::"+jsonArray.toString().replace(SUB_TYPE, SUB_CANCEL_TYPE));
                subscribeMap.remove(key);
            }
        }
    }
}
