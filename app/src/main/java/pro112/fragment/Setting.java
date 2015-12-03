package pro112.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro112.englishforbeginner.R;
import pro112.theme.SharedPreferencesManager;
import pro112.theme.Utils;

/**
 * Created by nhan on 31/10/15.
 */
public class Setting extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_setting, container, false);
        Utils.onActivityCreateSetTheme(getActivity());
        v.findViewById(R.id.theme_red).setOnClickListener(this);
        v.findViewById(R.id.theme_green).setOnClickListener(this);
        v.findViewById(R.id.theme_purple).setOnClickListener(this);
        v.findViewById(R.id.theme_blue).setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.theme_red:
                Utils.changeToTheme(getActivity(), Utils.THEME_RED);
                new SharedPreferencesManager(getActivity()).storeInt("theme", Utils.THEME_RED);
                break;
            case R.id.theme_green:
                Utils.changeToTheme(getActivity(), Utils.THEME_Green);
                new SharedPreferencesManager(getActivity()).storeInt("theme", Utils.THEME_Green);
                break;
            case R.id.theme_blue:
                Utils.changeToTheme(getActivity(), Utils.THEME_BLUE);
                new SharedPreferencesManager(getActivity()).storeInt("theme", Utils.THEME_BLUE);
                break;
            case R.id.theme_purple:
                Utils.changeToTheme(getActivity(), Utils.THEME_PURPLE);
                new SharedPreferencesManager(getActivity()).storeInt("theme", Utils.THEME_PURPLE);
                break;
        }
    }


}
