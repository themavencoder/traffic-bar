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

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.theartofdev.edmodo.cropper.CropImage;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;
    private ImageView mImageViewButton, mImageViewPhoto;
    private Uri imageUri;
    private String firstName, lastName;
    private TextView mTextViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        UiSettings.colorStatusbar(this, R.color.colorAccent);
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
        mTextViewName = findViewById(R.id.textview_name);
        mImageViewButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
     goToGallery();
        } else {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
                   /*Uri selectedImage = data.getData();
                   mImageViewPhoto.setImageURI(selectedImage);*/
                    try {
                        Uri photoUri = data.getData();
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
}
