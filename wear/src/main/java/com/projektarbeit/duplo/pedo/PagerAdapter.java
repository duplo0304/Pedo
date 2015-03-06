package com.projektarbeit.duplo.pedo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/********************************************************************************************
 * Implementierung eines FragmentPagerAdapters.
 * Dieser generiert die View aus den einzelnen Fragments.
 ********************************************************************************************/
public class PagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments = null;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Fragment>();
    }


    /**
     * @param position
     * @return Position bzw. Index der aktuellen Page
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    /**
     * @return Groesse bzw. Anzahl der vom Adapter verknuepften Fragmente
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * Fuegt Fragment-Page zu Sicht hinzu.
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }
}