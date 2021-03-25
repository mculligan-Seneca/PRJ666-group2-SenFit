/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.senfit.R;
import com.example.senfit.ui.home.SenfitHomeFragment;
import com.example.senfit.ui.inperson.HomeFragment;
import com.example.senfit.ui.inperson.InpersonFragment;
import com.example.senfit.ui.online.OnlineClassFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_home, R.string.tab_inperson, R.string.tab_online};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            return SenfitHomeFragment.newInstance();

            case 1:
                return InpersonFragment.newInstance(position + 1);

            case 2:
                return OnlineClassFragment.newInstance();

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}