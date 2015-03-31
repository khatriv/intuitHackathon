package com.example.intuithack.smbemployeeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by anshulika.ks on 30/03/15.
 */
public class OverviewFragment extends Fragment {

    private View root = null;
    private TextView textView, textView2, textView3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        System.out.println(OverviewFragment.class.getSimpleName() + "");
        root = inflater.inflate(R.layout.overview_layout, container, false);
        textView = (TextView)root.findViewById(R.id.textViewDayWise);
        textView2 = (TextView)root.findViewById(R.id.textViewWeekWise);
        textView3 = (TextView)root.findViewById(R.id.textViewMonthWise);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordDetail.class);
                intent.putExtra("title", "Day-wise");
                getActivity().startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordDetail.class);
                intent.putExtra("title", "Week-wise");
                getActivity().startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecordDetail.class);
                intent.putExtra("title", "Month-wise");
                getActivity().startActivity(intent);
            }
        });

        return root;
    }
}
