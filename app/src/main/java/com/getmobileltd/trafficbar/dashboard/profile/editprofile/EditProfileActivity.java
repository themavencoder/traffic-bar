/*
 * *
 * Creator: Tobiloba Adejumo on 3/8/19 12:48 PM Last modified: 3/8/19 12:48 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.profile.editprofile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.database.OnRetrieveUserApi;
import com.getmobileltd.trafficbar.database.OnRetrieveUserId;
import com.getmobileltd.trafficbar.database.repository.UserRepository;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;
    private ImageView mImageViewButton, mImageViewPhoto;
    private Uri imageUri;
    private String firstName, lastName;
    private TextView mTextViewName;
    private EditText mFirstName, mLastName, mEmailAddress, mPassword;
    private Button mButtonSave;
    private TrafficBarService trafficBarService;
    private Call<UserUpdateResponse> userUpdateResponseCall;
    private UserRepository repository;
    private int userId;
    private String databaseApiKey;
    private File file;
    private AppInstance appInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        UiSettings.colorStatusbar(this, R.color.colorAccent);
        appInstance = AppInstance.getInstance();
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        repository = new UserRepository(getApplication());
        repository.getApikey(new OnRetrieveUserApi() {
            @Override
            public void pnRetrieveUserFinish(String apiKey) {
                databaseApiKey = apiKey;
            }
        });
        repository.getUserId(new OnRetrieveUserId() {
            @Override
            public void getUserId(int id) {
                userId = id;
            }
        });
        init();
        Intent intent = getIntent();
        if (getIntent() != null) {
            firstName = intent.getStringExtra("firstNameConstant");
            lastName = intent.getStringExtra("lastNameConstant");
        }
        String fullName = firstName + " " + lastName;
        mTextViewName.setText(fullName);

    }

    private void init() {
        mImageViewButton = findViewById(R.id.imageview_upload_photo);
        mImageViewPhoto = findViewById(R.id.imageview_photo);
        mFirstName = findViewById(R.id.edit_text_first_name);
        mLastName = findViewById(R.id.edit_text_last_name);
        mEmailAddress = findViewById(R.id.edit_text_email_address);
        mPassword = findViewById(R.id.edit_text_password);
        mTextViewName = findViewById(R.id.textview_name);
        mImageViewButton.setOnClickListener(this);
        mButtonSave = findViewById(R.id.button_save_profile);
        mButtonSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_save_profile) {
            sendProfileToServer();
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            goToGallery();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }


    }

    private void sendProfileToServer() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", mFirstName.getText().toString())
                .addFormDataPart("last_name", mLastName.getText().toString())
                .addFormDataPart("email", mEmailAddress.getText().toString())
                .addFormDataPart("password", mPassword.getText().toString())
                .build();
        //   File file = new File(imageUri.toString());

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
        MultipartBody.Part firstName = MultipartBody.Part.createFormData("first_name", mFirstName.getText().toString());
        MultipartBody.Part lastName = MultipartBody.Part.createFormData("last_name", mLastName.getText().toString());
        MultipartBody.Part email = MultipartBody.Part.createFormData("email", mEmailAddress.getText().toString());
        MultipartBody.Part password = MultipartBody.Part.createFormData("password", mPassword.getText().toString());


        userUpdateResponseCall = trafficBarService.updateUser(databaseApiKey, userId, fileToUpload,firstName,lastName);

        userUpdateResponseCall.enqueue(new Callback<UserUpdateResponse>() {
            @Override
            public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {
                if (response.body().getCode() == 401) {
                    Toast.makeText(EditProfileActivity.this, "Unauhorized Request", Toast.LENGTH_SHORT).show();
                } else if (response.body().getCode() == 201) {
                  //  Toast.makeText(EditProfileActivity.this, "This is the image string " + response.body().getData().getPhoto(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditProfileActivity.this, "User Updated successfully", Toast.LENGTH_SHORT).show();
                    appInstance.setImageBaseUrl(response.body().getData().getPhoto());
                } else {
                    Toast.makeText(EditProfileActivity.this, "Error occurred.Try again", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Cannot connect to the internet", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToGallery();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void goToGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        String[] mimeTypes = {"image/jpeg", "image/jpg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    assert data != null;
                   Uri selectedImage = data.getData();
                   mImageViewPhoto.setImageURI(selectedImage);
                    try {
                        Uri photoUri = data.getData();
                        String filePath = getRealPathFromURIPath(photoUri, EditProfileActivity.this);
                        file = new File(filePath);
                        //   file = new File(photoUri.getPath());
                        CropImage.activity(photoUri)
                                .setAspectRatio(1, 1)
                                .start(this);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    break;
                default:
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                imageUri = result.getUri();
                //Intent editImageIntent = new Intent(this, EditImageActivity.class);
                mImageViewPhoto.setImageURI(imageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
