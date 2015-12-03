package pro112.englishforbeginner;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import pro112.SQLite.DbHelper;
import pro112.SQLite.Topic_DAO;
import pro112.SQLite.Vocabulary_DAO;
import pro112.fragment.LetsGo;
import pro112.fragment.MNG_Topic;
import pro112.fragment.MNG_Voca;
import pro112.fragment.MyVocabulary;
import pro112.fragment.Setting;
import pro112.theme.SharedPreferencesManager;
import pro112.theme.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DbHelper db;
    Topic_DAO topDAO;
    Vocabulary_DAO VocaDAO;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeToTheme(new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_BLUE));
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        navigationBarStatusBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DbHelper(getBaseContext());
        topDAO = new Topic_DAO(this);
        VocaDAO = new Vocabulary_DAO(this);

        Fragment fr = new LetsGo();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content, fr).commit();

        TypedValue typedValue21 = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue21, true);
        final int color = typedValue21.data;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                findViewById(R.id.mng_topic).setVisibility(View.GONE);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView img =(ImageView)headerView.findViewById(R.id.imgIcon);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img.setBackgroundTintList(ColorStateList.valueOf(color));
        }
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
            tintManager.setNavigationBarTintColor(color);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                recreate();
                setTitle(R.string.nav_title_a);
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fr = null;
        int id = item.getItemId();

        if (id == R.id.nav_letsgo) {
            fr = new LetsGo();
            setTitle(R.string.nav_title_a);
        } else if (id == R.id.nav_vocabulary) {
            fr = new MyVocabulary();
            setTitle(R.string.nav_title_b);
        } else if (id == R.id.nav_transcript) {
            Intent i = new Intent(MainActivity.this, TranscriptActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
        } else if (id == R.id.mng_topic) {
            fr = new MNG_Topic();
            setTitle(R.string.nav_title_d);
        } else if (id == R.id.mng_voca) {
            fr = new MNG_Voca();
            setTitle(R.string.nav_title_e);
        } else if (id == R.id.nav_setting) {
            fr = new Setting();

        }
        if(fr != null){
            FragmentManager fm =
                    getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.abc, 0, 0, R.anim.change).replace(R.id.content, fr).commit();
            fm.executePendingTransactions();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
