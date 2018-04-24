package com.arezoumandi.androidtask.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arezoumandi.androidtask.R;
import com.arezoumandi.androidtask.ui.fragment.CountryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add countries list fragment if this is first creation
        if (savedInstanceState == null) {
            CountryListFragment fragment = new CountryListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, CountryListFragment.TAG).commit();
        }
    }

}
