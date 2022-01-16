package ch.leonjost.stauzueri.pojo;

import java.util.ArrayList;

public class Tmc {
    private String countryCode;
    private String tableNumber;
    private String tableVersion;
    private String direction;
    private ArrayList<Point> points;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTableVersion() {
        return tableVersion;
    }

    public void setTableVersion(String tableVersion) {
        this.tableVersion = tableVersion;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
}
