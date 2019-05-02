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


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import com.getmobileltd.trafficbar.dashboard.discover.adapter.DiscoveryAdapter;
import com.getmobileltd.trafficbar.dashboard.discover.listener.RestaurantClickListener;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.discover.response.DiscoverResponse;
import com.getmobileltd.trafficbar.dashboard.discover.response.Restaurant;
import com.getmobileltd.trafficbar.dashboard.home.HomeFragment;
import com.getmobileltd.trafficbar.database.AsyncResponse;
import com.getmobileltd.trafficbar.database.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements RestaurantClickListener {
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

    public DiscoverFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UiSettings.colorStatusbar(getActivity(), R.color.white);
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        trafficBarService = TrafficBarApplication.get(getActivity()).getTrafficBarService();
        mButtonRestaurant = v.findViewById(R.id.button_enter_restaurant);
        AppInstance appInstance = AppInstance.getInstance();
        callbackApiKey = appInstance.getApi_key();
        repository = new UserRepository(getActivity().getApplication());
       /* UserRepository.delegate =  new AsyncResponse() {
            @Override
            public void processFinish(String apiKey) {
                callbackApiKey = apiKey;
            }
        };
*/
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mAdapter = new DiscoveryAdapter(getContext(), discoveryList);
        mAdapter.setmRestaurantClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        frameLayout = v.findViewById(R.id.progress_view);
        frameLayout.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), callbackApiKey, Toast.LENGTH_SHORT).show();
        getRestaurants();
        enterRestaurant();
        return v;


    }

    private void enterRestaurant() {
        mButtonRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address !=  null) {
                    Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,homeFragment)
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast.makeText(getActivity(), "Please select a restaurant first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRestaurants() {
        repository.getApikey();
        discoverCall = trafficBarService.getRestaurants(callbackApiKey);
        discoverCall.enqueue(new Callback<DiscoverResponse>() {

            @Override
            public void onResponse(Call<DiscoverResponse> call, @NonNull Response<DiscoverResponse> response) {
                assert response.body() != null;
                discoveryList.clear();
                Restaurant loopData = new Restaurant();
                if (response.body().getStatus().equals("success")) {
                    frameLayout.setVisibility(View.GONE);
                    List<Restaurant> restaurants = response.body().getData();
                    for (Restaurant restaurant : restaurants) {
                        discoveryList.add(new Restaurant(restaurant.getAddress()));
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

