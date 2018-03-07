package com.example.xstrike.facebook_search_by_tag;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.xstrike.facebook_search_by_tag.ui.fragments.CoreFragment;
import com.facebook.core.Core;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private final List<CoreFragment> fragmentList = new ArrayList<>();

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(CoreFragment coreFragment) {
        fragmentList.add(coreFragment);
    }

    public CoreFragment getFragment(int position) {
        return fragmentList.get(position);
    }
}
