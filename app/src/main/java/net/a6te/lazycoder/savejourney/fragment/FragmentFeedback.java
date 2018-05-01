package net.a6te.lazycoder.savejourney.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import net.a6te.lazycoder.savejourney.FeedbackActivity;
import net.a6te.lazycoder.savejourney.R;
import net.a6te.lazycoder.savejourney.adapter.AdapterBuss;
import net.a6te.lazycoder.savejourney.databinding.FragmentFragmentFeedbackBinding;
import net.a6te.lazycoder.savejourney.modelClass.Buss;

import java.util.ArrayList;

public class FragmentFeedback extends Fragment {


    private FragmentFragmentFeedbackBinding binding;
    private AdapterBuss adapter;
    private Buss buss;
    private ArrayList<Buss> bussList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fragment_feedback, container, false);
        // Inflate the layout for this fragment
        buss = new Buss();
        bussList = buss.getAllBussList();
        adapter = new AdapterBuss(getActivity(), bussList);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bussListLv.setAdapter(adapter);
        binding.bussListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class).putExtra("travelName",bussList.get(position).getBussName()));
            }
        });
    }
}
