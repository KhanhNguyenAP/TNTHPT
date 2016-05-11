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

public class DiaLy extends AppCompatActivity
{
    DrawerLayout drawerLayout_dialy;
    Toolbar toolbar_dialy;
    ActionBar actionBar_dialy;
    TextView textView_dialy;

    GridView grid;
    String[] TenDiaLy = {"Địa Lý 10", "Địa Lý 11", "Địa Lý 12"};
    int[] IconDiaLy = {R.drawable.dialy, R.drawable.dialy, R.drawable.dialy};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_ly);

        toolbar_dialy = (Toolbar) findViewById(R.id.toolbar_dialy);
        setSupportActionBar(toolbar_dialy);

        actionBar_dialy = getSupportActionBar();
        actionBar_dialy.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_dialy.setDisplayHomeAsUpEnabled(true);

        drawerLayout_dialy = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_dialy);

        NavigationView navigationView_dialy = (NavigationView) findViewById(R.id.navigation_view_dialy);
        if (navigationView_dialy!= null)
        {
            setupNavigationDrawerContent(navigationView_dialy);
        }

        setupNavigationDrawerContent(navigationView_dialy);

        //Main Menu
        CustomGridView adapter_dialy = new CustomGridView(DiaLy.this, TenDiaLy, IconDiaLy);
        grid = (GridView) findViewById(R.id.gdv_dialy);
        grid.setAdapter(new CustomGridView(this, TenDiaLy, IconDiaLy));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_dia_ly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout_dialy.openDrawer(GravityCompat.START);
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
                        textView_dialy = (TextView) findViewById(R.id.textView_dialy);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home_dialy:
                                menuItem.setChecked(true);
                                Toast.makeText(DiaLy.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_dialy.closeDrawer(GravityCompat.START);
                                Intent intent_dialy = new Intent(DiaLy.this, MainActivity.class);
                                startActivity(intent_dialy);
                                return true;

                            case R.id.item_navigation_drawer_dialy10:
                                menuItem.setChecked(true);
                                textView_dialy.setText(menuItem.getTitle());
                                drawerLayout_dialy.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_dialy11:
                                menuItem.setChecked(true);
                                textView_dialy.setText(menuItem.getTitle());
                                drawerLayout_dialy.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_dialy12:
                                menuItem.setChecked(true);
                                textView_dialy.setText(menuItem.getTitle());
                                drawerLayout_dialy.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}