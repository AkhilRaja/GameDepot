package integration.unity.akhil.gamedepot.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import integration.unity.akhil.gamedepot.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigationBar();

    }

    protected  void setupBottomNavigationBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);



    }
}
