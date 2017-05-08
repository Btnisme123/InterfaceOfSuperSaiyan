package vulan.com.trackingstore.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends BaseFragment {
    private LinearLayout mLayoutTitleIntro, mLayoutIntro;
    private Switch mSwitchNotify, mSwitchVibrate, mSwitchSound, mSwitchList;
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
        mSwitchVibrate = (Switch) rootView.findViewById(R.id.sw_vibrate);
        mSwitchSound = (Switch) rootView.findViewById(R.id.sw_sound);
        mSwitchList = (Switch) rootView.findViewById(R.id.sw_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.NOTIFY_SETTING, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.Settings.NOTIFY_SETTING, true)) {
            mSwitchNotify.setChecked(true);
        } else {
            mSwitchNotify.setChecked(false);
        }
        sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.VIBRATE_SETTING, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.Settings.VIBRATE_SETTING, true)) {
            mSwitchVibrate.setChecked(true);
        } else {
            mSwitchVibrate.setChecked(false);
        }
        sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.RING_SETTING, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.Settings.RING_SETTING, true)) {
            mSwitchSound.setChecked(true);
        } else {
            mSwitchSound.setChecked(false);
        }
        sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.LIST_SETTING, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.Settings.LIST_SETTING, false)) {
            mSwitchList.setChecked(true);
        } else {
            mSwitchList.setChecked(false);
        }
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
                sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.NOTIFY_SETTING, MODE_PRIVATE);
                if (isChecked) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.NOTIFY_SETTING, true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.NOTIFY_SETTING, false);
                    editor.commit();
                }

            }
        });
        mSwitchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.VIBRATE_SETTING, MODE_PRIVATE);
                if (isChecked) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.VIBRATE_SETTING, true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.VIBRATE_SETTING, false);
                    editor.commit();
                }
            }
        });
        mSwitchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.RING_SETTING, MODE_PRIVATE);
                if (isChecked) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.RING_SETTING, true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.RING_SETTING, false);
                    editor.commit();
                }
            }
        });
        mSwitchList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.LIST_SETTING, MODE_PRIVATE);
                if (isChecked) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.LIST_SETTING, true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Constants.Settings.LIST_SETTING, false);
                    editor.commit();
                }
            }
        });
    }
}
