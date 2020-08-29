package com.example.randomizer;

import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoinFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoinFragment newInstance(String param1, String param2) {
        CoinFragment fragment = new CoinFragment();
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

    private ImageView coin;
    private Drawable coin_drawable;
    private TextView result;
    private Random random;
    private ImageView resultImg;
    private Drawable heads_drawable;
    private Drawable tails_drawable;
    private Animatable2 anim;
    private Animatable2.AnimationCallback callback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coin, container, false);
        coin = (ImageView) view.findViewById(R.id.coin_view);
        result = (TextView) view.findViewById(R.id.coin_result);
        resultImg = (ImageView) view.findViewById(R.id.coin_result_img);

        Resources res = getResources();
        try {
            heads_drawable = Drawable.createFromXml(res, res.getXml(R.xml.heads));
            tails_drawable = Drawable.createFromXml(res, res.getXml(R.xml.tails));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        Button button = view.findViewById(R.id.coin_button);
        button.setOnClickListener(this);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.coin_button) {
            result.setText("");
            resultImg.setImageDrawable(null);

            coin_drawable = coin.getDrawable();
            callback = new Animatable2.AnimationCallback() {
                @Override
                public void onAnimationEnd(Drawable drawable) {
                    super.onAnimationEnd(drawable);

                    random = new Random();

                    if(random.nextBoolean()){
                        result.setText("Heads");
                        resultImg.setImageDrawable(heads_drawable);
                    }else{
                        result.setText("Tails");
                        resultImg.setImageDrawable(tails_drawable);
                    }
                }
            };

            if (coin_drawable instanceof Animatable2){
                anim = (Animatable2) coin_drawable;
                anim.registerAnimationCallback(callback);
                anim.start();
            }

        }
    }
}