package com.thud.thpt_dh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.customcontrols.CustomGridView;
import com.thud.thpt_dh.utils.async.SaveAllDataFromSerVer;
import com.thud.thpt_dh.utils.interfaces.Flags;

/**
 * Created by KhanhNguyen on 5/20/2016.
 */
public class DeThiActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    GridView grid;
    String[] TenMon = {"Toán", "Ngữ Văn", "Anh Văn", "Sinh Học","Vật Lý", "Hóa", "Lịch Sử", "Địa Lý"};
    int[] Icon = {R.drawable.toan, R.drawable.nguvan, R.drawable.anhvan,
            R.drawable.sinh, R.drawable.vatly, R.drawable.hoa,
            R.drawable.lichsu, R.drawable.dialy};
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dethi);

        //Cai dat toolbar thay the cho Actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_dethi);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //tìm drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_dethi);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view_dethi);
        if (navigationView != null)  {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);

        //Main Menu
        onContentGridView();
    }

    public void onContentGridView(){
        CustomGridView adapter = new CustomGridView(DeThiActivity.this, TenMon, Icon);
        grid = (GridView) findViewById(R.id.gdv_menu_dethi);
        grid.setAdapter(new CustomGridView(this, TenMon, Icon));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                if (pos == 8)
                    finish();
                else  {
                    Intent intent = null;
                    switch (pos) {
                        case 0:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 1;
                            break;
                        case 1:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 2;
                            break;
                        case 2:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 3;
                            break;
                        case 3:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 6;
                            break;
                        case 4:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 4;
                            break;
                        case 5:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 5;
                            break;
                        case 6:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 7;
                            break;
                        case 7:
                            intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                            Flags.chosen_dethi = 8;
                            break;
                    }
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId())    {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)  {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)  {
                        Intent intent = new Intent(DeThiActivity.this, ToanHocActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        switch (menuItem.getItemId())  {
                            case R.id.item_navigation_drawer_dethi:
                                menuItem.setChecked(true);
                                return true;

                            case R.id.item_navigation_drawer_montoan:
                                if (Flags.chosen_mon == 1){
                                    menuItem.setCheckable(true);
                                }
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 1;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monnguvan:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 2;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monanhvan:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 2;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monvatly:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 4;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monhoahoc:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 5;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monsinhoc:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 6;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monlichsu:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 7;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_mondialy:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 8;
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });
    }
}
