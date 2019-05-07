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

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.dashboard.discover.callback.ApiPasserCallback;
import com.getmobileltd.trafficbar.dashboard.profile.callback.FirstNamePasserCallback;
import com.getmobileltd.trafficbar.dashboard.profile.callback.LastNamePasserCallback;
import com.getmobileltd.trafficbar.database.OnRetrieveFirstName;
import com.getmobileltd.trafficbar.database.OnRetrieveLastName;
import com.getmobileltd.trafficbar.database.OnRetrieveUserApi;
import com.getmobileltd.trafficbar.database.repository.UserRepository;
import com.getmobileltd.trafficbar.orderfood.menulist.menulistener.MenuOnClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.discover.DiscoverFragment;
import com.getmobileltd.trafficbar.dashboard.favourite.FavouriteFragment;
import com.getmobileltd.trafficbar.dashboard.home.HomeFragment;
import com.getmobileltd.trafficbar.dashboard.mycart.MyCartFragment;
import com.getmobileltd.trafficbar.dashboard.profile.ProfileFragment;

import static android.view.View.VISIBLE;
import static com.getmobileltd.trafficbar.dashboard.mycart.addtocart.AddToCartActivity.EXTRA_CART;

public class DashboardActivity extends AppCompatActivity implements DiscoverFragment.OnMenuClickListener , MyCartFragment.MyCartFragmentCallback {

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
    private AHBottomNavigation bottomNavigation;
    private AHBottomNavigationItem item1, item2, item3, item4, item5;
    private static int count;
    private AppInstance appInstance;
    private UserRepository repository;
    public static final String DASHBOARD_TO_DISCOVER_FRAGMENT = "dashboard_to_discover_fragment";
    public String callbackApi;
    private ApiPasserCallback apiPasserCallback;
    private FirstNamePasserCallback firstNamePasserCallback;
    private LastNamePasserCallback lastNamePasserCallback;
    private String firstNamee, lastNamee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        apiPasserCallback = this.discoverFragment;

        mFrameLayout = findViewById(R.id.frame_layout);
        bottomNavigation = findViewById(R.id.bottom_navigation_view);
        homeFragment = new HomeFragment();
        discoverFragment = new DiscoverFragment();
        favouriteFragment = new FavouriteFragment();
        profileFragment = new ProfileFragment();
        cartFragment = new MyCartFragment();
        appInstance = AppInstance.getInstance();
        repository = new UserRepository(getApplication());
        repository.getApikey(new OnRetrieveUserApi() {
            @Override
            public void pnRetrieveUserFinish(String apiKey) {
                Toast.makeText(DashboardActivity.this, "Yay! activity" + apiKey, Toast.LENGTH_SHORT).show();
                callbackApi = apiKey;
                if (apiPasserCallback != null) apiPasserCallback.getApiFromDb(apiKey);
            }
        });
        repository.getFirstName(new OnRetrieveFirstName() {
            @Override
            public void getFirstName(String name) {
                Toast.makeText(DashboardActivity.this, " yay! activity first name " + name, Toast.LENGTH_SHORT).show();
                firstNamee = name;
                if (firstNamePasserCallback != null) {
                    firstNamePasserCallback.getFirstNameFromDb(name);
                }


            }


        });

repository.getLastName(new OnRetrieveLastName() {
    @Override
    public void getLastName(String name) {
        Toast.makeText(DashboardActivity.this, "activity last name yay!" +name , Toast.LENGTH_SHORT).show();
        lastNamee = name;
        if (lastNamePasserCallback != null) {
            lastNamePasserCallback.getLastNameFromDb(name);
        }

    }

    @Override
    public void startProgressbar() {

    }

    @Override
    public void stopProgressbar() {

    }
});

        item1 = new AHBottomNavigationItem(R.string.nav_home, R.drawable.ic_home_black_24dp, R.color.white);
        item2 = new AHBottomNavigationItem(R.string.nav_discover, R.drawable.ic_discover_black_24dp, R.color.white);
        item3 = new AHBottomNavigationItem(R.string.cart, R.drawable.ic_shopping_cart_black_24dp, R.color.white);
        item4 = new AHBottomNavigationItem(R.string.favourites, R.drawable.ic_favorite_black_24dp, R.color.white);
        item5 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_person_black_24dp, R.color.white);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        Toast.makeText(this, "Current nav location " + appInstance.getNavlocation(), Toast.LENGTH_SHORT).show();


        //      bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.ash));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.ash));
        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorAccent));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);


        bottomNavigation.setNotification(String.valueOf(appInstance.getCart_count()), 2);

        defaultPosition();


        navSelectedListener();

   if (intent.hasExtra(EXTRA_CART)) {
       bottomNavigation.setCurrentItem(2);
   } else {

       bottomNavigation.setCurrentItem(1);

   }

    }

    public void setApiPasserCallback(ApiPasserCallback apiPasserCallback) {
        this.apiPasserCallback = apiPasserCallback;
    }



    private void defaultPosition() {
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {

                setFragment(discoverFragment);
            }
        });
    }

    private void navSelectedListener() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {

                    setFragment(homeFragment);


                    return true;
                } else if (position == 1) {
                    setFragment(discoverFragment);

                    return true;
                } else if (position == 2) {
                    setFragment(cartFragment);
                    bottomNavigation.setNotification(String.valueOf(appInstance.getCart_count()), 2);

                    return true;
                } else if (position == 3) {
                    setFragment(favouriteFragment);

                    return true;
                } else if (position == 4) {
                    setFragment(profileFragment);

                    return true;
                }
                return false;

            }
        });
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
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);

        fragmentTransaction.commit();

    }

    @Override
    public void getPosition(int position) {
        bottomNavigation.setCurrentItem(position);
    }

    @Override
    public void getCartTotal(int total) {
        bottomNavigation.setNotification(String.valueOf(total), 2);
    }

    public String getApiKeyFromDatabase() {
        return callbackApi;
    }

    public String getFirstNamee() {
        return firstNamee;
    }

    public void setFirstNamee(String firstNamee) {
        this.firstNamee = firstNamee;
    }

    public String getLastNamee() {
        return lastNamee;
    }

    public void setLastNamee(String lastNamee) {
        this.lastNamee = lastNamee;
    }
}
