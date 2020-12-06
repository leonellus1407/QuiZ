package world.hello.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestingResultFragment extends Fragment {

    private static final String ARG_PARAM1 = "score";

    private String mParam1;

    public TestingResultFragment() {
    }

    public static TestingResultFragment newInstance(String score) {
        TestingResultFragment fragment = new TestingResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1 = getArguments().getString(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_testing_result, container, false);
        TextView scoreView = rootView.findViewById(R.id.score);
        scoreView.setText(this.mParam1);
        return rootView;
    }
}