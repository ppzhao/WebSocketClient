package com.caimao.socketclient.entity;

import java.io.Serializable;

public class GjsMarketItem implements Serializable {

    private String exchange;//交易所简称
    private String prodCode;//商品代码
    private String prodName;//商品名称
    private String boardCode;//
    private float openPx;//开盘价
    private float pxChange;//变化价格
    private float pxChangeRate;//变化的百分比
    private float highPx;//最高价
    private float lowPx;//最低价
    private float closePx;//收盘价
    private float lastPx;//最新价
    private float preclosePx;
    private float preClosePx;//昨收
    private float averagePx;//均价
    private int businessAmount;//成交量
    private float businessBalance;//成交金额
    private float highLimit;//
    private float lowLimit;
    private int lastAmount;
    private String ownProductId;
    private float lastSettle;//昨结
    private String type;
    private int position;//item位置
    private boolean isSelected = false;
    private String userId;
    private String minTime;
    private String isTrade;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public float getOpenPx() {
        return openPx;
    }

    public void setOpenPx(float openPx) {
        this.openPx = openPx;
    }

    public float getPxChange() {
        return pxChange;
    }

    public void setPxChange(float pxChange) {
        this.pxChange = pxChange;
    }

    public float getPxChangeRate() {
        return pxChangeRate;
    }

    public void setPxChangeRate(float pxChangeRate) {
        this.pxChangeRate = pxChangeRate;
    }

    public float getHighPx() {
        return highPx;
    }

    public void setHighPx(float highPx) {
        this.highPx = highPx;
    }

    public float getLowPx() {
        return lowPx;
    }

    public void setLowPx(float lowPx) {
        this.lowPx = lowPx;
    }

    public float getClosePx() {
        return closePx;
    }

    public void setClosePx(float closePx) {
        this.closePx = closePx;
    }

    public float getLastPx() {
        return lastPx;
    }

    public void setLastPx(float lastPx) {
        this.lastPx = lastPx;
    }

    public float getPreclosePx() {
        return preclosePx;
    }

    public void setPreclosePx(float preclosePx) {
        this.preclosePx = preclosePx;
    }

    public float getPreClosePx() {
        return preClosePx;
    }

    public void setPreClosePx(float preClosePx) {
        this.preClosePx = preClosePx;
    }

    public float getAveragePx() {
        return averagePx;
    }

    public void setAveragePx(float averagePx) {
        this.averagePx = averagePx;
    }

    public int getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(int businessAmount) {
        this.businessAmount = businessAmount;
    }

    public float getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(float businessBalance) {
        this.businessBalance = businessBalance;
    }

    public float getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(float highLimit) {
        this.highLimit = highLimit;
    }

    public float getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(float lowLimit) {
        this.lowLimit = lowLimit;
    }

    public int getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getOwnProductId() {
        return ownProductId;
    }

    public void setOwnProductId(String ownProductId) {
        this.ownProductId = ownProductId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getLastSettle() {
        return lastSettle;
    }

    public void setLastSettle(float lastSettle) {
        this.lastSettle = lastSettle;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getIsTrade() {
        return isTrade;
    }

    public void setIsTrade(String isTrade) {
        this.isTrade = isTrade;
    }

    @Override
    public String toString() {
        return "GjsMarketItem [exchange=" + exchange + ", prodCode=" + prodCode
                + ", prodName=" + prodName + ", boardCode=" + boardCode
                + ", openPx=" + openPx + ", pxChange=" + pxChange
                + ", pxChangeRate=" + pxChangeRate + ", highPx=" + highPx
                + ", lowPx=" + lowPx + ", closePx=" + closePx + ", lastPx="
                + lastPx + ", preclosePx=" + preclosePx + ", preClosePx="
                + preClosePx + ", averagePx=" + averagePx + ", businessAmount="
                + businessAmount + ", businessBalance=" + businessBalance
                + ", highLimit=" + highLimit + ", lowLimit=" + lowLimit
                + ", lastAmount=" + lastAmount + ", ownProductId="
                + ownProductId + ", lastSettle=" + lastSettle + ", type="
                + type + ", position=" + position + ", isSelected="
                + isSelected + ", userId=" + userId + ", minTime=" + minTime
                + "]";
    }

}
