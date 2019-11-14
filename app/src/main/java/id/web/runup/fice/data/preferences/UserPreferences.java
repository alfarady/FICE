package id.web.runup.fice.data.preferences;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class UserPreferences implements IUserPreferences {
    protected SharedPreferences mPreferences;

    public UserPreferences() {
        this.mPreferences = PreferencesProvider.providePreferences();
    }

    @Override
    public void setUserToken(String token) {
        mPreferences.edit().putString("token", token).apply();
    }

    @Override
    public String getUserToken() {
        return mPreferences.getString("token", "YWRtaW46QUxGQVJBRFkwOTA5");
    }

    @Override
    public void unSetUserToken() {
        mPreferences.edit().putString("token", "YWRtaW46QUxGQVJBRFkwOTA5").apply();
    }

    @Override
    public void setRoles(String roles) {
        mPreferences.edit().putString("roles", roles).apply();
    }

    @Override
    public String getRoles() {
        return mPreferences.getString("roles", "worker");
    }

    @Override
    public void unSetRoles() {
        mPreferences.edit().putString("roles", "worker").apply();
    }

    @Override
    public void setNotifReaded(int id, Boolean readed) {
        mPreferences.edit().putBoolean("notifReadedId"+id, readed).apply();
    }

    @Override
    public Boolean getNotifReaded(int id) {
        return mPreferences.getBoolean("notifReadedId"+id, false);
    }

    @Override
    public void unSetNotifReaded(int id) {
        mPreferences.edit().putBoolean("notifReadedId"+id, false).apply();
    }

    @Override
    public void setUserNotifToken(String token) {
        mPreferences.edit().putString("notifToken", token).apply();
    }

    @Override
    public String getUserNotifToken() {
        return mPreferences.getString("notifToken", "");
    }

    @Override
    public void unSetNotifUserToken() {
        mPreferences.edit().putString("notifToken", "").apply();
    }

    @Override
    public void setLocation(String coordinate) {
        mPreferences.edit().putString("coordinate", coordinate).apply();
    }

    @Override
    public String getUserLocation() {
        return mPreferences.getString("coordinate", "0, 0");
    }

    @Override
    public void unSetLocation() {
        mPreferences.edit().putString("coordinate", "0, 0").apply();
    }
}
