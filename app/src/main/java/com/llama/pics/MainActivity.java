package com.llama.pics;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.llama.pics.fragments.FavoriteImagesFragment;
import com.llama.pics.fragments.ImagesFragment;
import com.llama.pics.adapters.PagerAdapter;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_URL;

    private TextView mDateTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDateTimeView = findViewById(R.id.date_time_view);
        getDateTime();

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        // Add fragments

        adapter.addFragment(new ImagesFragment(), "Images");
        adapter.addFragment(new FavoriteImagesFragment(), "Favorite");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ImagesFragment())
                    .commit();
        }*/
    }

    private void getDateTime() {
        Date date = new Date();
        mDateTimeView.setText(DateFormat.getDateTimeInstance().format(date));
    }
}