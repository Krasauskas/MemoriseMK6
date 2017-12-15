package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 13-Nov-17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CategoriesFragment.newInstance();
            case 1:
                return CasesNameFragment.newInstance();
            case 2:
                return CasesYearFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return CategoriesFragment.TITLE;

            case 1:
                return CasesNameFragment.TITLE;

            case 2:
                return CasesYearFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}