package pro112.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import pro112.ItemAdapter;
import pro112.SQLite.Topic_DAO;
import pro112.SQLite.Vocabulary_DAO;
import pro112.englishforbeginner.R;
import pro112.model.Topic;
import pro112.model.Vocabulary;

/**
 * Created by nhan on 31/10/15.
 */
public class MNG_Voca extends Fragment {
    private static final Bundle PICK_FROM_FILE = null ;
    private static final int RESULT_AUDIO = 5;
    private static int RESULT_LOAD = 1;
    String img,path;
    View v;
    Vocabulary_DAO Voca_DAO;
    ListView lv_Myvocab;
    ItemAdapter adapter;
    Topic_DAO topDAO;
    Spinner sp;
    List<String> add;
    List<Topic> topic;
    TextView ChooseAudio;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mng_voca, container, false);
        try {
            lv_Myvocab = (ListView) v.findViewById(R.id.listView2);
            topDAO = new Topic_DAO(getActivity());
            Voca_DAO = new Vocabulary_DAO((getActivity()));
            ChooseAudio = (TextView)v.findViewById(R.id.chooseAudio);
            print();
            FloatingActionButton bt = (FloatingActionButton) v.findViewById(R.id.btn_add);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Custom_dialog();
                }
            });
        }catch (Exception e){
        }
        return v;
    }

    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD);
    }

    public void print() {
        List<Vocabulary> list = Voca_DAO.getVocabularyAll();
        Toast.makeText(getActivity(),list.size()+"" , Toast.LENGTH_SHORT).show();
        adapter = new ItemAdapter(getActivity(),R.layout.voca_item, list);
        lv_Myvocab.setAdapter(adapter);
    }

    public void Custom_dialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        v = inf.inflate(R.layout.dialog_voca, null);
        b.setView(v);
        Button b1 = (Button) v.findViewById(R.id.btnChooseAudio);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectSound();
            }
        });
        Button b2 = (Button) v.findViewById(R.id.btnChoosePhoto);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery();
            }
        });
        sp = (Spinner)v.findViewById(R.id.spinnerTop);
        add = topDAO.getTopicName();
        topic = topDAO.getTopic();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,add);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp.setAdapter(adapter);
        b.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                MaterialEditText e1 = (MaterialEditText) v.findViewById(R.id.eng);
                MaterialEditText e2 = (MaterialEditText) v.findViewById(R.id.vi);
                if (e1.getText().toString().equals("") || e2.getText().toString().equals("") || img.equals("") || path.equals("")) {
                    Toast.makeText(getActivity(), "Everything not be emty", Toast.LENGTH_SHORT).show();
                } else {
                    Vocabulary voca = new Vocabulary();
                    voca.setVoca_Eng(e1.getText().toString());
                    voca.setVoca_Vie(e2.getText().toString());
                    voca.setVoca_Image(img);
                    voca.setVoca_Audio(path);
                    voca.setTopic_ID(topic.get(sp.getSelectedItemPosition()).getTopic_ID());
                    Voca_DAO.insert(voca);
                    Fragment fr = new MNG_Voca();
                    FragmentManager fm =
                            getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.abc, 0, 0, R.anim.change).replace(R.id.content, fr).commit();
                }
                dialog.cancel();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        b.show();
    }

    public void CustomDialog_edit (final Vocabulary v) {
        AlertDialog.Builder bui = new AlertDialog.Builder(getActivity());

        LayoutInflater inf = getActivity().getLayoutInflater();
        this.v = inf.inflate(R.layout.dialog_voca, null);
        bui.setView(this.v);
        MaterialEditText e1 = (MaterialEditText) this.v.findViewById(R.id.eng);
        e1.setText(v.getVoca_Eng() + "");
        MaterialEditText e2 = (MaterialEditText) this.v.findViewById(R.id.vi);
        e2.setText(v.getVoca_Vie() + "");

        bui.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                MaterialEditText e1 = (MaterialEditText) MNG_Voca.this.v.findViewById(R.id.txt_MyVocab_Eng);
                MaterialEditText e2 = (MaterialEditText) MNG_Voca.this.v.findViewById(R.id.txt_MyVocab_Vi);

                if (e1.getText().toString().equals("") && e2.getText().toString().equals("")) {
                    CustomDialog_edit(v);
                    Toast.makeText(getActivity(), "Text not be emty", Toast.LENGTH_SHORT).show();
                }
                if (e1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Text not be emty", Toast.LENGTH_SHORT).show();
                }
                if (e2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Text not be emty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Edited", Toast.LENGTH_SHORT).show();

                    Voca_DAO = new Vocabulary_DAO(getActivity());
                    String text1 = e1.getText().toString();
                    String text2 = e2.getText().toString();
                    v.setVoca_Eng(text1);
                    v.setVoca_Eng(text2);

                    Voca_DAO.update(v);
                    print();
                    dialog.cancel();
                }
            }
        });
        bui.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

        bui.show();
    }

    public void SelectSound() {
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, RESULT_AUDIO);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            startActivityForResult(intent, RESULT_AUDIO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == RESULT_AUDIO && null != data){
                Uri audio  = data.getData();
                path = String.valueOf(audio);
                ChooseAudio = (TextView)v.findViewById(R.id.chooseAudio);
                ChooseAudio.setText("File selected");
            }} catch (Exception e) {
            Toast.makeText(getActivity(), "Something went embrassing", Toast.LENGTH_LONG)
                    .show();
        }

        try {

            if (requestCode == RESULT_LOAD && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                img = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) v.findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(img));
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went embrassing", Toast.LENGTH_LONG).show();
        }

    }

}
