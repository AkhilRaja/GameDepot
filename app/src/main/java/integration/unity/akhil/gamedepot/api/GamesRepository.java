package integration.unity.akhil.gamedepot.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;
import integration.unity.akhil.gamedepot.models.Games;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class GamesRepository {
    private GamesService gamesService;
    //TODO: Singleton pattern
    private static GamesRepository gamesRepository;

    private GamesRepository() {
        //TODO: Okhttp intercept for logging

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


    public synchronized static GamesRepository getInstance() {
        if (gamesRepository == null) {
            if (gamesRepository == null) {
                gamesRepository = new GamesRepository();
            }
        }
        return gamesRepository;
    }



    //TODO: Add Live Data

    public LiveData<Games> getPopularGames(String date, String ordering, int page_size) {

        final MutableLiveData<Games> data = new MutableLiveData<>();

        gamesService.getPopularGames(date,ordering,page_size)
                .enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Games> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    //Retrofit call to get the Popular Games
    //TODO: Add the call logic
    //Retrofit call to get the Popular Games
    //TODO: Add the call logic

}