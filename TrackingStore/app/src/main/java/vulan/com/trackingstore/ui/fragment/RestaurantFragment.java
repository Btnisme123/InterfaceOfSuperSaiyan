package vulan.com.trackingstore.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;

/**
 * Created by Thanh on 10/21/2016.
 */

public class RestaurantFragment extends BaseFragment {
    ImageView bannerFood,bannerDrink,bannerOther;
    public final static String TAG_RESTAURANT_FRAGMENT = "restaurant fragment";

    @Override
    protected void onCreateContentView(View rootView) {
        findViews(rootView);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_restaurant;
    }

    private void findViews(View view) {
        bannerFood = (ImageView) view.findViewById(R.id.banner_food);
        bannerDrink = (ImageView) view.findViewById(R.id.banner_drink);
        bannerOther = (ImageView) view.findViewById(R.id.banner_other);

        bannerFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open details of food
            }
        });
        bannerDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open details of drink
            }
        });
        bannerOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open details of other
            }
        });
    }
}
