package andriod.bignerdranch.homepwner;

import java.util.Date;
import java.util.UUID;

public class Possession {


    private UUID mId;
    private String mName;
    private String mSerial;
    private int mValue;
    private Date mDate;

    public Possession() {

        mDate = new Date();
        mId = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSerial() {
        return mSerial;
    }

    public void setSerial(String serial) {
        mSerial = serial;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId(){return mId;}

}
