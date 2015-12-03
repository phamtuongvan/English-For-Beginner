package pro112.theme;

import android.app.Activity;
import android.content.Intent;

import pro112.englishforbeginner.R;

public class Utils {
    private static int sTheme;
    public final static int THEME_BLUE = 0;
    public final static int THEME_PURPLE = 1;
    public final static int THEME_RED = 2;
    public final static int THEME_Green = 3;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
    }
    public static void changeToTheme(int theme) {
        sTheme = theme;
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            case THEME_BLUE:
                activity.setTheme(R.style.Theme_Blue);
                break;
            case THEME_PURPLE:
                activity.setTheme(R.style.Theme_Purple);
                break;
            case THEME_RED:
                activity.setTheme(R.style.Theme_Red);
                break;
            case THEME_Green:
                activity.setTheme(R.style.Theme_Green);
                break;
            default:
                activity.setTheme(R.style.Theme_Blue);
                break;
        }
    }
}

