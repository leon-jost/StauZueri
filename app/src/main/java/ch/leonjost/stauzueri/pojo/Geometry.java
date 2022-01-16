package ch.leonjost.stauzueri.pojo;

import java.util.ArrayList;

public class Geometry {
    private String type;
    private ArrayList<ArrayList<Double>> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }
}
