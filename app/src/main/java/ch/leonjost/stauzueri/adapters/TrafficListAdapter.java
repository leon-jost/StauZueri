package ch.leonjost.stauzueri.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ch.leonjost.stauzueri.R;
import ch.leonjost.stauzueri.activities.StauActivity;
import ch.leonjost.stauzueri.pojo.Incident;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class TrafficListAdapter extends RecyclerView.Adapter<TrafficListAdapter.ViewHolder> {
    private final String longestRecordedDelayKey = "longestRecordedDelay";
    StauActivity stauActivity;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView artTextView;
        public TextView fromTextView;
        public TextView toTextView;
        public TextView timeTextView;
        public TextView distanceTextView;
        public Button openInBrowserButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            artTextView = (TextView) itemView.findViewById(R.id.art);
            fromTextView = (TextView) itemView.findViewById(R.id.from);
            toTextView = (TextView) itemView.findViewById(R.id.to);
            timeTextView = (TextView) itemView.findViewById(R.id.time);
            distanceTextView = (TextView) itemView.findViewById(R.id.distance);
            openInBrowserButton = (Button) itemView.findViewById(R.id.openInBrowser);
        }
    }

    // Store a member variable for the contacts
    private List<Incident> incidents = new ArrayList<>();

    // Pass in the contact array into the constructor
    public TrafficListAdapter(List<Incident> incidents, SharedPreferences sharedPreferences, StauActivity stauActivity) {
        this.stauActivity = stauActivity;

        // only add incidents, which have a delay
        for (Incident incident : incidents) {
            if (incident.getProperties().getDelay() > 0) {
                this.incidents.add(incident);
            }
        }

        // sort incidents (descending, based on delay)
        Comparator<Incident> compareByDelay = Comparator.comparing(incident -> incident.getProperties().getDelay());
        this.incidents.sort(compareByDelay.reversed());

        // save incident if it is the longest recorded delay in the history of the app
        int longestRecordedDelay = sharedPreferences.getInt(longestRecordedDelayKey, 0);
        for (Incident incident : this.incidents) {
            if (incident.getProperties().getDelay() > longestRecordedDelay) {
                longestRecordedDelay = incident.getProperties().getDelay();
            }
        }
        // save new value
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(longestRecordedDelayKey, longestRecordedDelay);
        editor.apply();
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TrafficListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.traffic_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TrafficListAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        String art = incidents.get(position).getProperties().getEvents().get(0).getDescription();
        String from = incidents.get(position).getProperties().getFrom();
        String to = incidents.get(position).getProperties().getTo();

        int time = incidents.get(position).getProperties().getDelay();
        Date date = new Date((long) (time * 1000L));
        String formattedDate = new SimpleDateFormat("HH:mm:ss").format(date);

        double distance = incidents.get(position).getProperties().getLength();
        BigDecimal bigDecimal = new BigDecimal(Double.toString(distance));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        distance = bigDecimal.doubleValue();

        // Set item views based on your views and data model
        TextView artTextView = holder.artTextView;
        artTextView.setText("Art: " + art);
        TextView fromTextView = holder.fromTextView;
        fromTextView.setText("Von: " + from);
        TextView toTextView = holder.toTextView;
        toTextView.setText("Nach: " + to);
        TextView timeTextView = holder.timeTextView;
        timeTextView.setText("Länge (Zeit): " + formattedDate);
        TextView distanceTextView = holder.distanceTextView;
        distanceTextView.setText("Länge (Distanz): " + distance + "m");

        Button openInBrowserButton = holder.openInBrowserButton;
        openInBrowserButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
                    Locale.ENGLISH, "https://maps.google.com/maps?q=loc:%f,%f",
                    incidents.get(position).getGeometry().getCoordinates().get(0).get(1), incidents.get(position).getGeometry().getCoordinates().get(0).get(0))));
            stauActivity.startActivity(intent);
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return incidents.size();
    }
}
