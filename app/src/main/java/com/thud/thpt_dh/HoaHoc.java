package com.thud.thpt_dh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import xuly.CustomGridView;

public class HoaHoc extends AppCompatActivity {
    DrawerLayout drawerLayout_hoahoc;
    Toolbar toolbar_hoahoc;
    ActionBar actionBar_hoahoc;
    TextView textView_hoahoc;

    GridView grid;
    String[] TenHoaHoc = {"Hóa Học 10", "Hóa Học 11", "Hóa Học 12"};
    int[] IconHoaHoc = {R.drawable.hoa, R.drawable.hoa, R.drawable.hoa};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_hoc);

        toolbar_hoahoc = (Toolbar) findViewById(R.id.toolbar_hoahoc);
        setSupportActionBar(toolbar_hoahoc);

        actionBar_hoahoc = getSupportActionBar();
        actionBar_hoahoc.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_hoahoc.setDisplayHomeAsUpEnabled(true);

        drawerLayout_hoahoc = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_hoahoc);

        NavigationView navigationView_hoahoc = (NavigationView) findViewById(R.id.navigation_view_hoahoc);
        if (navigationView_hoahoc != null)
        {
            setupNavigationDrawerContent(navigationView_hoahoc);
        }

        setupNavigationDrawerContent(navigationView_hoahoc);

        //Main Menu
        CustomGridView adapter_hoahoc = new CustomGridView(HoaHoc.this, TenHoaHoc, IconHoaHoc);
        grid = (GridView) findViewById(R.id.gdv_hoahoc);
        grid.setAdapter(new CustomGridView(this, TenHoaHoc, IconHoaHoc));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_hoa_hoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout_hoahoc.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        textView_hoahoc = (TextView) findViewById(R.id.textView_hoahoc);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home_hoahoc:
                                menuItem.setChecked(true);
                                Toast.makeText(HoaHoc.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_hoahoc.closeDrawer(GravityCompat.START);
                                Intent intent_hoahoc = new Intent(HoaHoc.this, MainActivity.class);
                                startActivity(intent_hoahoc);
                                return true;

                            case R.id.item_navigation_drawer_hoahoc10:
                                menuItem.setChecked(true);
                                textView_hoahoc.setText(menuItem.getTitle());
                                drawerLayout_hoahoc.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_hoahoc11:
                                menuItem.setChecked(true);
                                textView_hoahoc.setText(menuItem.getTitle());
                                drawerLayout_hoahoc.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_hoahoc12:
                                menuItem.setChecked(true);
                                textView_hoahoc.setText(menuItem.getTitle());
                                drawerLayout_hoahoc.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}