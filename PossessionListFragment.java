package andriod.bignerdranch.homepwner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PossessionListFragment extends Fragment {
    private RecyclerView mPossessionRecyclerView;
    private PossessionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_possession_list, container, false);
        mPossessionRecyclerView = (RecyclerView) view.findViewById(R.id.possession_recycler_view);
        mPossessionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        ListDetail listDetail = ListDetail.get(getActivity());
        List<Possession> possessionList = listDetail.getPossessions();

        if (mAdapter == null) {
            mAdapter = new PossessionAdapter(possessionList) {
            };
            mPossessionRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class PossessionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Possession mPossession;
        private TextView mNameTextView;
        private TextView mDateTextView;
        private TextView mValueTextView;
        private String mValue;

        public void bind(Possession possession) {
            mPossession = possession;
            mNameTextView.setText(mPossession.getName());
            //mDateTextView.setText(mPossession.getDate().toString());

            String mValue = "$" + Double.toString(mPossession.getValue());
            mValueTextView.setText(mValue);
        }

        public PossessionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_possession, parent, false));

            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.possession_name);
            mDateTextView = (TextView) itemView.findViewById(R.id.possession_date);
            mValueTextView = (TextView) itemView.findViewById(R.id.possession_value);
        }

        @Override
        public void onClick(View view) {
            Intent intent = PossessionPagerActivity.newIntent(getActivity(), mPossession.getId());
            startActivity(intent);
        }
    }

    private class PossessionAdapter extends  RecyclerView.Adapter<PossessionHolder> {
        private List<Possession> mPossessionList;

        public PossessionAdapter(List<Possession> possessionList) {mPossessionList = possessionList; }

        @NonNull
        @Override
        public PossessionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PossessionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PossessionHolder holder, int position) {
            Possession possession = mPossessionList.get(position);
            holder.bind(possession);

        }


        public int getItemCount() {
            return mPossessionList.size();
        }

    }

}
