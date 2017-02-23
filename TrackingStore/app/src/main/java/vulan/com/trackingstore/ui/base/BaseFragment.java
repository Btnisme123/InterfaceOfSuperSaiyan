package vulan.com.trackingstore.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vulan.com.trackingstore.R;

/**
 * Created by VULAN on 10/20/2016.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayoutId(), container, false);
        onCreateContentView(rootView,savedInstanceState);
        return rootView;
    }

    protected abstract void onCreateContentView(View rootView,Bundle savedInstanceState);

    protected abstract int getFragmentLayoutId();

    protected void onBackPressed() {
        getBaseActivity().popFragment();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected String getTitle() {
        return getString(R.string.app_name);
    }

    protected boolean enableBackButton() {
        return true;
    }

    protected boolean enableToolbar() {
        return true;
    }


}
