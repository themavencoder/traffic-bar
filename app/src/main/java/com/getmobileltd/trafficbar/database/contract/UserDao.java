/*
 * *
 * Creator: Tobiloba Adejumo on 4/23/19 1:35 PM Last modified: 4/23/19 1:35 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.database.contract;

import android.provider.ContactsContract;

import com.getmobileltd.trafficbar.database.model.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by themavencoder on 23,April,2019
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT apiKey FROM user_table WHERE id = 1")
    String getApiKey();

    @Query("SELECT firstName FROM user_table WHERE id = 1")
    String getFirstName();

    @Query("SELECT lastName FROM user_table WHERE id = 1")
    String getLastName();

    @Query("SELECT userId FROM user_table WHERE id = 1")
    int getUserId();
}
