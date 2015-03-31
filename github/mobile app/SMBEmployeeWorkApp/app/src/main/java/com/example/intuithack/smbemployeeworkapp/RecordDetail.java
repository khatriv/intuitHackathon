package com.example.intuithack.smbemployeeworkapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshulika.ks on 31/03/15.
 */
public class RecordDetail extends ActionBarActivity {

    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rec_layout);

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

        mActionBar.setCustomView(mCustomView);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowCustomEnabled(true);

        listView = (ListView)findViewById(R.id.listView12);
        List<TaskRecord> taskRecordsDay = new ArrayList<TaskRecord>();
        taskRecordsDay.add(new TaskRecord("6 hours", "11 MAR", true));
        taskRecordsDay.add(new TaskRecord("4 hours", "13 MAR", false));
        taskRecordsDay.add(new TaskRecord("14 hours", "18 MAR", false));
        taskRecordsDay.add(new TaskRecord("3 hours", "21 Feb", true));

        List<TaskRecord> taskRecordsWeek = new ArrayList<TaskRecord>();
        taskRecordsWeek.add(new TaskRecord("35 hours", "11 MAR - 18 MAR", true));
        taskRecordsWeek.add(new TaskRecord("39 hours", "18 FEB - 25 FEB", true));
        taskRecordsWeek.add(new TaskRecord("40 hours", "13 JAN - 20 JAN", false));

        List<TaskRecord> taskRecordsMonth = new ArrayList<TaskRecord>();
        taskRecordsMonth.add(new TaskRecord("60 hours", "MAR 2015", true));
        taskRecordsMonth.add(new TaskRecord("150 hours", "JAN 2015", false));
        taskRecordsMonth.add(new TaskRecord("80 hours", "DEC 2014", true));

        RecordTaskAdaptor recordTaskAdaptor;
        if(title.equalsIgnoreCase("Day-wise")){
           recordTaskAdaptor = new RecordTaskAdaptor(taskRecordsDay, RecordDetail.this);
        }else if(title.equalsIgnoreCase("Week-wise")){
            recordTaskAdaptor= new RecordTaskAdaptor(taskRecordsWeek, RecordDetail.this);
        }else {
            recordTaskAdaptor= new RecordTaskAdaptor(taskRecordsMonth, RecordDetail.this);
        }
        listView.setAdapter(recordTaskAdaptor);
    }

    private class RecordTaskAdaptor extends BaseAdapter {
        private List<TaskRecord> mTasks;
        LayoutInflater inflater;
        Context context;

        public RecordTaskAdaptor(List<TaskRecord> mTasks, Context context) {
            this.mTasks = mTasks;
            this.context = context;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Object getItem(int position) {
            return mTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.record_detail_layout, null);
            }

            TextView textViewDuration = (TextView) convertView.findViewById(R.id.duration);
            textViewDuration.setText(mTasks.get(position).duration);
            TextView timelineText = (TextView) convertView.findViewById(R.id.textTimeLine);
            timelineText.setText(mTasks.get(position).getTimeline());
            ImageView imageView = (ImageView) convertView.findViewById(R.id.star);
            if (mTasks.get(position).isStar())
                imageView.setVisibility(View.VISIBLE);
            return convertView;
        }
    }
}
