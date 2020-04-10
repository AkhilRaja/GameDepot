package integration.unity.akhil.gamedepot.api;

import integration.unity.akhil.gamedepot.models.Games;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("games")
    Call<Games> getPopularGames(@Query("dates") String date,@Query("ordering") String ordering,@Query("page_size") int pageSize);

}
