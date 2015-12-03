package pro112;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pro112.englishforbeginner.R;
import pro112.model.Vocabulary;

/**
 * Created by Tuong Vinh on 11/13/2015.
 */
public class ItemAdapter extends ArrayAdapter<Vocabulary> {
    Context context = null;
    List<Vocabulary> listT = null;
    int textViewResourceId;
    Vocabulary voca;
    View v;
    public ItemAdapter(Context context, int resource, List<Vocabulary> objects) {
        super(context, resource, objects);
        this.context = context;
        this.textViewResourceId = resource;
        this.listT = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        voca = getItem(position);

        v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(textViewResourceId, null);
        }
        try {
            ImageView imgRem = (ImageView)v.findViewById(R.id.imgRem);
            final int id = v.getResources().getIdentifier(voca.getVoca_Image().toLowerCase(), "drawable", getContext().getPackageName());
            imgRem.setImageResource(id);
            ImageButton btnSound_Rem = (ImageButton)v.findViewById(R.id.btnSound_Rem);
            final int id1 = v.getResources().getIdentifier(voca.getVoca_Audio().toLowerCase(), "raw", getContext().getPackageName());
            btnSound_Rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mp = MediaPlayer.create(v.getContext(), id1);
                    mp.start();
                }
            });
        }catch (Exception e){
            ImageView imgRem = (ImageView)v.findViewById(R.id.imgRem);
            imgRem.setImageBitmap(BitmapFactory
                    .decodeFile(voca.getVoca_Image()));
            ImageButton btnSound_Rem = (ImageButton)v.findViewById(R.id.btnSound_Rem);
            try {
                final Uri uri = Uri.parse(voca.getVoca_Audio());
                btnSound_Rem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                            try {
                                MediaPlayer mp =MediaPlayer.create(v.getContext(), uri);
                                mp.start();
                            }catch (Exception e2){
                                Toast.makeText(getContext(), "No Sound file", Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }catch (Exception e1){
                btnSound_Rem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "No Sound file", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
            TextView txtEng_Rem = (TextView)v.findViewById(R.id.txtEng_Rem);
            TextView txtVie_Rem = (TextView)v.findViewById(R.id.txtVie_Rem);
            txtEng_Rem.setText(voca.getVoca_Eng());
            //txtEng_Rem.setTextColor(v.getResources().getColorStateList(R.color.red));
            txtVie_Rem.setText(voca.getVoca_Vie());
            //txtVie_Rem.setTextColor(v.getResources().getColorStateList(R.color.red));
        return v;
    }
}
