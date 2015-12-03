package pro112.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.hoang8f.widget.FButton;
import pro112.SQLite.Vocabulary_DAO;
import pro112.englishforbeginner.R;
import pro112.englishforbeginner.TestActivity;
import pro112.model.Vocabulary;
import pro112.theme.Utils;

/**
 * Created by nhan on 31/10/15.
 */
public class FragVocabulary extends Fragment implements View.OnClickListener {
    List<Vocabulary> vocaList;
    Vocabulary_DAO vocaDAO;
    int i = 0;
    TextView txtEng, txtVie;
    ImageButton btnSound;
    ImageView imgVoca;
    FButton btnPre, btnNext, btnFinish;
    int topic;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_vocabulary, container, false);
        Utils.onActivityCreateSetTheme(getActivity());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            topic = bundle.getInt("index", 1);
        }
        vocaDAO = new Vocabulary_DAO(getActivity());
        vocaList = vocaDAO.getVocabularyByTopic(topic);

        txtEng = (TextView) v.findViewById(R.id.txtEng);
        txtVie = (TextView) v.findViewById(R.id.txtVie);
        imgVoca = (ImageView) v.findViewById(R.id.imgVoca);

        int id = getResources().getIdentifier(vocaList.get(i).getVoca_Image(), "drawable", getActivity().getPackageName());

        imgVoca.setImageResource(id);
        txtEng.setText(vocaList.get(i).getVoca_Eng());
        txtVie.setText(vocaList.get(i).getVoca_Vie());
        btnPre = (FButton) v.findViewById(R.id.btnPre);
        btnPre.setVisibility(View.INVISIBLE);
        btnPre.setEnabled(false);
        btnNext = (FButton) v.findViewById(R.id.btnNext);
        btnFinish = (FButton) v.findViewById(R.id.btnFinish);
        btnSound = (ImageButton) v.findViewById(R.id.btnSound);
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
        final int id1 = getResources().getIdentifier(vocaList.get(i).getVoca_Audio(), "raw", getActivity().getPackageName());
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), id1);
                mp.start();
            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                i++;
                break;
            case R.id.btnPre:
                i--;
                break;
            case R.id.btnFinish:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Would you like to test the Topic?")
                        .setNegativeButton("Study again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                i=0;
                            }
                        })
                        .setPositiveButton("Test", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(getActivity(), TestActivity.class);
                                Bundle b = new Bundle();
                                b.putInt("topic", topic);
                                i.putExtras(b);
                                startActivity(i);
                                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.change_layout);
                            }
                        }).create().show();
                /*AlertDialog.Builder dia = new AlertDialog.Builder(getActivity());
                Dialog d = dia.setView(new View(getActivity())).setTitle("Would you like to test the Topic?")
                    .setNegativeButton("Study again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            i=0;
                        }
                    })
                    .setPositiveButton("Test", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent i = new Intent(getActivity(), TestActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("topic", topic);
                            i.putExtras(b);
                            startActivity(i);
                        }
                    }).create();
                d.show();*/
                break;
        }
        txtEng.setText(vocaList.get(i).getVoca_Eng());
        txtVie.setText(vocaList.get(i).getVoca_Vie());
        int id = getResources().getIdentifier(vocaList.get(i).getVoca_Image(), "drawable", getActivity().getPackageName());
        imgVoca.setImageResource(id);
        final int id1 = getResources().getIdentifier(vocaList.get(i).getVoca_Audio(), "raw", getActivity().getPackageName());
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity(), id1);
                mp.start();
            }
        });
        if (i == 0) {
            btnPre.setVisibility(View.INVISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnPre.setEnabled(false);
        } else {
            btnPre.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnPre.setEnabled(true);
        }
        if (i == vocaList.size() - 1) {
            btnNext.setVisibility(View.GONE);
            btnNext.setEnabled(false);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnNext.setEnabled(true);
        }
    }
}
