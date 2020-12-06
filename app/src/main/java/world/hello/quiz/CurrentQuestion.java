package world.hello.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentQuestion extends Fragment {

    private static final String ARG_PARAM1 = "label";
    private static final String ARG_PARAM2 = "answer1";
    private static final String ARG_PARAM3 = "answer2";
    private static final String ARG_PARAM4 = "answer3";
    private static final String ARG_PARAM5 = "answer4";

    private String label;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public CurrentQuestion() {
        super(R.layout.fragment_current_question);

    }

    public static androidx.fragment.app.Fragment newInstance(String label, String answer1, String answer2, String answer3, String answer4) {
        CurrentQuestion fragment = new CurrentQuestion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, label);
        args.putString(ARG_PARAM2, answer1);
        args.putString(ARG_PARAM3, answer2);
        args.putString(ARG_PARAM4, answer3);
        args.putString(ARG_PARAM5, answer4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_PARAM1);
            answer1 = getArguments().getString(ARG_PARAM2);
            answer2 = getArguments().getString(ARG_PARAM3);
            answer3 = getArguments().getString(ARG_PARAM4);
            answer4 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_question, container, false);

        TextView question = (TextView)view.findViewById(R.id.question);
        TextView answer1 = (TextView)view.findViewById(R.id.answer1);
        TextView answer2 = (TextView)view.findViewById(R.id.answer2);
        TextView answer3 = (TextView)view.findViewById(R.id.answer3);
        TextView answer4 = (TextView)view.findViewById(R.id.answer4);

        question.setText(this.label);
        answer1.setText(this.answer1);
        answer2.setText(this.answer2);
        answer3.setText(this.answer3);
        answer4.setText(this.answer4);
        return view;
    }
}