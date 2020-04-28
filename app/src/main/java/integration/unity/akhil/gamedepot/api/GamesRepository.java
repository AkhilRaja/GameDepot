package integration.unity.akhil.gamedepot.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import integration.unity.akhil.gamedepot.models.GameDetail;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static integration.unity.akhil.gamedepot.utils.Constants.BASE_URL;


public class GamesRepository {
    private GamesService gamesService;
    //Singleton pattern
    private static GamesRepository gamesRepository;
    final MutableLiveData<Games> popularGames = new MutableLiveData<>();
    final MutableLiveData<Games> anticipatedGames = new MutableLiveData<>();
    final MutableLiveData<Games> topGames = new MutableLiveData<>();

    public void workerUpdateGames(){

    }

    private GamesRepository() {
        //OKHTTP intercept for logging

        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .build();

                Request request = original.newBuilder()
                        .url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GamesService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gamesService = retrofit.create(GamesService.class);
    }
    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    public synchronized static GamesRepository getInstance() {
        if (gamesRepository == null) {
            if (gamesRepository == null) {
                gamesRepository = new GamesRepository();
            }
        }
        return gamesRepository;
    }


    public LiveData<Games> getPopularGames(String date, String ordering, int page_size) {
        gamesService.getGames(date,ordering,page_size)
                .enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                popularGames.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Games> call, Throwable t) {
                popularGames.setValue(null);
            }
        });
        return popularGames;
    }
    public LiveData<Games> getAnticipatedGames(String date, String ordering, int page_size) {
        gamesService.getGames(date,ordering,page_size)
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        anticipatedGames.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {
                        anticipatedGames.setValue(null);
                    }
                });
        return anticipatedGames;
    }
    public LiveData<Games> getTopGames(String date, String ordering, int page_size) {
        gamesService.getGames(date,ordering,page_size)
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        topGames.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {
                        topGames.setValue(null);
                    }
                });
        return topGames;
    }

    public LiveData<GameDetail> getGameDetail(int id) {
        final MutableLiveData<GameDetail> data = new MutableLiveData<>();
        gamesService.getGameDetail(id)
                .enqueue(new Callback<GameDetail>() {
                    @Override
                    public void onResponse(Call<GameDetail> call, Response<GameDetail> response) {
                        data.setValue(response.body());

                        Log.d("Game Depot :  ", "Response : " + response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<GameDetail> call, Throwable t) {
                        data.setValue(null);
                        Log.e("Game Depot :  ", "Response : " + t.getMessage());
                    }
                });
        return data;
    }

    public List<Result> getGamesForWidget() {
        List<Result> results = new ArrayList<>();
        gamesService.getGames(Constants.Popular.DATE, Constants.Popular.ORDERING,Constants.PAGE_SIZE)
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        results.addAll(response.body().getResults());
                        Log.d("Game Depot : ", "Response: " + response.body().getResults().size());
                    }

                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {
                        Log.e("Game Depot :  ", "Response : " + t.getMessage());
                    }
                });
        return results;
    }
}