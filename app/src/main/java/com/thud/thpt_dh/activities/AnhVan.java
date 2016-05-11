package com.thud.thpt_dh.activities;

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

import com.thud.thpt_dh.R;

import com.thud.thpt_dh.customcontrols.CustomGridView;

public class AnhVan extends AppCompatActivity
{
    DrawerLayout drawerLayout_anhvan;
    Toolbar toolbar_anhvan;
    ActionBar actionBar_anhvan;
    TextView textView_anhvan;

    GridView grid;
    String[] TenAnhVan = {"Chủ đề", "Ngữ pháp", "Anh Văn 12"}; //Tạo mảng tên môn
    int[] IconAnhVan = {R.drawable.anhvan10, R.drawable.anhvan11, R.drawable.anhvan12};//Icon tên môn

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anh_van);

        toolbar_anhvan = (Toolbar) findViewById(R.id.toolbar_anhvan); //lấy id toolbar môn anh văn
        setSupportActionBar(toolbar_anhvan);

        actionBar_anhvan = getSupportActionBar();
        actionBar_anhvan.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_anhvan.setDisplayHomeAsUpEnabled(true);

        drawerLayout_anhvan = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_anhvan);//menu ben trai
        NavigationView navigationView_anhvan = (NavigationView) findViewById(R.id.navigation_view_anhvan);

        if (navigationView_anhvan != null)
        {
            setupNavigationDrawerContent(navigationView_anhvan);
        }
        setupNavigationDrawerContent(navigationView_anhvan);

        //Main Menu
        CustomGridView adapter_anhvan = new CustomGridView(AnhVan.this, TenAnhVan, IconAnhVan);//Tao danh danh icon menu chinh
        grid = (GridView) findViewById(R.id.gdv_anhvan);
        grid.setAdapter(new CustomGridView(this, TenAnhVan, IconAnhVan));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_anh_van, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home: //nut hom
                drawerLayout_anhvan.openDrawer(GravityCompat.START);
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
                        textView_anhvan = (TextView) findViewById(R.id.textView_anhvan);
                        switch (menuItem.getItemId()) {//Khi nhan vao nut hom thi se tro ve man hinh chinh
                            case R.id.item_navigation_drawer_home_toan:
                                menuItem.setChecked(true);
                                Toast.makeText(AnhVan.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_anhvan.closeDrawer(GravityCompat.START);
                                Intent intent_anhvan = new Intent(AnhVan.this, MainActivity.class);
                                startActivity(intent_anhvan);
                                return true;

                            case R.id.item_navigation_drawer_anhvan10://Hien thi anh van lop 10 khi chon
                                menuItem.setChecked(true);
                                textView_anhvan.setText(menuItem.getTitle());
                                drawerLayout_anhvan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_anhvan11:
                                menuItem.setChecked(true);
                                textView_anhvan.setText(menuItem.getTitle());
                                drawerLayout_anhvan.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_anhvan12:
                                menuItem.setChecked(true);
                                textView_anhvan.setText(menuItem.getTitle());
                                drawerLayout_anhvan.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}