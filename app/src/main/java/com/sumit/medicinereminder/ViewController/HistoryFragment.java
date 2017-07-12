package com.sumit.medicinereminder.ViewController;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.fabtransitionactivity.SheetLayout;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sumit.medicinereminder.Model.History;
import com.sumit.medicinereminder.Model.PillBox;
import com.sumit.medicinereminder.R;


public class HistoryFragment extends Fragment implements SheetLayout.OnFabAnimationEndListener,View.OnClickListener {
    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        TableLayout stk = (TableLayout) rootView.findViewById(R.id.table_history);

        TableRow tbrow0 = new TableRow(container.getContext());
        mSheetLayout = (SheetLayout) rootView.findViewById(R.id.bottom_sheet);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSheetLayout.expandFab();
            }
        });
        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);
        TextView tt1 = new TextView(container.getContext());
        tt1.setText("Pill Name");
        tt1.setTextColor(Color.DKGRAY);
        tt1.setGravity(Gravity.CENTER);
        tt1.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt1);

        TextView tt2 = new TextView(container.getContext());
        tt2.setText("Date Taken");
        tt2.setTextColor(Color.DKGRAY);
        tt2.setGravity(Gravity.CENTER);
        tt2.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt2);

        TextView tt3 = new TextView(container.getContext());
        tt3.setText("Time Taken");
        tt3.setTextColor(Color.DKGRAY);
        tt3.setGravity(Gravity.CENTER);
        tt3.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt3);

        TextView tt4 = new TextView(container.getContext());
        tt4.setText("Pill Taken");
        tt4.setTextColor(Color.DKGRAY);
        tt4.setGravity(Gravity.CENTER);
        tt4.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt4);

        stk.addView(tbrow0);

        PillBox pillBox = new PillBox();

        for (History history : pillBox.getHistory(container.getContext())) {
            TableRow tbrow = new TableRow(container.getContext());

            TextView t1v = new TextView(container.getContext());
//            String pill_name = history.getPillName().substring(0,history.getPillName().indexOf('/'));
            t1v.setText(history.getPillName());
            t1v.setTextColor(Color.DKGRAY);
            t1v.setGravity(Gravity.CENTER);
            t1v.setMaxEms(4);
            tbrow.addView(t1v);

            TextView t2v = new TextView(container.getContext());
            String date = history.getDateString();
            t2v.setText(date);
            t2v.setTextColor(Color.DKGRAY);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);

            TextView t3v = new TextView(container.getContext());

            int nonMilitaryHour = history.getHourTaken() % 12;
            if (nonMilitaryHour == 0)
                nonMilitaryHour = 12;

            String minute;
            if (history.getMinuteTaken() < 10)
                minute = "0" + history.getMinuteTaken();
            else
                minute = "" + history.getMinuteTaken();

            String time = nonMilitaryHour + ":" + minute + " " + history.getAm_pmTaken();
            t3v.setText(time);
            t3v.setTextColor(Color.DKGRAY);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);

            TextView t4v = new TextView(container.getContext());
            Logger.addLogAdapter(new AndroidLogAdapter());
            Logger.i("value History" + history.getPillValue());
//            String pillTakenValue = history.getPillName().substring(0,history.getPillName().indexOf('/'));
            t4v.setText(history.getPillTakenValue());
            t4v.setTextColor(Color.DKGRAY);
            t4v.setGravity(Gravity.CENTER);
            t4v.setMaxEms(4);
            tbrow.addView(t4v);

            stk.addView(tbrow);
        }
        return rootView;
    }

    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), PillBoxActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mSheetLayout.expandFab();
            Logger.addLogAdapter(new AndroidLogAdapter());
            Logger.i("Clicked");
        }
    }
}