package id.web.runup.fice.mvp.profile;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.Register;
import id.web.runup.fice.data.models.UpdateUser;
import id.web.runup.fice.data.models.UploadAction;
import id.web.runup.fice.data.models.User;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends AbstractPresenter {
    private IProfileView mView;
    UserPreferences mDatabase = new UserPreferences();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    IApiService mApiClient;

    public ProfilePresenter(IProfileView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        setDataHome();
    }

    public void setDataHome(){
        Call<User> daftarCall = mApiClient.getUser(mDatabase.getUserToken(), mDatabase.getUserLocation());
        daftarCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.initDataProfile(response.body().getEmail(), "", response.body().getPhone(), response.body().getName(), response.body().getAge(), response.body().getCountry(), response.body().getAvaUrl());
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
                mView.stopProgressBar();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void doRegister(String email, String password, String msisdn, String fName, String age, String country) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(msisdn) || TextUtils.isEmpty(fName) || TextUtils.isEmpty(age) || TextUtils.isEmpty(country)){
            this.mView.setFocus("email");
            this.mView.showMsg("You must fill the form above");
        } else if(!email.matches(emailPattern)){
            this.mView.setFocus("email");
            this.mView.showMsg("Invalid email address");
        } else if(password.length() < 6){
            this.mView.setFocus("password");
            this.mView.showMsg("Your password must be at least 6 latters");
        } else if(msisdn.length() < 10){
            this.mView.setFocus("msisdn");
            this.mView.showMsg("Your phone number must be at least 10 numbers");
        } else if(age.length() != 2 && !TextUtils.isDigitsOnly(age)){
            this.mView.setFocus("age");
            this.mView.showMsg("Invalid age number");
        } else {
            this.update(email, password, msisdn, fName, age, country);
        }
    }

    private void update(String email, String password, String msisdn, String fName, String age, String country) {
        Call<UpdateUser> daftarCall = mApiClient.doUpdate(mDatabase.getUserToken(), email, password, msisdn, fName, age, country, mDatabase.getUserNotifToken());
        daftarCall.enqueue(new Callback<UpdateUser>() {
            @Override
            public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showMsg(response.body().getMessage());
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.setDone();
            }

            @Override
            public void onFailure(Call<UpdateUser> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.setDone();
            }
        });
    }

    public void uploadFile(String mediaPath) {
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("input_img", file.getName(), requestBody);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("imageData", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Log.e("keshav","mediaPath         ->  " +mediaPath);
        Log.e("keshav","file         ->  " +file);
        Log.e("keshav","file.getName ->  " +file.getName());
        Log.e("keshav","requestBody  ->  " +requestBody);
        Log.e("keshav","fileToUpload ->  " +fileToUpload);
        Log.e("keshav","filename     ->  " +filename);

        Call<UploadAction> call = mApiClient.uploadAva(mDatabase.getUserToken(), fileToUpload, filename);
//        Call<ServerResponseKeshav> call = getResponse.uploadFile(fileToUpload, filename);

        call.enqueue(new Callback<UploadAction>() {
            @Override
            public void onResponse(Call<UploadAction> call, Response<UploadAction> response)
            {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showMsg(response.body().getMessage());
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.setDone();
            }

            @Override
            public void onFailure(Call<UploadAction> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.setDone();
            }
        });
    }

    public void uploadCv(String mediaPath) {
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("input_cv", file.getName(), requestBody);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("imageData", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Log.e("keshav","mediaPath         ->  " +mediaPath);
        Log.e("keshav","file         ->  " +file);
        Log.e("keshav","file.getName ->  " +file.getName());
        Log.e("keshav","requestBody  ->  " +requestBody);
        Log.e("keshav","fileToUpload ->  " +fileToUpload);
        Log.e("keshav","filename     ->  " +filename);

        Call<UploadAction> call = mApiClient.uploadCv(mDatabase.getUserToken(), fileToUpload, filename);
//        Call<ServerResponseKeshav> call = getResponse.uploadFile(fileToUpload, filename);

        call.enqueue(new Callback<UploadAction>() {
            @Override
            public void onResponse(Call<UploadAction> call, Response<UploadAction> response)
            {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showMsg(response.body().getMessage());
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.setDone();
            }

            @Override
            public void onFailure(Call<UploadAction> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.setDone();
            }
        });
    }
}
