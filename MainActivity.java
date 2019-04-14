package andriod.bignerdranch.homepwner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    private static final String EXTRA_POSSESSION_ID = "com.bignerdranch.android.Homepwner.possession_id ";

    public static Intent newIntent(Context packageContext, UUID possessionId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_POSSESSION_ID, possessionId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID possessionId = (UUID) getIntent().getSerializableExtra(EXTRA_POSSESSION_ID);
        return PossessionFragment.newInstance(possessionId);
    }
}
