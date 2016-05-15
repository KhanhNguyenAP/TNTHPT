package com.thud.thpt_dh.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;

public class FragmentTrangChu extends Fragment implements ActivityInterface {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initControl();

        setEventForControl();

        getData();
    }

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {
    }

    @Override
    public void setData() {
    }
}
