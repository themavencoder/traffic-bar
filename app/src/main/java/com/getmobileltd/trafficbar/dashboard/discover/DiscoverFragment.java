/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 11:51 AM Last modified: 2/25/19 11:51 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.discover;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.DashboardActivity;
import com.getmobileltd.trafficbar.dashboard.discover.adapter.DiscoveryAdapter;
import com.getmobileltd.trafficbar.dashboard.discover.callback.ApiPasserCallback;
import com.getmobileltd.trafficbar.dashboard.discover.callback.NavigationCallback;
import com.getmobileltd.trafficbar.dashboard.discover.listener.RestaurantClickListener;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.discover.response.DiscoverResponse;
import com.getmobileltd.trafficbar.dashboard.discover.response.Restaurant;
import com.getmobileltd.trafficbar.dashboard.home.HomeFragment;

import com.getmobileltd.trafficbar.database.OnRetrieveUserApi;
import com.getmobileltd.trafficbar.database.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static com.getmobileltd.trafficbar.dashboard.DashboardActivity.DASHBOARD_TO_DISCOVER_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements RestaurantClickListener, ApiPasserCallback {
    private View v;
    private RecyclerView mRecyclerView;
    private DiscoveryAdapter mAdapter;
    private TrafficBarService trafficBarService;
    private FrameLayout frameLayout;
    public int modelid = 10000;
    private Call<DiscoverResponse> discoverCall;
    private List<Restaurant> discoveryList = new ArrayList<>();
    private Restaurant responseData;
    private UserRepository repository;
    private String callbackApiKey;
    private Button mButtonRestaurant;
    private String address;

    private HomeFragment homeFragment = new HomeFragment();
    private  AppInstance appInstance;
    private OnMenuClickListener onMenuClickListener;
    private String apiKeyFromCallback;
    private String bundleApikey;
    private String tickedLocation;

    public DiscoverFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void getApiFromDb(String apiKey) {
        bundleApikey = apiKey;
        Toast.makeText(getActivity(), "This is the bundlekeywithcallback" + bundleApikey, Toast.LENGTH_SHORT).show();
    }

    public interface OnMenuClickListener {
        void getPosition(int position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UiSettings.colorStatusbar(getActivity(), R.color.white);
      DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        assert dashboardActivity != null;
        dashboardActivity.setApiPasserCallback(this);

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        trafficBarService = TrafficBarApplication.get(getActivity()).getTrafficBarService();
        mButtonRestaurant = v.findViewById(R.id.button_enter_restaurant);
       appInstance = AppInstance.getInstance();


        callbackApiKey = appInstance.getApi_key();
        repository = new UserRepository(getActivity().getApplication());


        repository.getApikey(new OnRetrieveUserApi() {
            @Override
            public void pnRetrieveUserFinish(String apiKey) {
                apiKeyFromCallback = apiKey;
            }
        });
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mAdapter = new DiscoveryAdapter(getContext(), discoveryList);
        mAdapter.setmRestaurantClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        frameLayout = v.findViewById(R.id.progress_view);
        frameLayout.setVisibility(View.VISIBLE);

        getRestaurants();
        enterRestaurant();
        return v;



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.onMenuClickListener = (OnMenuClickListener) context;
        } catch (ClassCastException e) {
            Log.d("DiscoverFragment", e.getMessage());

        }

    }

    private void enterRestaurant() {
        mButtonRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address !=  null) {

                    Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
                    ((DashboardActivity) getActivity()).setTickedLocation(tickedLocation);
                    ((DashboardActivity) getActivity()).setFragment(homeFragment);


                    onMenuClickListener.getPosition(0);

                } else {
                    Toast.makeText(getActivity(), "Please select a restaurant first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRestaurants() {


        discoverCall = trafficBarService.getRestaurants(bundleApikey);
        discoverCall.enqueue(new Callback<DiscoverResponse>() {

            @Override
            public void onResponse(Call<DiscoverResponse> call, @NonNull Response<DiscoverResponse> response) {
                assert response.body() != null;
                discoveryList.clear();
                Restaurant loopData = new Restaurant();
                if (response.code() == 401) {
                //    Toast.makeText(getActivity(), "Retrieving API from database", Toast.LENGTH_SHORT).show();
                getRestaurants();
                }
                 else if (response.body().getStatus().equals("success")) {
                    frameLayout.setVisibility(View.GONE);
                    List<Restaurant> restaurants = response.body().getData();
                    for (Restaurant restaurant : restaurants) {
                        discoveryList.add(new Restaurant(restaurant.getAddress(),restaurant.getLocation()));
                    }

                } else {
                    Toast.makeText(v.getContext(), "An error occured", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Can not connect to the internet!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onClick(final Restaurant model, final ImageView iv) {
     address = model.getAddress();
     tickedLocation = model.getLocation();


    }

    @Override
    public void onDestroyView() {
        address = null;
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        address = null;
        super.onDestroy();
    }


}

