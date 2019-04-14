package andriod.bignerdranch.homepwner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class PossessionFragment extends Fragment {

    private static final String ARG_ITEM_ID = "possession_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Possession mPossession;
    private EditText mName;
    private EditText mSerial;
    private EditText mValue;
    private Button mDate;

    public static PossessionFragment newInstance(UUID possessionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, possessionId);

        PossessionFragment fragment = new PossessionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mPossession.setDate(date);

            updateDate();
        }
    }

    private void updateDate() {
        mDate.setText(mPossession.getDate().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID possessionId = (UUID) getArguments().getSerializable(ARG_ITEM_ID);
        mPossession = ListDetail.get(getActivity()).getPossession(possessionId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_possession, container, false);

        mName = (EditText) v.findViewById(R.id.possession_name);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mName.setText(mPossession.getName());
                mPossession.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSerial = (EditText) v.findViewById(R.id.possession_serial);
        mSerial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //mPossession.setSerial(mPossession.getSerial());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mSerial.setText(mPossession.getSerial());
                mPossession.setSerial(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mValue = (EditText) v.findViewById(R.id.possession_value);
        mValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mPossession.setValue(( int ) Double.parseDouble(s.toString()));
                int number = Integer.parseInt(s.toString());
                mPossession.setValue(number);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //mName = (EditText) v.findViewById(R.id.possession_name);
        mName.setText(mPossession.getName());

        //mSerial = (EditText) v.findViewById(R.id.possession_serial);
        mSerial.setText(mPossession.getSerial());

        //mValue = (EditText) v.findViewById(R.id.possession_value);
        String A = Integer.toString(mPossession.getValue());
        mValue.setText(A);

        mDate = (Button) v.findViewById(R.id.possession_date);
        updateDate();
        //mDate.setEnabled(false);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mPossession.getDate());
                dialog.setTargetFragment(PossessionFragment.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        return v;

    }
}
