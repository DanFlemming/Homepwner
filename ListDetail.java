package andriod.bignerdranch.homepwner;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListDetail {
    private static ListDetail sListDetail;

    private String[] nouns = {
            "Fluffy",
            "Shiny",
            "Rusty",
            "Dull",
            "Sharp"};
    private String[] adjs = {
            "Bear",
            "Spork",
            "Mac",
            "Guitar",
            "Window"};

    private List<Possession> mPossessionList;


    public static ListDetail get(Context context) {
        if (sListDetail == null) {
            sListDetail = new ListDetail(context);
        }
        return sListDetail;
    }

    private ListDetail(Context context) {
        mPossessionList = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//
//            Random r = new Random();
//            int i1 = r.nextInt(5);
//            int i2 = r.nextInt(5);
//            int i3 = r.nextInt(100);
//
//            Possession possession = new Possession();
//            possession.setName(nouns[i2] + " " + adjs[i1]);
//            possession.setValue(i3);
//
//
//            String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//            StringBuilder CHAR = new StringBuilder();
//            Random rnd = new Random();
//
//            while (CHAR.length() < 8) {
//                int index = (int) (rnd.nextFloat() * CHARS.length());
//                CHAR.append(CHARS.charAt(index));
//            }
//
//            String CHARID = CHAR.toString();
//            possession.setSerial(CHARID);
//
//            mPossessionList.add(possession);
//        }
    }

    public void addPossessions(Possession p) {
        mPossessionList.add(p);
    }

    public List<Possession> getPossessions() {return mPossessionList;}

    public Possession getPossession(UUID id) {
        for (Possession possession : mPossessionList) {
            if (possession.getId().equals(id)) {
                return possession;
            }
        }
        return null;
    }


}
