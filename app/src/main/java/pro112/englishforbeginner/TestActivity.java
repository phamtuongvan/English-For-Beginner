package pro112.englishforbeginner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import info.hoang8f.widget.FButton;
import pro112.ItemAdapter;
import pro112.SQLite.Trans_DAO;
import pro112.SQLite.Vocabulary_DAO;
import pro112.model.Transcript;
import pro112.model.Vocabulary;
import pro112.theme.SharedPreferencesManager;
import pro112.theme.Utils;

public class TestActivity extends Activity implements View.OnClickListener {
    int topic;
    FButton btnA, btnB, btnC, btnD;
    List<Vocabulary> voca_by_topic;
    List<Vocabulary> voca_all = new ArrayList<>();
    Vocabulary_DAO vocaDAO;
    Trans_DAO transDao;
    List<Integer> rdList_by_topic;
    List<Button> btnList;
    ImageView imgTest;
    ImageButton btnTest;
    TextView txtTest;
    int id1;
    int y = 0;
    Random rd = new Random();
    ArrayList<Vocabulary> remember = new ArrayList<>();
    ArrayList<Vocabulary> notremember = new ArrayList<>();
    private ProgressBar progressBar;
    private int progressStatus = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.changeToTheme(new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_BLUE));
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_test);
        navigationBarStatusBar();

        Intent intent = getIntent();
        topic = intent.getExtras().getInt("topic");
        vocaDAO = new Vocabulary_DAO(this);
        transDao = new Trans_DAO(this);
        voca_by_topic = vocaDAO.getVocabularyByTopic(topic);
        voca_all = vocaDAO.getVocabulary(topic);
        rdList_by_topic = new ArrayList();
        RandomVocaByTopic(voca_by_topic, rdList_by_topic);
        RandomListbyTopic();
        RandomVocaListAll();
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        btnA = (FButton) findViewById(R.id.btnA);
        btnB = (FButton) findViewById(R.id.btnB);
        btnC = (FButton) findViewById(R.id.btnC);
        btnD = (FButton) findViewById(R.id.btnD);
        imgTest = (ImageView) findViewById(R.id.imgTest);
        btnTest = (ImageButton) findViewById(R.id.btnTest);
        txtTest = (TextView) findViewById(R.id.txtTest);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        btnList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                btnList.add(btnA);
            } else if (i == 1) {
                btnList.add(btnB);
            } else if (i == 2) {
                btnList.add(btnC);
            } else if (i == 3) {
                btnList.add(btnD);
            }
        }
        RandomBtn();
        SetData(y);
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
    public void SetData(int y) {
        RandomBtn();
        RandomVocaListAll();
        btnList.get(0).setText(voca_all.get(rd.nextInt(voca_all.size())).getVoca_Eng());
        btnList.get(1).setText(voca_all.get(rd.nextInt(voca_all.size())).getVoca_Eng());
        btnList.get(2).setText(voca_all.get(rd.nextInt(voca_all.size())).getVoca_Eng());
        btnList.get(3).setText(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Eng());
        int id = getResources().getIdentifier(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Image(), "drawable", getPackageName());
        imgTest.setImageResource(id);
        txtTest.setText(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Vie());
        id1 = getResources().getIdentifier(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Audio(), "raw", getPackageName());
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), id1);
                mp.start();
            }
        });
        progressStatus = y + 1;
        progressBar.setProgress(progressStatus);
    }

    public void RandomVocaByTopic(List inlist, List<Integer> outlist) {
        for (int i = 0; i < inlist.size(); i++) {
            outlist.add(i);
        }
        Collections.shuffle(outlist);
    }

    public List RandomListbyTopic() {
        Collections.shuffle(voca_by_topic);
        return voca_by_topic;
    }

    public List RandomVocaListAll() {
        Collections.shuffle(voca_all);
        return voca_all;
    }

    public List RandomBtn() {
        Collections.shuffle(btnList);
        return btnList;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure stop this test?")
                .setNegativeButton("Continue", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        TestActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    public void onTrue(View v) {

        final FButton Tr = (FButton) findViewById(v.getId());
        Tr.setButtonColor(getResources().getColor(R.color.fbutton_color_nephritis));
        Tr.setShadowColor(getResources().getColor(R.color.fbutton_color_emerald));
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (y < 19) {
                    remember.add(voca_by_topic.get(rdList_by_topic.get(y)));
                    y++;
                    SetData(y);
                    if (y == 19) {
                        remember.add(voca_by_topic.get(rdList_by_topic.get(y)));
                        y++;
                    }
                }
                if (y < 10) {
                    btnTest.setVisibility(View.GONE);
                    txtTest.setVisibility(View.GONE);
                } else if (y < 15) {
                    imgTest.setVisibility(View.GONE);
                    btnTest.setVisibility(View.VISIBLE);
                } else if (y <= 19) {
                    btnTest.setVisibility(View.GONE);
                    txtTest.setVisibility(View.VISIBLE);
                }
                btnA.setEnabled(true);
                btnB.setEnabled(true);
                btnC.setEnabled(true);
                btnD.setEnabled(true);
                Tr.setButtonColor(getResources().getColor(R.color.fbutton_color_peter_river));
                Tr.setShadowColor(getResources().getColor(R.color.blue_light));
            }
        }, 800);

    }

    public void onFail(View v) {
        final FButton Tr = (FButton) findViewById(v.getId());
        Tr.setButtonColor(getResources().getColor(R.color.redy_dark));
        Tr.setShadowColor(getResources().getColor(R.color.red));
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (y < 19) {
                    notremember.add(voca_by_topic.get(rdList_by_topic.get(y)));
                    y++;
                    SetData(y);
                    if (y == 19) {
                        notremember.add(voca_by_topic.get(rdList_by_topic.get(y)));
                        y++;
                    }
                }
                if (y < 10) {
                    btnTest.setVisibility(View.GONE);
                    txtTest.setVisibility(View.GONE);
                } else if (y < 15) {
                    imgTest.setVisibility(View.GONE);
                    btnTest.setVisibility(View.VISIBLE);
                } else if (y <= 19) {
                    btnTest.setVisibility(View.GONE);
                    txtTest.setVisibility(View.VISIBLE);
                }
                btnA.setEnabled(true);
                btnB.setEnabled(true);
                btnC.setEnabled(true);
                btnD.setEnabled(true);
                Tr.setButtonColor(getResources().getColor(R.color.fbutton_color_peter_river));
                Tr.setShadowColor(getResources().getColor(R.color.blue_light));
            }
        }, 800);

    }

    public void finish() {
        AlertDialog.Builder bui = new AlertDialog.Builder(this);
        final float mark = (float) remember.size() * 10 / 20;
        LayoutInflater inf = this.getLayoutInflater();
        View view = inf.inflate(R.layout.voca_rem, null);
        ListView lvRem = (ListView) view.findViewById(R.id.lvRemem);
        ListView lvNotRemem = (ListView) view.findViewById(R.id.lvNotRemem);
        final TextView txtResult = (TextView)view.findViewById(R.id.txtResult);
        txtResult.setText("Corect " + remember.size() + " / 20 . Score : " + mark);
        ItemAdapter adapter = new ItemAdapter(view.getContext(), R.layout.voca_item, remember);
        lvRem.setAdapter(adapter);
        ItemAdapter adapter1 = new ItemAdapter(view.getContext(), R.layout.voca_item, notremember);
        lvNotRemem.setAdapter(adapter1);
        bui.setView(view).setTitle("Complete the Test !");
        bui.setNeutralButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        Transcript trans = new Transcript();
                trans.setTrans_mark(String.valueOf(mark));
                trans.setTopic_ID(topic);
                transDao.insert(trans);
                Intent i = new Intent(TestActivity.this, TranscriptActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
            }
        }).setPositiveButton("Don't Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(TestActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
            }
        });
        bui.show();
    }

    @Override
    public void onClick(View v) {
        if (y > 19) {
            finish();
        } else {
            MediaPlayer mp = MediaPlayer.create(getBaseContext(), id1);
            mp.start();
            switch (v.getId()) {
                case R.id.btnA:
                    if (btnA.getText().toString().equalsIgnoreCase(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Eng())) {
                        onTrue(v);
                    } else {
                        onFail(v);
                    }
                    break;
                case R.id.btnB:
                    if (btnB.getText().toString().equalsIgnoreCase(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Eng())) {
                        onTrue(v);
                    } else {
                        onFail(v);
                    }
                    break;
                case R.id.btnC:
                    if (btnC.getText().toString().equalsIgnoreCase(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Eng())) {
                        onTrue(v);
                    } else {
                        onFail(v);
                    }
                    break;
                case R.id.btnD:
                    if (btnD.getText().toString().equalsIgnoreCase(voca_by_topic.get(rdList_by_topic.get(y)).getVoca_Eng())) {
                        onTrue(v);
                    } else {
                        onFail(v);
                    }
                    break;
            }
        }
    }

}
