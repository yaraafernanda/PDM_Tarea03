package com.iteso.pdm_scrollabletabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm_scrollabletabs.database.ItemProductControl;

import java.util.ArrayList;

/**
 * Created by hsm-y on 03/03/2018.
 */

public class FragmentHome extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ItemProduct> myDataSet;

    public FragmentHome() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        myDataSet = new ArrayList<>();

        ItemProductControl itemProductControl = new ItemProductControl();
        myDataSet = itemProductControl.getItemProductsByCategory(
                2, DataBaseHandler.getInstance(getContext()));

        mAdapter = new AdapterProduct(this.getContext(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}