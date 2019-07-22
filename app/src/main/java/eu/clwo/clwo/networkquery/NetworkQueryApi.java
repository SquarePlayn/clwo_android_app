package eu.clwo.clwo.networkquery;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkQueryApi {
    @GET("jailbreak/api/v2/networkquery.php")
    Call<NetworkQueryResult> getComments();
}