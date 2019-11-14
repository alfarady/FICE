package id.web.runup.fice.data.preferences;

public interface IUserPreferences {
    public void setUserToken(String token);

    public String getUserToken();

    public void unSetUserToken();

    public void setRoles(String roles);

    public String getRoles();

    public void unSetRoles();

    public void setNotifReaded(int id, Boolean readed);

    public Boolean getNotifReaded(int id);

    public void unSetNotifReaded(int id);

    public void setUserNotifToken(String token);

    public String getUserNotifToken();

    public void unSetNotifUserToken();

    public void setLocation(String coordinate);

    public String getUserLocation();

    public void unSetLocation();
}
