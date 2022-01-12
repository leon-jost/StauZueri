package ch.leonjost.stauzueri.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Properties {
    public String id;
    public int iconCategory;
    public int magnitudeOfDelay;
    public Date startTime;
    public Date endTime;
    public String from;
    public String to;
    public double length;
    private int delay;
    public ArrayList<Object> roadNumbers;
    public String timeValidity;
    public ArrayList<Event> events;
    public Object aci;
    public Tmc tmc;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
