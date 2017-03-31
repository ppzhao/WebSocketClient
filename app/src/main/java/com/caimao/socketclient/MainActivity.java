package com.caimao.socketclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.caimao.android.socket.drafts.Draft_17;
import com.caimao.android.socket.handshake.ServerHandshake;
import com.caimao.socketclient.hq.HQController;
import com.caimao.socketclient.hq.HQSocketClient;
import com.caimao.socketclient.hq.SocketListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements SocketListener,Handler.Callback, View.OnClickListener, HQController.SocketMessageListener {

    private HQSocketClient hqSocketClient;
    private TextView messageTx;
    private Timer heartTimer;
    private EditText kLineType;
    private long HEART_PERIOD_TIME = 60 * 1000;//心跳时间
    private Handler mHandler;
    private String socketServerAddr;
    private EditText socketServerEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(this);
        socketServerAddr = "ws://10.201.6.70:8887/";
        initView();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {
        socketServerEt = (EditText)findViewById(R.id.scoket_server);
        findViewById(R.id.socket2).setOnClickListener(this);
        findViewById(R.id.connect).setOnClickListener(this);
        findViewById(R.id.disconnect).setOnClickListener(this);
        findViewById(R.id.sendping).setOnClickListener(this);
        findViewById(R.id.hangqing).setOnClickListener(this);
        findViewById(R.id.kLine).setOnClickListener(this);
        messageTx = (TextView) findViewById(R.id.message);
        kLineType = (EditText) findViewById(R.id.kline_type);
    }

    @Override
    public void onMessage(String message) {
        Message message1 = new Message();
        message1.obj = message;
        message1.what = 1;
        mHandler.sendMessage(message1);
        messageTx.setText(message);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("SOCKET", "onOpen");
        sendPing();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("SOCKET", "onClose " + code);
        closePing();
        if (code == 1006) {
            connectSocket();
        }
    }

    @Override
    public void onError(Exception ex) {
        Log.e("SOCKET", "onError");
    }

    public void connectSocket() {
        try {
            if (hqSocketClient != null && hqSocketClient.isOpen()) {
                hqSocketClient.close();
            }
            getSokcetServerAdd();
            //WebSocketClient不能重复使用
//            hqSocketClient = new HQSocketClient(new URI("ws://10.202.1.110:8089/hqapi/ws"), new Draft_17(), this);
            hqSocketClient = new HQSocketClient(new URI(getSokcetServerAdd()), new Draft_17(), this);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (!hqSocketClient.isOpen()) {
            hqSocketClient.connect();
            Log.e("SOCKET", "连接==");
        }
    }

    /**
     * 获取服务端地址
     * @return
     */
    private String getSokcetServerAdd() {
        String serverAddr = socketServerEt.getText().toString().trim();
        if (!TextUtils.isEmpty(serverAddr)) {
            socketServerAddr = serverAddr;
        }
        return socketServerAddr;
    }

    public void syncConnectionSocket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!hqSocketClient.isOpen()) {
                    try {
                        boolean result = hqSocketClient.connectBlocking();
                        Log.e("SOCKET", "连接：" + result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.socket2:
                startActivity(new Intent(this, Main2Activity.class));
                break;
            case R.id.connect:
                connectSocket();
//                syncConnectionSocket();
                break;
            case R.id.disconnect:
                if (hqSocketClient.isOpen()) {
                    hqSocketClient.close();
                }
                break;
            case R.id.sendping:
                sendPing();
                break;
            case R.id.hangqing:

                try {
                    if (hqSocketClient.isOpen()) {
                        //行情
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("symbol", "AG.NJS");
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("type", "subHq");
                        jsonObject1.put("event", "ticker");
                        jsonObject1.put("param", jsonObject);

                        //深度
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("symbol", "AG.NJS");
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("type", "subHq");
                        jsonObject3.put("event", "depth");
                        jsonObject3.put("param", jsonObject2);

                        JSONArray jsonArray = new JSONArray();
                        jsonArray.put(jsonObject1);
                        jsonArray.put(jsonObject3);
//                        hqSocketClient.send(jsonArray.toString());
                        String req = "[{\"type\":\"subHq\",\"param\":{\"symbol\":\"mAu(T+D).SJS\"},\"event\":\"ticker\"},{\"type\":\"subHq\",\"param\":{\"symbol\":\"AG.NJS\"},\"event\":\"ticker\"},{\"type\":\"subHq\",\"param\":{\"symbol\":\"Ag(T+D).SJS\"},\"event\":\"ticker\"},{\"type\":\"subHq\",\"param\":{\"symbol\":\"AG.LIFFE\"},\"event\":\"ticker\"},{\"type\":\"subHq\",\"param\":{\"symbol\":\"AU.LIFFE\"},\"event\":\"ticker\"},{\"type\":\"subHq\",\"param\":{\"symbol\":\"Au(T+D).SJS\"},\"event\":\"ticker\"}]";
                        hqSocketClient.send(req);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.kLine:
                //Kline
                String kLineType = this.kLineType.getText().toString().trim();
                try {
                    JSONObject kLineJS1 = new JSONObject();
                    kLineJS1.put("type", "subHq");
                    kLineJS1.put("event", "kline");

                    JSONObject kLineJS = new JSONObject();
                    kLineJS.put("symbol", "AG.NJS");
                    kLineJS.put("kType", kLineType);
                    kLineJS1.put("param", kLineJS);

                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(kLineJS1);
                    if (hqSocketClient.isOpen()) {
                        hqSocketClient.send(jsonArray.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

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
            }, HEART_PERIOD_TIME, HEART_PERIOD_TIME);
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

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                messageTx.setText((String) msg.obj);
                break;
        }
        return false;
    }

    @Override
    public void responseMsg(String msg) {

    }

    @Override
    public void disConnect() {

    }
}
