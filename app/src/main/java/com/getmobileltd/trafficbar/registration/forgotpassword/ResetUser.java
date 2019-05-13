/*
 * *
 * Creator: Tobiloba Adejumo on 5/13/19 10:25 AM Last modified: 5/13/19 10:25 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.forgotpassword;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 13,May,2019
 */
public class ResetUser {

    public ResetUser(String email) {
        this.email = email;
    }

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
