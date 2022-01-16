package ch.leonjost.stauzueri.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import ch.leonjost.stauzueri.retrofitApiConnectors.IRetrofitTrafficApi;
import ch.leonjost.stauzueri.retrofitApiConnectors.RetrofitTrafficApi;
import ch.leonjost.stauzueri.pojo.Incident;
import ch.leonjost.stauzueri.pojo.Traffic;
import retrofit2.Call;
import retrofit2.Callback;

public class TrafficApiService extends Service {
    public List<Incident> incidents = new ArrayList<>();

    // Binder given to clients
    private final IBinder binder = new TrafficApiBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class TrafficApiBinder extends Binder {
        public TrafficApiService getService() {
            // Return this instance of MainService so clients can call public methods
            return TrafficApiService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /** method for clients */
    public void getIncidents(Callback<Traffic> callback) {
        IRetrofitTrafficApi apiService =
                RetrofitTrafficApi.getClient().create(IRetrofitTrafficApi.class);

        Call<Traffic> call = apiService.getTraffic();
        call.enqueue(callback);
    }
}