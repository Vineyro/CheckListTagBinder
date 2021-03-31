package llc.arma.checklisttag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Utils {

    /**
     * Генерирует md5 от заданной строки
     * @param md5 заданная строка
     * @return хэш заданной троки
     */
    @Nullable
    public static String MD5(@NotNull String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Получает id os
     * @param context контекст приложения
     * @return id os
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidId(@NotNull Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
