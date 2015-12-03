package pro112.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import pro112.englishforbeginner.MainActivity;
import pro112.englishforbeginner.R;
import pro112.theme.SharedPreferencesManager;
import pro112.theme.Utils;

/**
 * Created by ThanhMinh on 11/20/2015.
 */
public class SplScr extends Activity {
    int t = 3000;
    ImageView i;
    TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeToTheme(new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_BLUE));
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.splscr);
        navigationBarStatusBar();
        TypedValue typedValue21 = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue21, true);
        final int color = typedValue21.data;
        i = (ImageView) findViewById(R.id.img);
        tv = (TextView) findViewById(R.id.tv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            i.setBackgroundTintList(ColorStateList.valueOf(color));
            tv.setTextColor(ColorStateList.valueOf(color));
        }
        Animation b = AnimationUtils.loadAnimation(this, R.anim.translate);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.anim);
        i.startAnimation(a);
        tv.setAnimation(b);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplScr.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
            }
        }, t);
    }
    public void navigationBarStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            TypedValue typedValue19 = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue19, true);
            final int color = typedValue19.data;
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintColor(color);
            tintManager.setNavigationBarTintColor(color);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            TypedValue typedValue21 = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
            final int color = typedValue21.data;
            window.setStatusBarColor(color);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintColor(color);
            tintManager.setNavigationBarTintColor(getResources().getColor(R.color.blue_dark));
            tintManager.setNavigationBarTintColor(color);
        }
    }
}
