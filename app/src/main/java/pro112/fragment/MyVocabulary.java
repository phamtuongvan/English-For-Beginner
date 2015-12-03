package pro112.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.ArrayList;

import pro112.MyVocab_Adapter;
import pro112.SQLite.DbHelper;
import pro112.SQLite.MyVocab_DAO;
import pro112.englishforbeginner.R;
import pro112.model.Personal;

/**
 * Created by nhan on 31/10/15.
 */
public class MyVocabulary extends Fragment {
    private static final Bundle PICK_FROM_FILE = null ;
    private static final int RESULTA_AUDIO = 5;
    ListView listview;
    TextView edtChooseAudio;
    private static int RESULT_LOAD = 1;
    String img_Decodable_Str,path;
    View v;
    MyVocab_DAO myDAO;
    DbHelper db;
    ListView lv_Myvocab;
    MyVocab_Adapter adapter;
    Boolean click1 = true;
    String uriStr;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_myvocabulary, container, false);
        lv_Myvocab = (ListView) v.findViewById(R.id.list_MyVocab);
        myDAO = new MyVocab_DAO(getActivity());
        print();
        FloatingActionButton bt = (FloatingActionButton) v.findViewById(R.id.btn_MyVocab);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_insert();
                print();
            }
        });

        lv_Myvocab.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personal c = new Personal();
                c = (Personal) lv_Myvocab.getItemAtPosition(position);
                if (c.getVoca_Audio() == null) {
                    Toast.makeText(getActivity(), "Please choose a sound", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        Uri uri = Uri.parse(c.getVoca_Audio());
                        mediaPlayer.setDataSource(getActivity(), uri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Please choose a sound", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        {
            lv_Myvocab.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    final  Personal    a = (Personal) lv_Myvocab.getItemAtPosition(position);
                    int c = a.getId_MyVocab();
                    AlertDialog.Builder bui = new AlertDialog.Builder(getActivity());
                    LayoutInflater inf = getActivity().getLayoutInflater();
                    v = inf.inflate(R.layout.dialog_myvocab, null);
                    bui.setView(v);
                    MaterialEditText e1 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Eng);
                    e1.setText(a.getNew_MyVocab() + "");
                    MaterialEditText e2 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Vi);
                    e2.setText(a.getMean_MyVocab() + "");
                    Button b1 = (Button) v.findViewById(R.id.btn_chooseS);
                    Button b2 = (Button) v.findViewById(R.id.btn_chooseP);
                    final ImageView img =(ImageView)v.findViewById(R.id.abc);

                    edtChooseAudio = (TextView) v.findViewById(R.id.edt_chooseS);
                    bui.setView(v);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectsound();
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadImagefromGallery();
                        }
                    });


                    bui.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            MaterialEditText e1 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Eng);
                            MaterialEditText e2 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Vi);
                            String nombre = e1.getText().toString();
                            if (e1.getText().toString().trim().equals("") || e1.getText().toString().subSequence(0, 1).equals(" ")) {
                                Toast.makeText(getActivity(), "Please enter a word", Toast.LENGTH_SHORT).show();
                            } else if(!nombre.matches("[a-zA-Z.? ]*")) {
                                Toast.makeText(getActivity(), "Please enter a word not The special character", Toast.LENGTH_SHORT).show();
                            }else if(img_Decodable_Str == null){
                                myDAO = new MyVocab_DAO(getActivity());
                                String text1 = e1.getText().toString();
                                String text2 = e2.getText().toString();
                                a.setNew_MyVocab(text1);
                                a.setMean_MyVocab(text2);
                                a.setVoca_Audio(path);
                                myDAO.update(a);
                                print();
                                dialog.cancel();
                            }
                            else {
                                myDAO = new MyVocab_DAO(getActivity());
                                String text1 = e1.getText().toString();
                                String text2 = e2.getText().toString();
                                a.setNew_MyVocab(text1);
                                a.setMean_MyVocab(text2);
                                a.setVoca_Image(img_Decodable_Str);
                                a.setVoca_Audio(path);
                                myDAO.update(a);
                                print();
                                dialog.cancel();
                            }
                        }

                    });
                    bui.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                            myDAO.delete(a.getId_MyVocab());

                            dialog.cancel();
                            print();
                        }
                    });
                    bui.show();
                    print();
                    return true;
                }


            });
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
        ArrayList<Personal> list = myDAO.getAllMyVoca();
        adapter = new MyVocab_Adapter(getActivity(), list);
        lv_Myvocab.setAdapter(adapter);
    }

    public void Dialog_insert() {
        final Personal a = new Personal();
        AlertDialog.Builder bui = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        v = inf.inflate(R.layout.dialog_myvocab, null);
        Button b1 = (Button) v.findViewById(R.id.btn_chooseS);
        Button b2 = (Button) v.findViewById(R.id.btn_chooseP);
        edtChooseAudio = (TextView)v.findViewById(R.id.edt_chooseS);
        path = a.getVoca_Audio();
        img_Decodable_Str = a.getVoca_Image();

        bui.setView(v);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectsound();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagefromGallery();
            }
        });
        bui.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                MaterialEditText e1 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Eng);
                MaterialEditText e2 = (MaterialEditText) v.findViewById(R.id.txt_MyVocab_Vi);
                String nombre = e1.getText().toString();
                if (e1.getText().toString().trim().equals("") || e1.getText().toString().subSequence(0, 1).equals(" ")) {
                    Toast.makeText(getActivity(), "Please enter a word", Toast.LENGTH_SHORT).show();
                    Dialog_insert();
                }

                else if(!nombre.matches("[a-zA-Z.? ]*")){
                    Toast.makeText(getActivity(), "Please enter a word not The special character", Toast.LENGTH_SHORT).show();
                    Dialog_insert();
                }
                /*else if(path == null){
                    Toast.makeText(getActivity(), "Please choose a sound ", Toast.LENGTH_SHORT).show();
                } *//*else if(path != null){
                    path = null;
                    Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
                }*//*
                else if(img_Decodable_Str == null){
                    Toast.makeText(getActivity(), "Please choose a image", Toast.LENGTH_SHORT).show();
                }*/else {
                    myDAO = new MyVocab_DAO(getActivity());

                    a.setNew_MyVocab(e1.getText().toString());
                    a.setMean_MyVocab(e2.getText().toString());
                    a.setVoca_Image(img_Decodable_Str);
                    a.setVoca_Audio(path);
                    myDAO.insert(a);
                    print();
                    dialog.cancel();
                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
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

    public void CustomDialog_sua(final Personal a) {
    }

    public void selectsound() {
    /*    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(intent, RESULTA_AUDIO);*/
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, RESULTA_AUDIO);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            startActivityForResult(intent, RESULTA_AUDIO);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == RESULTA_AUDIO && null != data){
                Uri audio  = data.getData();
           /*String[] filePathColumn = {MediaStore.Audio.Media.DATA};


           Cursor cursor =getActivity().getContentResolver().query(audio,
                   filePathColumn, null, null, null);
           // Move to first row
           cursor.moveToFirst();

           int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
           path = cursor.getString(columnIndex);
           cursor.close();*/
                path = String.valueOf(audio);

                edtChooseAudio = (TextView)v.findViewById(R.id.edt_chooseS);
                edtChooseAudio.setText("File selected");
            }} catch (Exception e) {
            Toast.makeText(getActivity(), "Something went embrassing", Toast.LENGTH_LONG)
                    .show();
        }

        try {

            if (requestCode == RESULT_LOAD
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor =getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();

                final ImageView imgView = (ImageView) v.findViewById(R.id.abc);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(img_Decodable_Str));
                imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder bui = new AlertDialog.Builder(getActivity());
                        LayoutInflater inf = getActivity().getLayoutInflater();
                        v = inf.inflate(android.R.layout.select_dialog_item, null);

                        bui.setTitle("Warning");
                        bui.setMessage("would you like to delete it?");
                        bui.setView(v);
                        bui.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        bui.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                img_Decodable_Str = null;
                                imgView.setImageBitmap(BitmapFactory
                                        .decodeFile(img_Decodable_Str));
                                dialog.cancel();
                            }
                        });

                        bui.show();

                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went embrassing", Toast.LENGTH_LONG)
                    .show();
        }

    }
}