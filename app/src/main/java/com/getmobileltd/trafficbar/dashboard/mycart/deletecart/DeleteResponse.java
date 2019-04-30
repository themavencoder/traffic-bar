/*
 * *
 * Creator: Tobiloba Adejumo on 4/29/19 3:04 PM Last modified: 4/29/19 3:03 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.deletecart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 29,April,2019
 */
public class DeleteResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
