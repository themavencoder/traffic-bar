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

import com.getmobileltd.trafficbar.database.OnRetrieveFirstName;
import com.getmobileltd.trafficbar.database.OnRetrieveLastName;
import com.getmobileltd.trafficbar.database.OnRetrieveUserApi;
import com.getmobileltd.trafficbar.database.OnRetrieveUserId;
import com.getmobileltd.trafficbar.database.UserDatabase;
import com.getmobileltd.trafficbar.database.contract.UserDao;
import com.getmobileltd.trafficbar.database.model.User;

/**
 * Created by themavencoder on 23,April,2019
 */
public class UserRepository {
    private UserDao userDao;


    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
        Log.d("INSERT", "Successfully inserted");

    }

    public void getApikey(OnRetrieveUserApi onRetrieveUserApi) {
        new GetApiKeyAsyncTask(userDao,onRetrieveUserApi).execute();
    }

   public void getUserId(OnRetrieveUserId onRetrieveUserId) {
        new GetUserIdAsyncTask(userDao,onRetrieveUserId).execute();

   }

    public void getFirstName(OnRetrieveFirstName onRetrieveFirstName) {
        new GetFirstNameAsyncTask(userDao,onRetrieveFirstName).execute();

    }
    public void getLastName(OnRetrieveLastName onRetrieveLastName) {
        new GetLastNameAsyncTask(userDao,onRetrieveLastName).execute();
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

    public static class GetUserIdAsyncTask extends AsyncTask<Void,Void,Integer> {
private static UserDao userDao;
private OnRetrieveUserId onRetrieveUserId;

      GetUserIdAsyncTask(UserDao userDao, OnRetrieveUserId onRetrieveUserId) {
          this.onRetrieveUserId = onRetrieveUserId;
          this.userDao = userDao;
      }
        @Override
        protected Integer doInBackground(Void... voids) {
            return userDao.getUserId();
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
           onRetrieveUserId.getUserId(s);
        }
    }
    public static class GetFirstNameAsyncTask extends AsyncTask<Void, Void, String> {

        private static UserDao userDao;
        private OnRetrieveFirstName onRetrieveFirstName;


        public GetFirstNameAsyncTask(UserDao userDao, OnRetrieveFirstName onRetrieveFirstName) {

            this.userDao  =userDao;
            this.onRetrieveFirstName = onRetrieveFirstName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {

            return userDao.getFirstName();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onRetrieveFirstName.getFirstName(s);
        }
    }
    public static class GetLastNameAsyncTask extends AsyncTask<Void, Void, String> {

        private static UserDao userDao;
        private OnRetrieveLastName onRetrieveLastName;


        public GetLastNameAsyncTask(UserDao userDao, OnRetrieveLastName onRetrieveLastName) {

            this.userDao  =userDao;
            this.onRetrieveLastName = onRetrieveLastName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {

            return userDao.getLastName();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            onRetrieveLastName.getLastName(s);
        }
    }
    public static class GetApiKeyAsyncTask extends AsyncTask<Void, Void, String> {

        private static UserDao userDao;
        private OnRetrieveUserApi onRetrieveUserApi;


        public GetApiKeyAsyncTask(UserDao userDao, OnRetrieveUserApi onRetrieveUserApi) {

            this.userDao  =userDao;
            this.onRetrieveUserApi = onRetrieveUserApi;
        }


        @Override
        protected String doInBackground(Void... voids) {

            return userDao.getApiKey();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onRetrieveUserApi.pnRetrieveUserFinish(s);
        }
    }

}
