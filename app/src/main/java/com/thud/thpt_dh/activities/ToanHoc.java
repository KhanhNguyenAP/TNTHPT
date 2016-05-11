package com.thud.thpt_dh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.thpt_dh.R;

import com.thud.thpt_dh.customcontrols.CustomGridView;

public class ToanHoc extends ActionBarActivity {
    DrawerLayout drawerLayout_toan;
    Toolbar toolbar_toan;
    ActionBar actionBar_toan;
    TextView textView_toan;

    GridView grid;
    String[] TenToan = {"Giải Tích 10","Hình Học 10", "Giải Tích 11","Hình Học 11", "Giải Tích 12","Hình Học 12"};
    int[] IconToan = {R.drawable.toan10, R.drawable.toan10,R.drawable.toan11,
            R.drawable.toan11,R.drawable.toan12,R.drawable.toan12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toan_hoc);

        toolbar_toan = (Toolbar) findViewById(R.id.toolbar_toan);
        setSupportActionBar(toolbar_toan);
        actionBar_toan = getSupportActionBar();
        actionBar_toan.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_toan.setDisplayHomeAsUpEnabled(true);
        drawerLayout_toan = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_toan);

        NavigationView navigationView_toan= (NavigationView) findViewById(R.id.navigation_view_toan);
        if (navigationView_toan != null)  {
            setupNavigationDrawerContent(navigationView_toan);
        }

        setupNavigationDrawerContent(navigationView_toan);

        //Main Menu
        CustomGridView adapter_toan = new CustomGridView(ToanHoc.this, TenToan, IconToan);
        grid = (GridView) findViewById(R.id.gdv_toan);
        grid.setAdapter(new CustomGridView(this, TenToan, IconToan));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 5)
                    finish();
                else {
                    Intent intent = null;
                    switch (pos) {
                        case 0:
                            intent = new Intent();
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                    }
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_toan_hoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())  {
            case android.R.id.home:
                drawerLayout_toan.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)  {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        textView_toan = (TextView) findViewById(R.id.textView_nguvan);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home_nguvan:
                                menuItem.setChecked(true);
                                Toast.makeText(ToanHoc.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                Intent intent_toan = new Intent(ToanHoc.this, MainActivity.class);
                                startActivity(intent_toan);
                                return true;

                            case R.id.item_navigation_drawer_hinhhoc10:
                                menuItem.setChecked(true);
                                textView_toan.setText(menuItem.getTitle());
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_giaitich11:
                                menuItem.setChecked(true);
                                textView_toan.setText(menuItem.getTitle());
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_hinhhoc11:
                                menuItem.setChecked(true);
                                textView_toan.setText(menuItem.getTitle());
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_giaitich12:
                                menuItem.setChecked(true);
                                textView_toan.setText(menuItem.getTitle());
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_hinhhoc12:
                                menuItem.setChecked(true);
                                textView_toan.setText(menuItem.getTitle());
                                drawerLayout_toan.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}