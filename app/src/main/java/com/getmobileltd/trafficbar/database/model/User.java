/*
 * *
 * Creator: Tobiloba Adejumo on 4/23/19 1:29 PM Last modified: 4/23/19 1:29 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.database.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by themavencoder on 23,April,2019
 */
@Entity(tableName = "user_table" , indices = {@Index(value = {"id","apiKey"}, unique = true)} )
public class User {

    @PrimaryKey
    private int id;
    private String apiKey;
    private String firstName;
    private String lastName;
    private int userId;


    public User(int id, String apiKey,String firstName, String lastName, int userId) {
        this.id = id;
        this.apiKey = apiKey;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
