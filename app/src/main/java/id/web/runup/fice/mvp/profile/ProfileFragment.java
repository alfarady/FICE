package id.web.runup.fice.mvp.profile;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;

import id.web.runup.fice.R;
import id.web.runup.fice.helpers.PathUtil;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements IProfileView{

    TextView mTxtName, mTxtEmail, mBtnCv;
    Button mBtnEdit, mBtnSave;
    EditText mEtEmail, mEtPassword, mEtMsisdn, mEtFName, mEtAge, mEtCountry;
    Boolean editable = false;
    RoundedImageView mProfileDp;
    ProgressBar mProgressBar;
    SwipeRefreshLayout refreshProfile;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    String mediaPath, mediaPath2;

    ProfilePresenter mPresenter = new ProfilePresenter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null);

        mTxtName = v.findViewById(R.id.txtName);
        mTxtEmail = v.findViewById(R.id.txtEmail);
        mEtEmail = v.findViewById(R.id.etEmail);
        mEtPassword = v.findViewById(R.id.etPassword);
        mEtMsisdn = v.findViewById(R.id.etMsisdn);
        mEtFName = v.findViewById(R.id.etfName);
        mEtAge = v.findViewById(R.id.etAge);
        mEtCountry = v.findViewById(R.id.etCountry);
        mBtnCv = v.findViewById(R.id.tvUploadCV);
        mBtnEdit = v.findViewById(R.id.btnEdit);
        mBtnSave = v.findViewById(R.id.btnSave);
        mProfileDp = v.findViewById(R.id.profileDp);
        mProgressBar = v.findViewById(R.id.profileProgressBar);
        refreshProfile = v.findViewById(R.id.refreshProfile);

        this.initListener();
        mPresenter.onCreate(getActivity().getIntent());

        return v;
    }

    private void initListener(){
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editable){
                    editable = true;
                    mEtEmail.setEnabled(true);
                    mEtPassword.setEnabled(true);
                    mEtMsisdn.setEnabled(true);
                    mEtFName.setEnabled(true);
                    mEtAge.setEnabled(true);
                    mEtCountry.setEnabled(true);
                    mBtnEdit.setEnabled(false);
                    mBtnEdit.setBackground(getContext().getDrawable(R.drawable.bg_button_disable));
                    mBtnSave.setVisibility(View.VISIBLE);
                } else {
                    mBtnEdit.setBackground(getContext().getDrawable(R.drawable.bg_button_red));
                    mBtnSave.setVisibility(View.GONE);
                }
            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doRegister(mEtEmail.getText().toString().trim(), mEtPassword.getText().toString().trim(), mEtMsisdn.getText().toString().trim(),
                        mEtFName.getText().toString().trim(), mEtAge.getText().toString().trim(), mEtCountry.getText().toString().trim());
            }
        });

        refreshProfile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setDataHome();
            }
        });
        mProfileDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mBtnCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("*/*");
                        String[] mimetypes = {"image/*", "application/pdf"};
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                        startActivityForResult(intent, 2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 1);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                // Set the Image in ImageView for Previewing the Media
                //imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                mPresenter.uploadFile(mediaPath);
            } else if (requestCode == 2 && resultCode == RESULT_OK && null != data){
                // Get the Image from data
                Uri selectedImageURI = data.getData();
                mediaPath2 = PathUtil.getPath(getContext(),selectedImageURI);
                mPresenter.uploadCv(mediaPath2);
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    @Override
    public void initDataProfile(String email, String password, String msisdn, String fname, String age, String country, String ava_url){
        mProgressBar.setVisibility(View.GONE);
        refreshProfile.setRefreshing(false);
        final String firstName;
        if(fname.contains(" ")){
            String temp[] = fname.split(" ");
            firstName = temp[0];
        } else {
            firstName = fname;
        }

        mTxtName.setText(firstName);
        mTxtEmail.setText(email);

        mEtEmail.setText(email);
        mEtPassword.setText(password);
        mEtMsisdn.setText(msisdn);
        mEtFName.setText(fname);
        mEtAge.setText(age);
        mEtCountry.setText(country);
        if(!ava_url.equals("default.jpg")) {
            Glide.with(getContext())
                    .asBitmap()
                    .load(ava_url)
                    .into(mProfileDp);
        }
    }

    @Override
    public void showMsg(String errorMsg) {
        if (errorMsg == null || errorMsg.isEmpty()) return;
        Snackbar sb = Snackbar.make(getActivity().findViewById(android.R.id.content), errorMsg, Snackbar.LENGTH_LONG);
        View sbView = sb.getView();
        sbView.setBackgroundColor(Color.WHITE);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        sb.show();
    }

    @Override
    public void setFocus(String what) {
        switch (what){
            case "email": mEtEmail.requestFocus();
                break;
            case "password": mEtPassword.requestFocus();
                break;
            case "msisdn": mEtMsisdn.requestFocus();
                break;
            case "age": mEtAge.requestFocus();
                break;

        }
    }

    @Override
    public void setDone(){
        mProgressBar.setVisibility(View.VISIBLE);
        mBtnEdit.setBackground(getContext().getDrawable(R.drawable.bg_button_red));
        mBtnSave.setVisibility(View.GONE);
        mPresenter.setDataHome();
        mEtEmail.setEnabled(false);
        mEtPassword.setEnabled(false);
        mEtMsisdn.setEnabled(false);
        mEtFName.setEnabled(false);
        mEtAge.setEnabled(false);
        mEtCountry.setEnabled(false);
        mBtnEdit.setEnabled(true);
    }

    @Override
    public void startWelcomeActivity() {
        startActivity(new Intent(getActivity(), WelcomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void stopProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
