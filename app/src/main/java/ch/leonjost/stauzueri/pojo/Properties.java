package ch.leonjost.stauzueri.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Properties {
    private String id;
    private int iconCategory;
    private int magnitudeOfDelay;
    private Date startTime;
    private Date endTime;
    private String from;
    private String to;
    private double length;
    private int delay;
    private ArrayList<Object> roadNumbers;
    private String timeValidity;
    private ArrayList<Event> events;
    private Object aci;
    private Tmc tmc;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIconCategory() {
        return iconCategory;
    }

    public void setIconCategory(int iconCategory) {
        this.iconCategory = iconCategory;
    }

    public int getMagnitudeOfDelay() {
        return magnitudeOfDelay;
    }

    public void setMagnitudeOfDelay(int magnitudeOfDelay) {
        this.magnitudeOfDelay = magnitudeOfDelay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public ArrayList<Object> getRoadNumbers() {
        return roadNumbers;
    }

    public void setRoadNumbers(ArrayList<Object> roadNumbers) {
        this.roadNumbers = roadNumbers;
    }

    public String getTimeValidity() {
        return timeValidity;
    }

    public void setTimeValidity(String timeValidity) {
        this.timeValidity = timeValidity;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Object getAci() {
        return aci;
    }

    public void setAci(Object aci) {
        this.aci = aci;
    }

    public Tmc getTmc() {
        return tmc;
    }

    public void setTmc(Tmc tmc) {
        this.tmc = tmc;
    }
}
