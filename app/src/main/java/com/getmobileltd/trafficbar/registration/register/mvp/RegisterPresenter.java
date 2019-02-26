/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 5:19 PM Last modified: 2/25/19 5:19 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.register.mvp;

import android.databinding.ObservableField;

import com.getmobileltd.trafficbar.registration.register.model.User;

/**
 * Created by themavencoder on 25,February,2019
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;
    private User user;

    public RegisterPresenter(User user, RegisterContract.View view) {
        this.user = user;
        this.view = view;
    }

    @Override
    public boolean checkParameters() {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        return !firstName.trim().isEmpty() && !lastName.trim().isEmpty();

    }


    @Override
    public void navigateToNextActivity() {
        view.buttonClick();
    }

    @Override
    public void saveName(String firstName, String lastName) {
        user.setFirstName(firstName.trim());
        user.setLastName(lastName.trim());
    }

    @Override
    public String firstName() {
        return user.getFirstName();
    }

    @Override
    public String lastName() {
        return user.getLastName();
    }

    @Override
    public void setError() {
        view.showError("No field should be left empty");
    }
}

