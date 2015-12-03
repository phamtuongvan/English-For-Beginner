package pro112.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pro112.SQLite.Topic_DAO;
import pro112.TopicAdapter;
import pro112.englishforbeginner.R;
import pro112.model.Topic;
import pro112.theme.Utils;


/**
 * Created by nhan on 31/10/15.
 */
public class LetsGo extends Fragment {
    ListView lv;
    Topic_DAO topDAO;
    List<Topic> topic = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_letsgo, container, false);
        Utils.onActivityCreateSetTheme(getActivity());
        topDAO = new Topic_DAO(getActivity());
        topic = topDAO.getTopic();
        lv = (ListView) v.findViewById(R.id.lvTopic);
        TopicAdapter topicArray = new TopicAdapter(getActivity(), R.layout.one_item, topic);
        lv.setAdapter(topicArray);
        return v;

    }
}
