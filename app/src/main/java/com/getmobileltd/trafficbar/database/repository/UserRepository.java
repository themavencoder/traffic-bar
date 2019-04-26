/*
 * *
 * Creator: Tobiloba Adejumo on 4/23/19 1:55 PM Last modified: 4/23/19 1:55 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.database.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.service.autofill.UserData;
import android.util.Log;
import android.widget.Toast;

import com.getmobileltd.trafficbar.database.AsyncResponse;
import com.getmobileltd.trafficbar.database.UserDatabase;
import com.getmobileltd.trafficbar.database.contract.UserDao;
import com.getmobileltd.trafficbar.database.model.User;

/**
 * Created by themavencoder on 23,April,2019
 */
public class UserRepository {
    private UserDao userDao;
    private String apiKey;
   public static AsyncResponse delegate;

    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();

    //    apiKey = userDao.getApiKey();

    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
        Log.d("INSERT", "Successfully inserted");

    }
    public void getApikey() {
        new GetApiKeyAsyncTask(userDao).execute();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }

    }

    public static class GetApiKeyAsyncTask extends AsyncTask<Void, Void, String> {

        private UserDao userDao;
        private AsyncResponse delegate;

        public GetApiKeyAsyncTask(UserDao userDao) {

            this.userDao  =userDao;
        }


        @Override
        protected String doInBackground(Void... voids) {
            String abc = "abc sample";
            return abc;
            //
            // return userDao.getApiKey();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//          delegate.processFinish(s);
        }
    }

}
