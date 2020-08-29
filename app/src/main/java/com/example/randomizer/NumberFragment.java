package com.example.randomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.lang.Math;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NumberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumberFragment newInstance(String param1, String param2) {
        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText min;
    private EditText max;
    private TextView result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_number, container, false);

        Button button = view.findViewById(R.id.num_button);
        min = view.findViewById(R.id.min);
        max = view.findViewById(R.id.max);
        result = view.findViewById(R.id.num_result);

        button.setOnClickListener(this);
        max.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        //if(view.getId() == R.id.num_button){
            if(!min.getText().toString().equals("") && !max.getText().toString().equals("")){
                int intMin = Integer.parseInt(min.getText().toString());
                int intMax = Integer.parseInt(max.getText().toString());
                int range = (intMax - intMin) + 1;
                if (range > 0){
                    int intResult = (int) (Math.random() * range) + intMin;
                    result.setText(Integer.toString(intResult));
                }else{
                    result.setText("Error: Min cannot be larger than Max");
                }
            }else{
                result.setText("Please enter Min and Max values");
            }
        //}
    }
}