package com.sumit.medicinereminder.Model;

import com.orhanobut.logger.Logger;

/**
 * Created by Sumit on 13-Jul-17.
 */
public class History {
    private int hourTaken;
    private int minuteTaken;
    private String dateString;
    private String pillName;

    public int getPillValue() {
        return pillValue;
    }

    public void setPillValue(int pillValue) {
        this.pillValue = pillValue;
    }

    public int pillValue = 0;

    public String getPillTakenValue() {
        Logger.i("History Modal" + pillTakenValue);
        return pillTakenValue;
    }

    public void setPillTakenValue(String pillTakenValue) {
        Logger.i("History Modal" + pillTakenValue);
        this.pillTakenValue = pillTakenValue;
    }

    private String pillTakenValue="";
    public boolean isPillTaken() {
        return pillTaken;
    }

    public void setPillTaken(boolean pillTaken) {
        this.pillTaken = pillTaken;
    }

    private boolean pillTaken;

    public int getHourTaken() { return hourTaken; }

    public void setHourTaken(int hourTaken) { this.hourTaken = hourTaken; }

    public int getMinuteTaken() { return minuteTaken; }

    public void setMinuteTaken(int minuteTaken) { this.minuteTaken = minuteTaken; }

    public String getAm_pmTaken() { return (hourTaken < 12) ? "am" : "pm"; }

    public String getDateString() { return dateString; }

    public void setDateString(String dateString) { this.dateString = dateString; }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }
}
