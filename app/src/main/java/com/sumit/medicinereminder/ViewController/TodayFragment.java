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
import com.sumit.medicinereminder.Model.Alarm;
import com.sumit.medicinereminder.Model.PillBox;
import com.sumit.medicinereminder.R;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TodayFragment extends Fragment implements SheetLayout.OnFabAnimationEndListener,View.OnClickListener {

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_today, container, false);

        TableLayout stk = (TableLayout) rootView.findViewById(R.id.table_today);

        Typeface lightFont = Typeface.createFromAsset(container.getContext().getAssets(), "fonts/Roboto-Light.ttf");
        mSheetLayout = (SheetLayout) rootView.findViewById(R.id.bottom_sheet);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSheetLayout.expandFab();
            }
        });
        PillBox pillBox = new PillBox();
        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        List<Alarm> alarms = Collections.emptyList();

        try {
            alarms = pillBox.getAlarms(container.getContext(), day);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (alarms.size() != 0) {
            for (Alarm alarm : alarms) {
                TableRow tbrow = new TableRow(container.getContext());

                TextView t1v = new TextView(container.getContext());
                t1v.setText(alarm.getPillName());
                t1v.setTextColor(Color.DKGRAY);
                t1v.setGravity(Gravity.CENTER);
                t1v.setPadding(30, 30, 30, 30);
                t1v.setTextSize(25);
                t1v.setTypeface(lightFont);
                t1v.setMaxEms(6);

                tbrow.addView(t1v);

                TextView t2v = new TextView(container.getContext());

                String time = alarm.getStringTime();
                t2v.setText(time);
                t2v.setTextColor(Color.DKGRAY);
                t2v.setGravity(Gravity.CENTER);
                t2v.setPadding(30, 30, 30, 30);
                t2v.setTextSize(25);
                t2v.setTypeface(lightFont);
                tbrow.addView(t2v);

                stk.addView(tbrow);
            }
        } else {
            TableRow tbrow = new TableRow(container.getContext());

            TextView t1v = new TextView(container.getContext());
            t1v.setText("You don't have any alarms for Today!");
            t1v.setTextColor(Color.DKGRAY);
            t1v.setGravity(Gravity.CENTER);
            t1v.setPadding(30, 30, 30, 30);
            t1v.setTextSize(25);
            t1v.setTypeface(lightFont);
            t1v.setMaxEms(10);
            tbrow.addView(t1v);

            stk.addView(tbrow);
        }
        return rootView;
    }

//    @OnClick(R.id.fab)
//    void onFabClick() {
//        mSheetLayout.expandFab();
//    }

    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), AddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.fab)
        {
            mSheetLayout.expandFab();
            Logger.addLogAdapter(new AndroidLogAdapter());
            Logger.i("Clicked");
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            mSheetLayout.contractFab();
//        }
//    }
}
