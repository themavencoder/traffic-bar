/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 11:53 AM Last modified: 2/25/19 11:53 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.checkout.CheckoutActivity;
import com.getmobileltd.trafficbar.dashboard.DashboardActivity;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.AddToCartActivity;
import com.getmobileltd.trafficbar.dashboard.profile.callback.FirstNamePasserCallback;
import com.getmobileltd.trafficbar.dashboard.profile.callback.LastNamePasserCallback;
import com.getmobileltd.trafficbar.dashboard.profile.editprofile.EditProfileActivity;
import com.getmobileltd.trafficbar.database.OnRetrieveFirstName;
import com.getmobileltd.trafficbar.database.OnRetrieveLastName;
import com.getmobileltd.trafficbar.database.repository.UserRepository;

import static com.getmobileltd.trafficbar.application.TrafficBarApplication.IMAGE_BASE_URL;

public class ProfileFragment extends Fragment {
    private TextView viewProfile, mTextViewFullName;
    private View v;
    private UserRepository repository;
    private String firstName, lastName;
    private AppInstance appInstance;
    private String repoFirstName, repoLastName;
    private CircleImageView imageViewProfile;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UiSettings.colorStatusbar(getActivity(), R.color.colorAccent);
        // Inflate the layout for this fragment
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        assert dashboardActivity != null;

        repoFirstName = dashboardActivity.getFirstNamee();
        repoLastName = dashboardActivity.getLastNamee();
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        appInstance = AppInstance.getInstance();
        firstName = appInstance.getFirstName();
        lastName = appInstance.getLastName();
        init();

        String fullName = repoFirstName + " " + repoLastName;
        mTextViewFullName.setText(fullName);

        return v;


    }

    private void init() {
        viewProfile = v.findViewById(R.id.textview_view_profile);
        mTextViewFullName = v.findViewById(R.id.textview_full_name);
        imageViewProfile = v.findViewById(R.id.imageview_profile_image);
        String location =  IMAGE_BASE_URL + appInstance.getImageBaseUrl();
        Toast.makeText(getActivity(), "" + location, Toast.LENGTH_SHORT).show();

        GlideUrl glideUrl = new GlideUrl(location,
                new LazyHeaders.Builder()
        .addHeader("Authorization",appInstance.getApi_key()).build());

        Glide.with(getActivity()).load(glideUrl).apply(new RequestOptions()
        .placeholder(R.color.colorAccent).error(R.color.black)).into(imageViewProfile);






        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditProfileActivity.class);
                intent.putExtra("firstNameConstant",repoFirstName);
                intent.putExtra("lastNameConstant",repoLastName);
                startActivity(intent);
            }
        });
    }



}

