/*
 * *
 * Creator: Tobiloba Adejumo on 2/26/19 1:36 PM Last modified: 2/26/19 1:19 PM Copyright: All rights reserved Ⓒ 2019
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

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.MainActivity;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.registration.login.dialog.LoginDialog;
import com.getmobileltd.trafficbar.registration.login.networkresponse.LogInResponse;
import com.getmobileltd.trafficbar.registration.register.model.User;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private EditText mEditEmail, mEditPassword;
    private Button mButtonLogin;
    private TrafficBarService trafficBarService;
    private Call<LogInResponse> loginCall;
    private LoginContract.Presenter presenter;
    private User user;
    private LoginDialog mLoginDialog;
    private SmoothProgressBar mProgressBar;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UiSettings.fullScreen(this);
        init();
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        mEditEmail = findViewById(R.id.edit_text_email_address);
        mEditPassword = findViewById(R.id.edit_text_password);
        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(this);
        presenter = new LoginPresenter(this);
        mLoginDialog = new LoginDialog();
       // mProgressBar = findViewById(R.id.progress_view);
        frameLayout = findViewById(R.id.progress_view);

      //  frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void buttonClick() {
       Intent intent = new Intent(this,MainActivity.class);
       startActivity(intent);
   //    startActivity(new Intent(this, MainActivity.class));
      //  startActivity(new Intent());

    }

    @Override
    public void onClick(View v) {
            String email = mEditEmail.getText().toString();
            String password = mEditPassword.getText().toString();
            presenter.saveLoginCredentials(email,password);
            if (presenter.checkParameters()) {
              /*  mLoginDialog.setCancelable(false);
                mLoginDialog.show(getSupportFragmentManager(),"my_dialog");*/
            frameLayout.setVisibility(View.VISIBLE);
                user = new User(email,password);
                loginUser(user);
            }

    }

    private void loginUser(User user) {
        loginCall = trafficBarService.logUser(user);
        loginCall.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.code() == 200) {
                           // mLoginDialog.dismiss();
                    frameLayout.setVisibility(View.GONE);
                        presenter.navigateToNextActivity();
                } else {
                  //  mLoginDialog.dismiss();
                    frameLayout.setVisibility(View.GONE);
                    presenter.setError();
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
             //   mLoginDialog.dismiss();
                frameLayout.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
