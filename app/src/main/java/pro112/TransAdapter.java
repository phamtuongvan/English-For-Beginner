package pro112;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pro112.englishforbeginner.R;
import pro112.model.Transcript;

/**
 * Created by Tuong Vinh on 11/13/2015.
 */
public class TransAdapter extends ArrayAdapter<Transcript> {
    Context context = null;
    List<Transcript> listT = null;
    int textViewResourceId;
    Transcript trans;
    public TransAdapter(Context context, int resource, List<Transcript> objects) {
        super(context, resource, objects);
        this.context = context;
        this.textViewResourceId = resource;
        this.listT = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        trans = getItem(position);

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(textViewResourceId, null);
        }
        TextView txtTrans = (TextView)v.findViewById(R.id.txtTrans);
        txtTrans.setText(trans.getTrans_mark());
        return v;
    }
}
