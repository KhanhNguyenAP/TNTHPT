package fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thud.thpt_dh.R;

public class FragmentLeftMain extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_leftmain, null);
        return root;
    }
}