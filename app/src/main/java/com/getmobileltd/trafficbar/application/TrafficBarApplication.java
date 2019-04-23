/*
 * *
 * Creator: Tobiloba Adejumo on 2/26/19 9:06 AM Last modified: 2/26/19 9:04 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.application;

import android.app.Activity;
import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by themavencoder on 26,February,2019
 */
public class TrafficBarApplication extends Application {
    public static final String BASE_URL = "https://trafficbar.herokuapp.com/api/v1/";
    private TrafficBarService trafficBarService;
    //Create Logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create OkHttpClient
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);

    public static TrafficBarApplication get(Activity activity) {
        return (TrafficBarApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit trafficBarRetrofit =  new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build())
                .build();

        trafficBarService =  trafficBarRetrofit.create(TrafficBarService.class);
    }

    public TrafficBarService getTrafficBarService() {
        return  trafficBarService;
    }
}
