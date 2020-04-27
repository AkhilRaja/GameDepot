package integration.unity.akhil.gamedepot.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentMainDetailBindingImpl;
import integration.unity.akhil.gamedepot.lifecycle.GamesObserver;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.models.ShortScreenshot;
import integration.unity.akhil.gamedepot.models.User;
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
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.mainDetailFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
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
        ShortScreenshot[] screenShotUrl = game.getShortScreenshots().toArray(new ShortScreenshot[0]);
        MainListFragmentDirections.
                ActionMainListFragmentToMainDetailFragment action =
                MainListFragmentDirections.actionMainListFragmentToMainDetailFragment(screenShotUrl);

        action.setGameid(game.getId());
        navController.navigate(action);
    }



}
