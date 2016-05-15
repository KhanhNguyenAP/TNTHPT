package com.thud.thpt_dh.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.adapters.SettingDrawerAdapter;
import com.thud.thpt_dh.model.DrawerSetting;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.util.ArrayList;

public class NavigationDrawerFragment extends Fragment {
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private NavigationDrawerCallbacks mCallbacks;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;
    private View view;
    private SettingDrawerAdapter settingDrawerAdapter;

    private int mCurrentSelectedPosition = 0;

    private Resources res;
    private ActionBarDrawerToggle mDrawerToggle;

    public static ArrayList<android.app.Fragment> fragments = new ArrayList<>();
    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    selectItem(position);

                    if(view.getTag() != null){
                        setViewFromDrawer(position);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        res = getResources();

        //get data for list navigation drawer
        CharSequence[] array_setting_title = res.getStringArray(R.array.array_setting);

        Drawable[] colors = new Drawable[]{
                res.getDrawable(R.color.md_blue_light_50),
                res.getDrawable(R.color.md_blue_light_100),
                res.getDrawable(R.color.md_blue_light_50),
                res.getDrawable(R.color.md_blue_light_100),
                res.getDrawable(R.color.md_blue_light_50),
                res.getDrawable(R.color.md_blue_light_100),
                res.getDrawable(R.color.md_blue_light_50),
                res.getDrawable(R.color.md_blue_light_100),
                res.getDrawable(R.color.md_blue_light_50),
                res.getDrawable(R.color.md_blue_light_100)
        };
        Drawable[] icons = new Drawable[]{
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information),
                res.getDrawable(R.drawable.settings_personal_information)
        };
        ArrayList<DrawerSetting> values = new ArrayList<>();
        for(int i =0; i< array_setting_title.length; ++i){
            values.add(new DrawerSetting(array_setting_title[i].toString(),icons[i], colors[i]));
        }

        settingDrawerAdapter = new SettingDrawerAdapter(getActivity(),values);
        mDrawerListView.setAdapter(settingDrawerAdapter);
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        return mDrawerListView;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.menu,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item != null && item.getItemId() == android.R.id.home) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
                return false;
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }


    private void setViewFromDrawer(int position){
        try{

            //version build & code
            if(position == 8 || position == 9){
                return;
            }

            if(position == 5){
                return;
            }


            FragmentManager fragmentManager = getActivity().getFragmentManager();
            for(android.app.Fragment fragment : fragments){
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
            }

            switch (position){
                //fragment personal info
                case 0: {
                    showTrangChu(fragmentManager);
                    break;

                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
//            Log.e(Def.ERROR, e.getMessage());
        }
    }

    /**
     * shor Trang Chu
     * @param fragmentManager
     */
    private void showTrangChu(FragmentManager fragmentManager){
        android.app.Fragment fragmentTrangChu =
                fragmentManager.findFragmentByTag(Def.TAG_SETTING_TRANGCHU);

        if(fragmentTrangChu != null){
            fragmentManager.beginTransaction().remove(fragmentTrangChu).commitAllowingStateLoss();
        }

        fragmentTrangChu = new FragmentTrangChu();
        fragments.add(fragmentTrangChu);
        fragmentManager.beginTransaction()
                .add(R.id.fra_main, fragmentTrangChu, Def.TAG_SETTING_TRANGCHU)
                .commit();
    }//--end showPersonalInfo

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
