package pro112;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pro112.englishforbeginner.MainActivity;
import pro112.englishforbeginner.R;
import pro112.fragment.FragVocabulary;
import pro112.model.Topic;

/**
 * Created by Tuong Vinh on 11/13/2015.
 */
public class TopicAdapter extends ArrayAdapter<Topic> {
    Context context = null;
    List<Topic> listT = null;
    int textViewResourceId;
    Topic topic;
    FloatingActionButton btn_topic;
    TextView txtTopic;

    public TopicAdapter(Context context, int resource, List<Topic> objects) {
        super(context, resource, objects);
        this.context = context;
        this.textViewResourceId = resource;
        this.listT = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        topic = getItem(position);

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(textViewResourceId, null);
        }
        btn_topic = (FloatingActionButton) v.findViewById(R.id.btn_topic);
        int id = getContext().getResources().getIdentifier(topic.getTopic_Name().toLowerCase(), "mipmap", getContext().getPackageName());
        btn_topic.setImageResource(id);
        txtTopic = (TextView) v.findViewById(R.id.txtTopic);
        txtTopic.setText(topic.getTopic_Name());
        final Context context = parent.getContext();
        btn_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = null;
                Bundle args = new Bundle();
                switch (position) {
                    case 0:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 1:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 2:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 3:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 4:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 5:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                    case 6:
                        fr = new FragVocabulary();
                        args.putInt("index", position + 1);
                        break;
                }
                fr.setArguments(args);
                FragmentManager fm = ((MainActivity) context).getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.abc, 0, 0, R.anim.change).replace(R.id.content, fr).commit();
                fm.executePendingTransactions();
            }
        });
        return v;
    }
}
