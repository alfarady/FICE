package id.web.runup.fice.mvp.register;

import android.widget.EditText;

public interface IRegisterView {
    void showMainActivity();

    void startLoading();
    void stopLoading();

    void showMsg(String errorMsg);

    void setFocus(String what);

}
