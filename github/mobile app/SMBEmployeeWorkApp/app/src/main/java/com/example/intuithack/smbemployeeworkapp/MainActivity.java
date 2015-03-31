package com.example.intuithack.smbemployeeworkapp;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private MyPagerAdapter myPagerAdapter;
    private Context mContext;
    ImageView workImage;
    ImageView recordImage;
//    TextView timerTextView;
    TextView workTextView;
    TextView recordTextView;
    ViewPager vpPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.action_bar_main, null);

        Chronometer chronometer = (Chronometer)mCustomView.findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
//        chronometer.setFormat("HH:MM:SS");
        chronometer.start();

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer cArg) {
                long t = SystemClock.elapsedRealtime() - cArg.getBase()-19800000l;
                cArg.setText(DateFormat.format("kk:mm:ss", t));
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowCustomEnabled(true);

        vpPager = (ViewPager) findViewById(R.id.vpPager);

        workImage = (ImageView)findViewById(R.id.tab_indicator_work);
        recordImage = (ImageView)findViewById(R.id.tab_indicator_record);

        workTextView = (TextView) findViewById(R.id.workText);
        recordTextView = (TextView) findViewById(R.id.recordTimeText);

        workTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(0);
                updateColor(0);
            }
        });
        recordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(1);
                updateColor(1);
            }
        });

        mContext = MainActivity.this;
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(myPagerAdapter);
        vpPager.setCurrentItem(0);

        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                System.out.println("!!!!!!onPageSelected [position: " + position + "]");
                updateColor(position);
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

    }

    private void updateColor(int position) {
        if(position==0){
            workImage.setImageResource(R.color.elv_btn_pressed);
            recordImage.setImageResource(android.R.color.transparent);
        }else {
            workImage.setImageResource(android.R.color.transparent);
            recordImage.setImageResource(R.color.elv_btn_pressed);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Fragment fragment = new WorkBoardFragment();

                    Bundle args = new Bundle();
                    args.putInt("page_position", position + 1);

                    fragment.setArguments(args);

                    return fragment;
                case 1:
                    Fragment fragment1 = new OverviewFragment();

                    Bundle args1 = new Bundle();
                    args1.putInt("page_position", position + 1);

                    fragment1.setArguments(args1);

                    return fragment1;

                default:
                    Fragment fragment2 = new OverviewFragment();

                    Bundle args2 = new Bundle();
                    args2.putInt("page_position", position + 1);

                    fragment2.setArguments(args2);

                    return fragment2;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
            return "WORK";
            else return "RECORD";
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            System.out.println("destroyItem() [position: " + position + "]");
        }

    }
}
