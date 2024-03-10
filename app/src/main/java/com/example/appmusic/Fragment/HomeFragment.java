package com.example.appmusic.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmusic.Adapters.SliderAdapter;
import com.example.appmusic.Models.SliderModel;
import com.example.appmusic.R;
import com.example.appmusic.Utils.SliderTimer;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;


public class HomeFragment extends Fragment {

    private ViewPager slider;
    private ArrayList<SliderModel> sliderModelList;
    private SliderAdapter sliderAdapter;
    private TabLayout sliderIndicator;
    private Timer timer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        slider = view.findViewById(R.id.slider);
        sliderIndicator = view.findViewById(R.id.slider_indicator);
        sliderModelList = new ArrayList<>();
        timer = new Timer();
        sliderModelList.add(new SliderModel(R.drawable.poster,"PlayList #1"));
        sliderModelList.add(new SliderModel(R.drawable.poster1,"PlayList #2"));
        sliderModelList.add(new SliderModel(R.drawable.poster3,"PlayList #3"));
        sliderModelList.add(new SliderModel(R.drawable.poster4,"PlayList #4"));
        sliderAdapter = new SliderAdapter(getContext(), sliderModelList);
        slider.setAdapter(sliderAdapter);
        sliderIndicator.setupWithViewPager(slider);

        timer.scheduleAtFixedRate(new SliderTimer(getActivity(),slider, sliderModelList.size()),3000,6000);
        return view;
    }
}