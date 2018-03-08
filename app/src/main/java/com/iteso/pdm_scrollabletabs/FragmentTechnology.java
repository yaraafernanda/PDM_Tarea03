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
        myDataSet.add(new ItemProduct("Mac", "BestBuy", "Zapopan","3338466316","Llevate esta Mac con un 30% de descuento para que puedas programar para XCode y Android sin tener que batallar tanto como en tu Windows", 0, 0));
        myDataSet.add(new ItemProduct("Alienware", "BestBuy", "Guadalajara","3311960785","Llevate esta Alienware para que puedas programar viedojuegos increíbles", 1, 1));
        myDataSet.add(new ItemProduct("Lanix", "BestBuy", "Tlaquepaque","3352486325","Llevate esta Lanix perfecta para tus tareas escolares", 2, 2));

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
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
