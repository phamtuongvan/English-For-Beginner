package pro112;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pro112.englishforbeginner.R;
import pro112.model.Personal;

/**
 * Created by nhan on 29/11/15.
 */
public class MyVocab_Adapter extends ArrayAdapter<Personal> {
    Activity activity;
    List<Personal> psList;
    private static Context mContext;

    public MyVocab_Adapter(Activity activity,List<Personal> objects) {
        super(activity, R.layout.custom_myvocab, objects);
        this.activity = activity;
        this.psList = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Personal ps = new Personal();
        ps = getItem(position);
        View v = convertView;


        LayoutInflater inflater = activity.getLayoutInflater();
        v = inflater.inflate(R.layout.custom_myvocab,null,true);
        TextView New_word = (TextView) v.findViewById(R.id.tv_Myvocab);
        TextView New_word_vi = (TextView) v.findViewById(R.id.tv_Myvocab_Vi);
        ImageView img    = (ImageView)v.findViewById(R.id.img_MyVocab);
       // ImageButton btn_speak = (ImageButton)v.findViewById(R.id.btn_MySound);
        img.setImageBitmap(BitmapFactory
                .decodeFile(ps.getVoca_Image()));
        New_word.setText(ps.New_MyVocab);
        New_word_vi.setText(ps.Mean_MyVocab);

        return v;
    }
}