package com.vytautaskrasauskas.memorisemk6;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);

        setViewPager();

        //Expandable button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public boolean isFABOpen;
            FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
            FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);

//            boolean isFABOpen;

            public void showFABMenu() {
                this.isFABOpen = true;
                fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
                fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
            }

            public void closeFABMenu() {
                this.isFABOpen = false;
                fab1.animate().translationY(0);
                fab2.animate().translationY(0);
            }

            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        //Add category button
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO close the expandable FAB
                goToAddCategoryActivity();
            }
        });

        //Add case button
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //TODO close the expandable FAB
                goToAddCaseActivity();
            }
        });
    }

    private void goToAddCategoryActivity() {

        Intent intent = new Intent(this, AddCategoryActivity.class);

        startActivity(intent);

    }

    private void goToAddCaseActivity() {

        Intent intent = new Intent(this, AddCaseActivity.class);

        startActivity(intent);
    }


    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}