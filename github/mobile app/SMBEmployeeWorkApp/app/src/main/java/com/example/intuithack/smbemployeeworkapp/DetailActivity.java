package com.example.intuithack.smbemployeeworkapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

/**
 * Created by anshulika.ks on 31/03/15.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_layout);

        String title = getIntent().getStringExtra("title");

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.action_bar_layout, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.detailTitle);
        mTitleTextView.setText(title);
        ImageView backImage = (ImageView) mCustomView.findViewById(R.id.back_key);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView imageViewTime = (ImageView)findViewById(R.id.timeText);
        ImageView imageViewAlloted = (ImageView)findViewById(R.id.alotedTime);
        int img1 = R.drawable.shirt_front;
        int img2 = R.drawable.shirt_back;

        Button b1 = (Button)findViewById(R.id.updateButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"Not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        Button b2 = (Button)findViewById(R.id.completeButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"Done", Toast.LENGTH_SHORT).show();
            }
        });

        Random rand = new Random();
        int diceRoll = rand.nextInt(5);
        switch (diceRoll){
            case 0:
                img1 = R.drawable.shirt_front;
                img2 = R.drawable.shirt_back;
                break;
            case 1:
                img1 = R.drawable.chino_front;
                img2 = R.drawable.chino_side;
                break;
            case 2:
                img1 = R.drawable.kurta_front;
                img2 = R.drawable.kurta_back;
                break;
            case 3:
                img1 = R.drawable.sweater_front;
                img2 = R.drawable.sweater_back;
                break;
            case 4:
            default:
                    img1 = R.drawable.trouser_side;
                    img2 = R.drawable.trouser_back;
                break;

        }
        imageViewTime.setImageResource(img1);
        imageViewAlloted.setImageResource(img2);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowCustomEnabled(true);

        Task task = new Task("Bake 5 Truffles", 3L,false,TaskPriority.ARRIVED, new Date(2015, 2, 31));

    }

}
