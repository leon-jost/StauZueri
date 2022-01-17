package ch.leonjost.stauzueri.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ch.leonjost.stauzueri.R;
import ch.leonjost.stauzueri.adapters.TrafficListAdapter;
import ch.leonjost.stauzueri.pojo.Incident;
import ch.leonjost.stauzueri.pojo.Traffic;
import ch.leonjost.stauzueri.services.TrafficApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StauActivity extends AppCompatActivity {
    TrafficApiService trafficApiService;
    boolean trafficApiServiceBound = false;

    private static final String TAG = StauActivity.class.getSimpleName();
    List<Incident> incidents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stau);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, TrafficApiService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        trafficApiServiceBound = false;
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            TrafficApiService.TrafficApiBinder binder = (TrafficApiService.TrafficApiBinder) service;
            trafficApiService = binder.getService();
            trafficApiServiceBound = true;

            // Run Code after Service has been bound
            trafficApiService.getIncidents(new Callback<Traffic>() {

                @Override
                public void onResponse(Call<Traffic> call, Response<Traffic> response) {
                    incidents = response.body().getIncidents();
                    InitializeRecyclerView();
                }

                @Override
                public void onFailure(Call<Traffic> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            trafficApiServiceBound = false;
        }
    };

    private void InitializeRecyclerView() {
        // Lookup the recyclerview in activity layout
        RecyclerView incidentsRecyclerView = (RecyclerView) findViewById(R.id.incidentsRecyclerView);
        // Create adapter passing in the sample user data
        TrafficListAdapter adapter = new TrafficListAdapter(incidents, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()), this);
        // Attach the adapter to the recyclerview to populate items
        incidentsRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        incidentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void switchToStatistikActivity(View view) {
        Intent intent = new Intent(this, StatistikActivity.class);
        startActivity(intent);
    }
}