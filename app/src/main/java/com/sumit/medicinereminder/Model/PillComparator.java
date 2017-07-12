package com.sumit.medicinereminder.Model;

import java.util.Comparator;

/**
 * Created by Sumit on 13-Jul-17.
 */
public class PillComparator implements Comparator<Pill> {

    @Override
    public int compare(Pill pill1, Pill pill2){

        String firstName = pill1.getPillName();
        String secondName = pill2.getPillName();
        return firstName.compareTo(secondName);
    }
}
