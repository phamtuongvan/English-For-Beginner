package pro112.englishforbeginner;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import pro112.SQLite.Topic_DAO;
import pro112.SQLite.Trans_DAO;
import pro112.TransAdapter;
import pro112.model.Transcript;
import pro112.theme.SharedPreferencesManager;
import pro112.theme.Utils;

public class TranscriptActivity extends AppCompatActivity {
    Trans_DAO transDAO;
    ListView lvTrans;
    List<Transcript> transList;
    Spinner sp;
    Topic_DAO topDAO;
    List<String> add;
    TransAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeToTheme(new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_BLUE));
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_transcript);
        navigationBarStatusBar();
        transDAO = new Trans_DAO(this);
        topDAO = new Topic_DAO(this);
        lvTrans = (ListView) findViewById(R.id.lvTrans);
        sp = (Spinner) findViewById(R.id.spnTopic);
        add = topDAO.getTopicName();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, add);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp.setAdapter(adapter1);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 1:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 2:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 3:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 4:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 5:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                    case 6:
                        transList = transDAO.getTranscriptByTopic(position + 1);
                        adapter = new TransAdapter(getBaseContext(), R.layout.trans_item, transList);
                        lvTrans.setAdapter(adapter);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            tintManager.setTintColor(color);
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
        }
    }
}
