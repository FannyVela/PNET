package uca.es.appandroid;

public class Actividad {

    private int time;
    private String title;
    private String speaker;

    public Actividad (int time, String title, String speaker){
        this.time = time;
        this.title = title;
        this.speaker = speaker;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
