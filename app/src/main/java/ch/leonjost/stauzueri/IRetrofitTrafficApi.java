package ch.leonjost.stauzueri;

import java.util.List;

import ch.leonjost.stauzueri.pojo.Traffic;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitTrafficApi {
    @GET("incidentDetails?key=CFSNkPl1ywmPJqNtxODTtiCO0SEcCAVn&bbox=8.627789,47.138083,8.93951,47.510497&fields={incidents{type,geometry{type,coordinates},properties{id,iconCategory,magnitudeOfDelay,events{description,code},startTime,endTime,from,to,length,delay,roadNumbers,timeValidity,aci{probabilityOfOccurrence,numberOfReports,lastReportTime},tmc{countryCode,tableNumber,tableVersion,direction,points{location,offset}}}}}&language=de-DE")
    Call<Traffic> getTraffic();
}
