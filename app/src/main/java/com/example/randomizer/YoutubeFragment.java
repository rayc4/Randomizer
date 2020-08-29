package com.example.randomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YoutubeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YoutubeFragment extends Fragment implements View.OnClickListener, YouTubePlayer.OnInitializedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YoutubeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YoutubeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YoutubeFragment newInstance(String param1, String param2) {
        YoutubeFragment fragment = new YoutubeFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        Button button = view.findViewById(R.id.youtube_button);
        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        System.out.println("Clicked");
        YouTubePlayerSupportFragmentX playerFragment = YouTubePlayerSupportFragmentX.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.frame_fragment, playerFragment).commit();
        playerFragment.initialize("AIzaSyBnnANRSFiHaDaYX6DOE4jZID_j_YD80sQ", this);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        System.out.println("success");
        youTubePlayer.loadVideo("dQw4w9WgXcQ");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("Failed");
    }
}