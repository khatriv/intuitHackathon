package com.example.intuithack.smbemployeeworkapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by anshulika.ks on 30/03/15.
 */
public class WorkBoardFragment extends Fragment {

    private View root = null;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        System.out.println(WorkBoardFragment.class.getSimpleName() + "");
        root = inflater.inflate(R.layout.work_board_layout, container, false);
        mListView = (ListView)root.findViewById(R.id.listView);
        final List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Pending", 0L,true, TaskPriority.NONE,null));
        tasks.add(new Task("Woman Trouser", 3L,false,TaskPriority.ARRIVED, new Date(2015, 2, 31)));
        tasks.add(new Task("Khaki chinos", 3L,false,TaskPriority.URGENT,new Date(2015, 2, 10)));
        tasks.add(new Task("Black Shirts", 3L,false, TaskPriority.PENDING,new Date(2015, 1, 10)));
        tasks.add(new Task("Polka dots shirt", 3L,false,TaskPriority.PENDING,new Date(2015, 2, 21)));
        tasks.add(new Task("Cardigan with scarf", 3L,false,TaskPriority.PENDING,new Date(2015, 2, 22)));
        tasks.add(new Task("Ladies blouse", 3L,false,TaskPriority.PENDING,new Date(2015, 2, 24)));
        tasks.add(new Task("5 long skirts", 3L,false,TaskPriority.PENDING,new Date(2015, 2, 26)));
        tasks.add(new Task("Completed", 0L,true, TaskPriority.NONE,null));
        tasks.add(new Task("Salwaar Suit", 1L,false,TaskPriority.FINISHED,new Date(2015, 2, 28)));
        tasks.add(new Task("Waist coat", 2L,false,TaskPriority.FINISHED,new Date(2015, 3, 13)));

        TaskListAdapter taskListAdapter = new TaskListAdapter(getActivity(), tasks);
        mListView.setAdapter(taskListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(WorkBoardFragment.class.getSimpleName() + " onItemClick " + tasks.get(position).getTitle());
                if(!tasks.get(position).isHeader()) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("title", tasks.get(position).getTitle());
                    getActivity().startActivity(intent);
                }
            }
        });

        return root;
    }

    private class TaskListAdapter extends BaseAdapter{

        private List<Task> mTasks;
        LayoutInflater inflater;
        Context context;

        public TaskListAdapter(Context context, List<Task> taskList){
            mTasks = taskList;

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
                if (getItemViewType(position) == 0) {
                    convertView = inflater.inflate(R.layout.work_item_title, null);
                } else {
                    convertView = inflater.inflate(R.layout.work_item,null);
                }
            }

            if(getItemViewType(position) == 0) {
                TextView textView = (TextView)convertView.findViewById(R.id.headingtext);
                textView.setText(mTasks.get(position).getTitle().toUpperCase(Locale.ENGLISH));
            }else {
                TextView textView = (TextView)convertView.findViewById(R.id.titleText);
                textView.setText(mTasks.get(position).getTitle());
                TextView timeText = (TextView)convertView.findViewById(R.id.timeText);
                timeText.setText(mTasks.get(position).getTitle());
                TextView alotedWhen = (TextView)convertView.findViewById(R.id.alotedTime);
                SimpleDateFormat sdf = new SimpleDateFormat("dd, MMM");
                String date = sdf.format(mTasks.get(position).getDate());
                alotedWhen.setText(date);

                switch (mTasks.get(position).getTaskPriority().ordinal()){
                    case 2:
                        convertView.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case 4:
                        convertView.setBackgroundColor(getResources().getColor(R.color.elv_btn_pressed));
                        break;
                    case 0:
                        convertView.setBackgroundColor(getResources().getColor(R.color.red));
                        break;
                    case 1:
                        convertView.setBackgroundColor(getResources().getColor(R.color.tab_indicator_color));
                        break;
                    case 5:
                    default:
                        convertView.setBackgroundColor(getResources().getColor(R.color.pullout_divider_level_0));
                }
            }
            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if(mTasks.get(position).isHeader())
                return 0;
            else
                return 1;
        }
    }
}
