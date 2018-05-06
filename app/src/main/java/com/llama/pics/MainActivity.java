package com.llama.pics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.llama.pics.fragments.ImagesFragment;
import com.llama.pics.fragments.ImagesFragmentCopy;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ImagesFragment())
                    .commit();
        }
    }
}