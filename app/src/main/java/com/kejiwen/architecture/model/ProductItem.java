package com.kejiwen.architecture.model;

/**
 * Created by tangqifa on 15/5/12.
 */
public class ProductItem {
    private String title;//标题
    private String state;//状态
    private int recommendState;//推荐状态
    private String risk;//风险
    private String rate;//收益
    private String days;//天数
    private String notice;//公告
    private String list;//列表
    private String detail;//详情
    private String startMoney;//起点金额
    private String positionMoney;//持仓金额
    private String leftMoney;//剩余金额
    private String totalMoney;//总金额
    private String comment;
    private boolean tipsVisibility;//tips可见性

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(String startMoney) {
        this.startMoney = startMoney;
    }

    public String getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(String leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getRecommendState() {
        return recommendState;
    }

    public void setRecommendState(int recommendState) {
        this.recommendState = recommendState;
    }

    public String getPositionMoney() {
        return positionMoney;
    }

    public void setPositionMoney(String positionMoney) {
        this.positionMoney = positionMoney;
    }

    public boolean isTipsVisibility() {
        return tipsVisibility;
    }

    public void setTipsVisibility(boolean tipsVisibility) {
        this.tipsVisibility = tipsVisibility;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
