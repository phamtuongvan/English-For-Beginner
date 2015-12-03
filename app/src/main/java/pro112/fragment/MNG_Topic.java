package pro112.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import pro112.SQLite.Topic_DAO;
import pro112.englishforbeginner.R;
import pro112.model.Topic;
import pro112.theme.Utils;

/**
 * Created by Sang on 26/11/2015.
 */
public class MNG_Topic extends Fragment implements View.OnClickListener {
    View view;
    List<Topic> topic = new ArrayList<>();
    Topic_DAO topDAO;
    Topic top;
    SwipeMenuListView lv;
    ListAdapter customAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_mng_topic, container, false);
        View v2 = inflater.inflate(R.layout.app_bar_main, container, false);
        Utils.onActivityCreateSetTheme(getActivity());
        FloatingActionButton bt = (FloatingActionButton)v.findViewById(R.id.btn_add);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog();
            }
        });
        topDAO = new Topic_DAO(getActivity());
        topic = topDAO.getTopic();
        lv=(SwipeMenuListView)v.findViewById(R.id.listView2);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
                openItem.setWidth(200);
                menu.addMenuItem(openItem);
                openItem.setIcon(R.mipmap.ic_backup_restore_white_18dp);
                openItem.setBackground(R.color.blue_dark);
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
                menu.addMenuItem(deleteItem);
                deleteItem.setWidth(200);
                deleteItem.setIcon(R.mipmap.delete);
                deleteItem.setBackground(R.color.blue_dark);
            }
        };
        lv.setMenuCreator(creator);
        // Right
        lv.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
        lv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                int vitri = position;
                Topic a = topic.get(vitri);
                switch (index) {
                    case 0:
                        // open
                        CustomDialog_sua(a);
                        break;
                    case 1:
                        topDAO.delete(a.getTopic_ID());
                        Toast.makeText(getActivity(), "Delete success"+a.getTopic_Name(), Toast.LENGTH_SHORT).show();
                        updateTopic();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        customAdapter = new ListAdapter(getActivity(), R.layout.one_item2,topic);
        lv .setAdapter(customAdapter);
        registerForContextMenu(lv);

        return v;
    }

   /* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        getActivity().getMenuInflater().inflate(R.menu.menu_xoa_sua, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int vitri = info.position;
        Topic a = topic.get(vitri);
        top = a;
        switch (item.getItemId()) {
            case R.id.xoa:
                topDAO.delete(a.getTopic_ID());
                Toast.makeText(getActivity(), "Xóa thành công "+a.getTopic_Name(), Toast.LENGTH_SHORT).show();
                updateTopic();
                break;
            case R.id.sua:
                CustomDialog_sua();
                break;
        }
        return false;
    }*/
    public void updateTopic(){
        topDAO = new Topic_DAO(getActivity());
        topic = topDAO.getTopic();
        customAdapter = new ListAdapter(getActivity(), R.layout.one_item2,topic);
        lv .setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        lv.setAdapter(customAdapter);
    }

    public void CustomDialog() {
        AlertDialog.Builder bui=new AlertDialog.Builder(getActivity());

        LayoutInflater inf = getActivity().getLayoutInflater();
        view = inf.inflate(R.layout.dialog_topic, null);
        bui.setView(view);
        //topDAO = TopicDAO(this);
        MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.topic_name);


        bui.setNegativeButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.topic_name);
                //TopicDAO  topDAO = new TopicDAO(this);
                String nombre = editText.getText().toString();
                if(!nombre.matches("[a-zA-Z.? ]*")){
                    CustomDialog();
                    Toast.makeText(getActivity(), "Bạn chưa điền tên topic vào ô trống", Toast.LENGTH_SHORT).show();
                }else if(editText.getText().toString().trim().equals("") || editText.getText().toString().subSequence(0,1).equals(" ")){
                    CustomDialog();
                    Toast.makeText(getActivity(), "Bạn chưa điền tên topic vào ô trống", Toast.LENGTH_SHORT).show();
                }else {
                    topDAO = new Topic_DAO(getActivity());
                    Topic a = new Topic();
                    a.setTopic_Name(editText.getText().toString());
                    topDAO.insert(a);
                    Toast.makeText(getActivity(), "Thêm thành công " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    updateTopic();
                    dialog.cancel();
                }

            }
        });
        bui.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

        bui.show();
    }

    public void CustomDialog_sua(final Topic a) {
        AlertDialog.Builder bui=new AlertDialog.Builder(getActivity());

        LayoutInflater inf = getActivity().getLayoutInflater();
        view = inf.inflate(R.layout.dialog_sua, null);
        bui.setView(view);
        MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.topic_name);
        editText.setText(a.getTopic_Name()+"");

        bui.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.topic_name);
                String nombre = editText.getText().toString();
                if(!nombre.matches("[a-zA-Z.? ]*")){
                    CustomDialog_sua(a);
                    Toast.makeText(getActivity(), "Bạn chưa điền tên topic vào ô trống", Toast.LENGTH_SHORT).show();
                }else if(editText.getText().toString().trim().equals("") || editText.getText().toString().subSequence(0,1).equals(" ")){
                    CustomDialog_sua(a);
                    Toast.makeText(getActivity(), "Bạn chưa điền tên topic vào ô trống", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();

                    topDAO = new Topic_DAO(getActivity());
                    String topic_Name = editText.getText().toString();
                    a.setTopic_Name(topic_Name);
                    topDAO.update(a);
                    updateTopic();
                    dialog.cancel();
                }
            }
        });
        bui.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });

        bui.show();
    }


    @Override
    public void onClick(View v) {

    }

    public class ListAdapter extends ArrayAdapter<Topic> {
        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }
        public ListAdapter(Context context, int resource, List<Topic> items) {
            super(context, resource, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.one_item2, null);
            }
            Topic p = getItem(position);
            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.txtNameTopic);
                tt1.setText(p.getTopic_Name());
            }
            return v;
        }

    }
}
