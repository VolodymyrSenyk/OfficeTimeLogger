package com.senyk.volodymyr.officetimelogger.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.senyk.volodymyr.officetimelogger.R;

public class OfficeTimeLoggerMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            /*if (destination.getId() == R.id.statisticsFragment) {
                Toolbar toolbar = findViewById(R.id.statistics_screen_toolbar);
                setSupportActionBar(toolbar);
                *//*toolbar.inflateMenu(R.menu.statistics_screen_menu)
                toolbar.setOnMenuItemClickListener {
                    onOptionsItemSelected(it);
                }*//*
            } else {
                Toolbar toolbar = findViewById(R.id.time_logger_screen_toolbar);
                setSupportActionBar(toolbar);
            }*/
        });
    }
}
