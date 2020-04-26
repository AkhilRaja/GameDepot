package integration.unity.akhil.gamedepot.api;

import integration.unity.akhil.gamedepot.models.GameDetail;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GamesService {

    String URL = Constants.BASE_URL;
    @GET("games")
    Call<Games> getGames(@Query("dates") String date, @Query("ordering") String ordering, @Query("page_size") int pageSize);
    @GET("games/{id}")
    Call<GameDetail> getGameDetail(@Path("id") int id);
}
