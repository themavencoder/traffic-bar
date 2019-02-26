/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 1:26 PM Last modified: 2/25/19 1:26 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.register.handlers;

import android.content.Context;
import android.view.View;

import com.getmobileltd.trafficbar.registration.register.mvp.RegisterPresenter;

/**
 * Created by themavencoder on 25,February,2019
 */
public class RegisterClickHandler {
    private Context context;
    private RegisterPresenter presenter;
    private String firstName;
    private String lastName;

    public RegisterClickHandler(Context context, String firstName,String lastName) {
        this.context = context;
        this.firstName = firstName;
        this.lastName = lastName;


    }
    public void buttonContinueClick(View view) {



    }
    public void textViewLoginClick(View view) {

    }
}
