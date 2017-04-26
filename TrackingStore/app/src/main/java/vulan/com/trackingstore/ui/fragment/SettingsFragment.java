package vulan.com.trackingstore.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

public class SettingsFragment extends BaseFragment {
    private LinearLayout mLayoutTitleIntro, mLayoutIntro;
    private Switch mSwitchNotify, mSwitchVibrate, mSwitchRing;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_settings;
    }

    private void findViews(View rootView) {
        mLayoutTitleIntro = (LinearLayout) rootView.findViewById(R.id.layout_title_intro);
        mLayoutIntro = (LinearLayout) rootView.findViewById(R.id.layout_intro);
        mSwitchNotify = (Switch) rootView.findViewById(R.id.sw_notify);
    }

    private void init() {
        mLayoutTitleIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLayoutIntro.getVisibility() == View.VISIBLE) {
                    mLayoutIntro.setVisibility(View.GONE);
                } else {
                    mLayoutIntro.setVisibility(View.VISIBLE);
                }
            }
        });

        mSwitchNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.NOTIFY_SETTING, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.NOTIFY_SETTING, true);
                    editor.commit();
                }

            }
        });
    }
}
