package integration.unity.akhil.gamedepot.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentMainDetailBindingImpl;
import integration.unity.akhil.gamedepot.lifecycle.GamesObserver;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.view.callback.OnClickCallBack;


public class MainActivity extends AppCompatActivity implements OnClickCallBack {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);
        setupBottomNavigationBar();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Setup Bottom Navigation Bar
        setupBottomNavigationBar();

        //Observe Lifecycle events
        getLifecycle().addObserver(new GamesObserver());
    }

    protected  void setupBottomNavigationBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onGotoDetailView(View view, Result game) {
        Log.d("Game Depot", "Game : " + game.getId());
        MainListFragmentDirections.
                ActionMainListFragmentToMainDetailFragment action =
                MainListFragmentDirections.actionMainListFragmentToMainDetailFragment();

        action.setGameid(game.getId());
        navController.navigate(action);

    }
}
