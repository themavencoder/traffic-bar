/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 10:50 AM Last modified: 2/25/19 10:50 AM Copyright: All rights reserved Ⓒ 2019
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language governing
 *    permissions and limitations under the License.
 * /
 */

package com.getmobileltd.trafficbar.dashboard;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.discover.DiscoverFragment;
import com.getmobileltd.trafficbar.dashboard.favourite.FavouriteFragment;
import com.getmobileltd.trafficbar.dashboard.home.HomeFragment;
import com.getmobileltd.trafficbar.dashboard.profile.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFrameLayout;
  private HomeFragment homeFragment;
  private DiscoverFragment discoverFragment;
  private FavouriteFragment favouriteFragment;
  private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        UiSettings.fullScreen(this);
        mFrameLayout = findViewById(R.id.frame_layout);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        homeFragment = new HomeFragment();
        discoverFragment = new DiscoverFragment();
        favouriteFragment = new FavouriteFragment();
        profileFragment = new ProfileFragment();

        navListener();


    }

    private void navListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_discover:
                        setFragment(discoverFragment);
                        return true;
                    case R.id.nav_cart:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_favourites:
                        setFragment(favouriteFragment);
                        return true;
                    case R.id.nav_profile:
                        setFragment(profileFragment);
                        return  true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}
