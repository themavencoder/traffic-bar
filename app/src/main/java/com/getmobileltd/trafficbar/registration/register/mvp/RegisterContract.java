/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 5:18 PM Last modified: 2/25/19 5:18 PM Copyright: All rights reserved Ⓒ 2019
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

import androidx.databinding.ObservableField;

/**
 * Created by themavencoder on 25,February,2019
 */
public interface RegisterContract {
    interface View {
        void showError(String message);
        void buttonClick();

    }
    interface Presenter {
        boolean checkParameters();
        void navigateToNextActivity();
        void saveName(String firstName, String lastName);
        String firstName();
        String lastName();
        void setError();


    }
}
