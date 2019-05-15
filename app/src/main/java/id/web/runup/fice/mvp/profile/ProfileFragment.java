package id.web.runup.fice.mvp.profile;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.web.runup.fice.R;

public class ProfileFragment extends Fragment implements IProfileView{

    TextView mTxtName, mTxtEmail, mBtnCv;
    Button mBtnEdit, mBtnSave;
    EditText mEtEmail, mEtPassword, mEtMsisdn, mEtFName, mEtAge, mEtCountry;
    Boolean editable = false;

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
        mBtnEdit.setBackground(getContext().getDrawable(R.drawable.bg_button_red));
        mBtnSave.setVisibility(View.GONE);
    }
}
