package vulan.com.trackingstore.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import vulan.com.trackingstore.R;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView mRecyclerFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_details);
        findView();
    }

    private void findView() {
        mRecyclerFood = (RecyclerView) findViewById(R.id.recycler_food);
    }

    private void init() {

    }
}
