package ch.leonjost.stauzueri.retrofitApiConnectors;

import java.util.List;

import ch.leonjost.stauzueri.pojo.Traffic;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitTrafficApi {
    @GET("incidentDetails?key=CFSNkPl1ywmPJqNtxODTtiCO0SEcCAVn&bbox=8.376953,47.312656,8.673995,47.462583&fields={incidents{type,geometry{type,coordinates},properties{id,iconCategory,magnitudeOfDelay,events{description,code},startTime,endTime,from,to,length,delay,roadNumbers,timeValidity,aci{probabilityOfOccurrence,numberOfReports,lastReportTime},tmc{countryCode,tableNumber,tableVersion,direction,points{location,offset}}}}}&language=de-DE")
    Call<Traffic> getTraffic();
}
