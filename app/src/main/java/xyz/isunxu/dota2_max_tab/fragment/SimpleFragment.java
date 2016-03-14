package xyz.isunxu.dota2_max_tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import xyz.isunxu.dota2_max_tab.R;

/**
 * Created by sunxu on 16/3/14.
 */
public class SimpleFragment extends Fragment {

    public static String POSITION = "postion";

    public static int[] imageArrays = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,};

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int postion = 0;
        if (bundle != null) {
            postion =  bundle.getInt(POSITION);
        }

        LinearLayout linearLayout  = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imageView = (ImageView) linearLayout.getChildAt(0);
        imageView.setImageResource(imageArrays[postion]);
        return linearLayout;
    }


    public static Fragment getInstance(int postion) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, postion);

        SimpleFragment simpleFragment = new SimpleFragment();
        simpleFragment.setArguments(bundle);

        return simpleFragment;
    }


}
