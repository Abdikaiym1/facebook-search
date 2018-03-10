package com.example.xstrike.facebook_search_by_tag.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.xstrike.facebook_search_by_tag.R;
import com.example.xstrike.facebook_search_by_tag.beans.DateOfPlace;
import com.example.xstrike.facebook_search_by_tag.ui.fragments.CoreFragment;
import com.example.xstrike.facebook_search_by_tag.ui.fragments.PreviewFragment;
import com.example.xstrike.facebook_search_by_tag.ui.fragments.QueryFragment;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QueryFragment.ListenerSendArray {
    private List<CoreFragment> pages;
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.xstrike.facebook_search_by_tag",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        setupFragments();
        setupViewPager();
        setupTabLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentAdapter.getFragment(0).onActivityResult(requestCode, resultCode, data);
    }

    private void setupFragments() {
        pages = Arrays.asList(new QueryFragment(), new PreviewFragment());
        fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());

        for (CoreFragment page : pages) {
            fragmentAdapter.addFragment(page);
        }

    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(fragmentAdapter);
    }

    private void setupTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void sendArray(List<DateOfPlace> dateOfPlaces) {
        Log.d("sendArray", "111");
        pages.get(1).changeList(dateOfPlaces);
    }
}
