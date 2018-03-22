package com.iteso.pdm_scrollabletabs;

import android.content.Intent;
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
import java.util.Iterator;

/**
 * Created by hsm-y on 03/03/2018.
 */

public class FragmentTechnology extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ItemProduct> myDataSet;

    public FragmentTechnology() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_technology, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        myDataSet = new ArrayList<ItemProduct>();
        /*myDataSet.add(new ItemProduct(getResources().getString(R.string.product1),
                getResources().getString(R.string.store1),
                getResources().getString(R.string.location1),
                getResources().getString(R.string.phone1),
                getResources().getString(R.string.desc1),0, 0));
        myDataSet.add(new ItemProduct(getResources().getString(R.string.product2),
                getResources().getString(R.string.store2),
                getResources().getString(R.string.location2),
                getResources().getString(R.string.phone2),
                getResources().getString(R.string.desc2),1, 1));
        myDataSet.add(new ItemProduct(getResources().getString(R.string.product3),
                getResources().getString(R.string.store3),
                getResources().getString(R.string.location3),
                getResources().getString(R.string.phone3),
                getResources().getString(R.string.desc3),2, 2));
        */

        ItemProductControl itemProductControl = new ItemProductControl();
        myDataSet = itemProductControl.getItemProductsByCategory(
                1, DataBaseHandler.getInstance(getContext()));

        mAdapter = new AdapterProduct(getContext(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        itemProductControl = null;
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<ItemProduct> iterator = myDataSet.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                myDataSet.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }

}
