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
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.getmobileltd.trafficbar.registration.login.LoginActivity;
import com.getmobileltd.trafficbar.registration.register.SignUpActivity;
import com.getmobileltd.trafficbar.registration.register.confirmregister.ConfirmRegisterActivity;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.mvp.RegisterPresenter;

/**
 * Created by themavencoder on 25,February,2019
 */
public class RegisterClickHandler {
    SignUpActivity activity = new SignUpActivity();
    private Context context;
 private User user;

    public RegisterClickHandler(Context context, User user) {
        this.context = context;
        this.user = user;



    }
    public void buttonContinueClick(View view) {
        Toast.makeText(context, "Your firstname is " + user.getFirstName()+ "lastName is " +user.getLastName(), Toast.LENGTH_SHORT).show();



    }
    public void textViewLoginClick(View view) {

    }
}
