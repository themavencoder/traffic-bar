/*
 * *
 * Creator: Tobiloba Adejumo on 5/12/19 5:38 AM Last modified: 5/12/19 5:38 AM Copyright: All rights reserved Ⓒ 2019
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

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.EmailValidator;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonResetPassword;
    private EditText mEditTextEmailAddress;
    private TrafficBarService trafficBarService;
    private Call<ResetPasswordResponse> resetPasswordResponseCall;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        init();
    }

    private void init() {
        mButtonResetPassword = findViewById(R.id.button_reset_password);
        mEditTextEmailAddress = findViewById(R.id.edit_text_email_address);
        mButtonResetPassword.setOnClickListener(this);
        frameLayout = findViewById(R.id.progress_view);
    }

    @Override
    public void onClick(View v) {
        String emailAddress = mEditTextEmailAddress.getText().toString();
        ResetUser resetUser = new ResetUser(emailAddress);
        if (!EmailValidator.isValidEmail(emailAddress)) {
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
        } else {
            frameLayout.setVisibility(View.VISIBLE);
            resetPassword(resetUser);
        }
        
    }

    private void resetPassword(ResetUser user) {


        resetPasswordResponseCall = trafficBarService.resetPassword(user);
        resetPasswordResponseCall.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    frameLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(ForgotPasswordActivity.this, "A reset link has been sent to your email!", Toast.LENGTH_LONG).show();
                } else if (response.body().getCode() == 404) {
                    frameLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(ForgotPasswordActivity.this, "No user is linked with this email address! Try again", Toast.LENGTH_LONG).show();
                } else {
                    frameLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(ForgotPasswordActivity.this, "Unknown problem occurred", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                frameLayout.setVisibility(View.INVISIBLE);
                Toast.makeText(ForgotPasswordActivity.this, "Limited network connection!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
