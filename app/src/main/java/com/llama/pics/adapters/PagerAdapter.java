package com.llama.pics.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.llama.pics.fragments.FavoriteImagesFragment;
import com.llama.pics.fragments.ImagesFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listFragmentTitle = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }


    @Override
    public int getCount() {
        return listFragmentTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmentTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        listFragment.add(fragment);
        listFragmentTitle.add(title);
    }
}
