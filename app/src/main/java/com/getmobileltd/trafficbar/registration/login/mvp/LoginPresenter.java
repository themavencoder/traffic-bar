/*
 * *
 * Creator: Tobiloba Adejumo on 2/26/19 1:21 PM Last modified: 2/26/19 1:21 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.login.mvp;

import com.getmobileltd.trafficbar.registration.register.model.User;

/**
 * Created by themavencoder on 26,February,2019
 */
public class LoginPresenter implements LoginContract.Presenter {
    User user;
    private LoginContract.View view;
    public LoginPresenter(LoginContract.View view) {
        this.view  = view;
        user = new User();
    }
    @Override
    public boolean checkParameters() {
        String email = user.getEmailAddress();
        String password = user.getPassword();
        return !email.isEmpty() && !password.isEmpty();
    }

    @Override
    public void navigateToNextActivity() {
        view.buttonClick();

    }

    @Override
    public String email() {
        return user.getEmailAddress();
    }

    @Override
    public String password() {
        return user.getPassword();
    }

    @Override
    public void setError() {
        view.showError("There was a problem logging you in");
    }

    @Override
    public void saveLoginCredentials(String email, String password) {
        user.setEmailAddress(email);
        user.setPassword(password);
    }
}
