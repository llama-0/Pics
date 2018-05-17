package com.llama.pics.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.llama.pics.R;
import com.llama.pics.utils.VolleySingleton;

import static com.llama.pics.MainActivity.EXTRA_URL;

public class ImageDetailFragment extends Fragment {

    private ImageLoader mImageLoader;

    public ImageDetailFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getView().findViewById(R.id.toolbar_detailed);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);


        // get the data -- display image
        mImageLoader = VolleySingleton.getInstance(getContext()).getImageLoader();
        NetworkImageView imageView = getView().findViewById(R.id.imageView);
        System.out.println("EXTRA_URL variable in second fragment " + EXTRA_URL);
        imageView.setImageUrl(EXTRA_URL, mImageLoader);
    }

}
