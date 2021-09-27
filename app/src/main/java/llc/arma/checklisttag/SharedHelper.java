package llc.arma.checklisttag;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import llc.arma.checklisttag.data.UserEntity;
import okhttp3.Cookie;

public class SharedHelper {

    private static final String SP_NAME = "SP_NAME";
    private static final String TOKEN = "TOKEN";
    public static final String COOKIES = "COOKIES";
    private static final String USER = "USER";
    public static final String SERVER_ADDRESS = "SERVER_ADDRESS";

    public static String getToken(){
        return getString(TOKEN);
    }

    public static void setToken(String token){
        setString(TOKEN, token);
    }

    public static void setUser(UserEntity user){
        setString(USER, new Gson().toJson(user));
    }

    public static UserEntity getUser(){
        TypeToken<UserEntity> token = new TypeToken<UserEntity>(){};
        return new Gson().fromJson(getString(USER), token.getType());
    }

    public static void saveCookies(List<Cookie> cookies){

        setString(COOKIES, new Gson().toJson(cookies));

    }

    public static List<Cookie> getCookies(){

        return new Gson().fromJson(getString(COOKIES), new TypeToken<List<Cookie>>(){}.getType());

    }

    public static boolean isAuth(){
        return getString(TOKEN) != null && getString(TOKEN).length() > 0;
    }

    public static void saveServerAddress(String path){

        setString(SERVER_ADDRESS, path);

    }

    public static String getServerAddress(){

        String path = getString(SERVER_ADDRESS);

        return path.equals("") ? null : path;
    }

    /**
     * Возвращает bool сохраненную в Prefs'ах
     * @param name имя сохраненного значения
     * @return bool
     */
    private static boolean getBoolean(String name){
        SharedPreferences sharedPreferences = App.getAppComponent().provideContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }

    /**
     * Сохраняет bool в Prefs'ах
     * @param name имя сохраненного значения
     * @param value значение
     */
    private static void setBoolean(String name, boolean value){
        SharedPreferences sharedPreferences =  App.getAppComponent().provideContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    /**
     * Возвращает строчку сохраненную в Prefs'ах
     * @param name имя сохраненного значения
     * @return String
     */
    private static String getString(String name){
        SharedPreferences sharedPreferences = App.getAppComponent().provideContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, "");
    }

    /**
     * Сохраняет строчку в Prefs'ах
     * @param name имя значения
     * @param value само значение
     */
    private static void setString(String name, String value){
        SharedPreferences sharedPreferences =  App.getAppComponent().provideContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

}