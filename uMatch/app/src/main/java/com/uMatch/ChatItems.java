package com.uMatch;


public class ChatItems {
    private int id;
    private String date, content;
    private boolean fromMe;
    private boolean showTime = true;

    public ChatItems(int id, String date, String content, boolean fromMe, boolean showTime) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.fromMe = fromMe;
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFromMe() {
        return fromMe;
    }

    public void setFromMe(boolean fromMe) {
        this.fromMe = fromMe;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }
}
