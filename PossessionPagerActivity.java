package andriod.bignerdranch.homepwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class PossessionPagerActivity extends AppCompatActivity {
    private static final String EXTRA_POSSESSION_ID =
            "com.bignerdranch.homepwner.possession_id";

    private ViewPager mViewPager;
    private List<Possession> mPossessions;

    public static Intent newIntent(Context packageContext, UUID possessionId){
        Intent intent = new Intent(packageContext, PossessionPagerActivity.class);
        intent.putExtra(EXTRA_POSSESSION_ID, possessionId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possession_pager);

        UUID possessionId = (UUID) getIntent().getSerializableExtra(EXTRA_POSSESSION_ID);

        mViewPager = (ViewPager) findViewById(R.id.possession_view_pager);

        mPossessions = ListDetail.get(this).getPossessions();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Possession possession = mPossessions.get(i);
                return PossessionFragment.newInstance(possession.getId());
            }

            @Override
            public int getCount() {
                return mPossessions.size();
            }
        });

        for (int i = 0; i < mPossessions.size(); i++){
            if (mPossessions.get(i).getId().equals(possessionId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}