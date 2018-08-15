package adel.co.asyst.learnsession.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtils {

    public final String KEY_NAME = "name";
    public final String KEY_ADDRESS = "address";
    public final String KEY_GENDER = "gender";
    public final String KEY_EDU = "edu";
    Context mContext;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionUtils(Context context) {
        this.mContext = context;
        preferences = context.getSharedPreferences("training", 0);

        editor = preferences.edit();
    }

    public void saveName(String name, String address, String gender, String edu) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_EDU, edu);
        editor.commit();
    }

    public String loadName() {

        String name = preferences.getString(KEY_NAME, "");
        return name;
    }

    public String loadAddress() {

        String address = preferences.getString(KEY_ADDRESS, "");
        return address;
    }

    public String loadEdu() {

        String edu = preferences.getString(KEY_EDU, "");
        return edu;
    }

    public String loadGender() {

        String gender = preferences.getString(KEY_GENDER, "");
        return gender;
    }
}
