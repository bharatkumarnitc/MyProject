package onlinevoting.nitcalicut.onlinevoting.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import onlinevoting.nitcalicut.onlinevoting.MainActivity;
import onlinevoting.nitcalicut.onlinevoting.model.Admin_user;

public class SharedprefHelper {
    private static final String SHARED_PREF_NAME = "admin_user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private static SharedprefHelper mInstance;
    private static Context mCtx;

    public SharedprefHelper(Context context) {
        mCtx = context;
    }

    public static synchronized SharedprefHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedprefHelper(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Admin_user user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID,user.getAdmin_id());
        editor.putString(KEY_NAME, user.getAdmin_name());
        editor.putString(KEY_EMAIL, user.getAdmin_email());
        editor.putString(KEY_PASSWORD,user.getAdmin_pwd());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    //this method will give the logged in user
    public Admin_user getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return new Admin_user(
                sharedPreferences.getString(KEY_ID,null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null)
        );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}
