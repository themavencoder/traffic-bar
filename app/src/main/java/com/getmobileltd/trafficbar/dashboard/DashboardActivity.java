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

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.discover.DiscoverFragment;
import com.getmobileltd.trafficbar.dashboard.favourite.FavouriteFragment;
import com.getmobileltd.trafficbar.dashboard.home.HomeFragment;
import com.getmobileltd.trafficbar.dashboard.mycart.MyCartFragment;
import com.getmobileltd.trafficbar.dashboard.profile.ProfileFragment;

import static android.view.View.VISIBLE;

public class DashboardActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFrameLayout;
  private HomeFragment homeFragment;
  private DiscoverFragment discoverFragment;
  private FavouriteFragment favouriteFragment;
  private ProfileFragment profileFragment;
  private MyCartFragment cartFragment;
  private BottomNavigationMenuView menuView;
  private BottomNavigationItemView itemView;
  private View notificationBadge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mFrameLayout = findViewById(R.id.frame_layout);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        homeFragment = new HomeFragment();
        discoverFragment = new DiscoverFragment();
        favouriteFragment = new FavouriteFragment();
        profileFragment = new ProfileFragment();
        cartFragment = new MyCartFragment();
        addBadgeView();
        navListener();


    }

    private void addBadgeView() {
        if (menuView != null) {
             menuView = (BottomNavigationMenuView) mBottomNavigationView.getChildAt(2);
            itemView = (BottomNavigationItemView) menuView.getChildAt(2);
            notificationBadge = LayoutInflater.from(this).inflate(R.layout.view_notification_badge, menuView, false);
            itemView.addView(notificationBadge);
        }

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
                        setFragment(cartFragment);
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
