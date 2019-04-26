/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 2:39 PM Last modified: 3/6/19 2:39 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menulist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by themavencoder on 06,March,2019
 */
public class MenuResponse {
    @SerializedName("status")
     private String status;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Information> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public List<Information> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<Information> data) {
        this.data = data;
    }


}
