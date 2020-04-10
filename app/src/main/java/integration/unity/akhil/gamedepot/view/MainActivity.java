package integration.unity.akhil.gamedepot.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.api.ApiInterface;
import integration.unity.akhil.gamedepot.api.RetrofitApiClient;
import integration.unity.akhil.gamedepot.models.Games;
import integration.unity.akhil.gamedepot.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiService =
            RetrofitApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationBar();

        //Retrofit Calls for the Most Popular Games
        Call<Games> call = apiService.getPopularGames(Constants.Popular.DATE,Constants.Popular.ORDERING,Constants.PAGE_SIZE);
        call.enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                int statusCode = response.code();
                Log.d("API",statusCode + "");

                //TODO: Parse the response into a Games object to map with Views
            }

            @Override
            public void onFailure(Call<Games> call, Throwable t) {
                Log.e("API","Error" + t.getLocalizedMessage() + t.getMessage());
                //TODO: Handle errors
            }
        });

        //Retrofit Calls for the Most Anticipated Games
        //TODO: ADD this call logic

        //Retrofit Calls for the Top Rated Games
        //TODO: ADD this call logic

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigationBar();

    }

    protected  void setupBottomNavigationBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
