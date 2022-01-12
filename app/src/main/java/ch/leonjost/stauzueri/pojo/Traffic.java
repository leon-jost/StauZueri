package ch.leonjost.stauzueri.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Traffic {
    private ArrayList<Incident> incidents;

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(ArrayList<Incident> incidents) {
        this.incidents = incidents;
    }
}

