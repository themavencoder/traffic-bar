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


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.discover.adapter.DiscoveryAdapter;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private DiscoveryAdapter mAdapter;
    private List<DiscoveryModel> discoveryList = SampleContent.RESTAURANT;


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UiSettings.colorStatusbar(getActivity(),R.color.white);
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mAdapter = new DiscoveryAdapter(getContext(),discoveryList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

}
