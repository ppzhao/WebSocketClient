package com.caimao.socketclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.caimao.socketclient.entity.GjsMarketItem;
import com.caimao.socketclient.hq.HQController;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,HQController.SocketMessageListener {

    private TextView messageTx;
    private EditText kLineType;
    private long HEART_PERIOD_TIME = 60*1000;//心跳时间
    private HQController instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        instance = HQController.getInstance(this);
        instance.initMsgReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        instance.endMsgReceiver();
    }

    private void initView() {
        findViewById(R.id.connect).setVisibility(View.GONE);
        findViewById(R.id.disconnect).setVisibility(View.GONE);
        findViewById(R.id.sendping).setVisibility(View.GONE);
        findViewById(R.id.socket2).setVisibility(View.GONE);
        findViewById(R.id.hangqing).setOnClickListener(this);
        findViewById(R.id.depth).setOnClickListener(this);
        findViewById(R.id.kLine).setOnClickListener(this);
        messageTx = (TextView)findViewById(R.id.message);
        kLineType = (EditText)findViewById(R.id.kline_type);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.depth:
                ArrayList<GjsMarketItem> depthList = new ArrayList<>();
                GjsMarketItem depthItem = new GjsMarketItem();
                depthItem.setProdCode("mAu(T+D)");
                depthItem.setExchange("SJS");
                GjsMarketItem depthItem1 = new GjsMarketItem();
                depthItem1.setProdCode("Ag(T+D)");
                depthItem1.setExchange("SJS");
                depthList.add(depthItem);
                depthList.add(depthItem1);
                instance.sendDepth(depthList);
                break;
            case R.id.hangqing:
                ArrayList<GjsMarketItem> gjsMarketItems = new ArrayList<>();
                GjsMarketItem gjsMarketItem = new GjsMarketItem();
                gjsMarketItem.setProdCode("AG");
                gjsMarketItem.setExchange("NJS");
                GjsMarketItem gjsMarketItem1 = new GjsMarketItem();
                gjsMarketItem1.setProdCode("CU");
                gjsMarketItem1.setExchange("NJS");
                gjsMarketItems.add(gjsMarketItem);
                gjsMarketItems.add(gjsMarketItem1);
                instance.sendHQ(gjsMarketItems);

//                try {
//                    if (hqSocketClient.isOpen()){
//                        //行情
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("symbol","AG.NJS");
//                        JSONObject jsonObject1 = new JSONObject();
//                        jsonObject1.put("type","subHq");
//                        jsonObject1.put("event","ticker");
//                        jsonObject1.put("param",jsonObject);
//
//                        //深度
//                        JSONObject jsonObject2 = new JSONObject();
//                        jsonObject2.put("symbol","AG.NJS");
//                        JSONObject jsonObject3 = new JSONObject();
//                        jsonObject3.put("type","subHq");
//                        jsonObject3.put("event","depth");
//                        jsonObject3.put("param",jsonObject2);
//
//                        JSONArray jsonArray = new JSONArray();
//                        jsonArray.put(jsonObject1);
//                        jsonArray.put(jsonObject3);
//                        hqSocketClient.send(jsonArray.toString());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.kLine:
                //Kline
                String kLineType = this.kLineType.getText().toString().trim();
//                try {
//                    JSONObject kLineJS1 = new JSONObject();
//                    kLineJS1.put("type","subHq");
//                    kLineJS1.put("event","kline");
//
//                    JSONObject kLineJS = new JSONObject();
//                    kLineJS.put("symbol","AG.NJS");
//                    kLineJS.put("kType",kLineType);
//                    kLineJS1.put("param",kLineJS);
//
//                    JSONArray jsonArray = new JSONArray();
//                    jsonArray.put(kLineJS1);
//                    if (hqSocketClient.isOpen()){
//                        hqSocketClient.send(jsonArray.toString());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                ArrayList<GjsMarketItem> kLineItems = new ArrayList<>();
                GjsMarketItem kLineItem = new GjsMarketItem();
                kLineItem.setProdCode("AG");
                kLineItem.setExchange("NJS");
                kLineItems.add(kLineItem);
                instance.sendKline(kLineItems,kLineType);
                break;

        }
    }

    @Override
    public void responseMsg(String msg) {
        Log.e("SOCKET", "UI Receive msg:::: ");
        messageTx.setText(msg);
    }

    @Override
    public void disConnect() {
        Log.e("SOCKET", "UI disConnect ::::");
    }
}
