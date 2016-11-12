package vulan.com.trackingstore.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.customview.BallBounce;


public class BlankFragment extends BaseFragment {

    @Override
    protected void onCreateContentView(View rootView) {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_blank;
    }
}
