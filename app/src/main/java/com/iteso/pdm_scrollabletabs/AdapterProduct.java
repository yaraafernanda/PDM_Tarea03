package com.iteso.pdm_scrollabletabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.pdm_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

/**
 * Created by hsm-y on 26/02/18.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private ArrayList<ItemProduct> products;
    private Context context;

    public AdapterProduct(Context context, ArrayList<ItemProduct> products) {
        this.products = products;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button mDetail;
        public Button mShare;
        public TextView mProductTitle;
        public TextView mProductStore;
        public TextView mProductLocation;
        public TextView mProductPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;

        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_product_layout);
            mDetail = (Button) v.findViewById(R.id.item_product_detail);
            mShare = (Button) v.findViewById(R.id.item_product_share);
            mProductTitle = (TextView) v.findViewById(R.id.item_product_title);
            mProductStore = (TextView) v.findViewById(R.id.item_product_store);
            mProductLocation = (TextView) v.findViewById(R.id.item_product_location);
            mProductPhone = (TextView) v.findViewById(R.id.item_product_phone);
            mProductImage = (ImageView) v.findViewById(R.id.item_product_image);
            mProductThumbnail = (ImageView) v.findViewById(R.id.item_product_thumbnail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mProductTitle.setText(products.get(position).getTitle());
        holder.mProductStore.setText(products.get(position).getStore().getName());
        holder.mProductLocation.setText(products.get(position).getStore().getCity().getName());
        holder.mProductPhone.setText(products.get(position).getStore().getPhone());

        switch (products.get(position).getImage()) {
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware);
                break;
            case 2:
                holder.mProductImage.setImageResource(R.drawable.lanix);
                break;
            case 3:
                holder.mProductImage.setImageResource(R.drawable.sala);
                break;
            case 4:
                holder.mProductImage.setImageResource(R.drawable.iphone);
                break;
        }

        holder.mProductPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + products.get(position).getStore().getPhone()));
                context.startActivity(intent);
            }
        });

        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProduct item = new ItemProduct();
                item.setTitle(products.get(position).getTitle());
                item.setStore(products.get(position).getStore());
                item.setCategory(products.get(position).getCategory());
                item.setCode(products.get(position).getCode());
                item.setImage(products.get(position).getImage());

                Intent intent = new Intent(context, ActivityProduct.class);
                intent.putExtra("ITEM", item);
                ((ActivityMain) context).startActivityForResult(intent, products.get(position).getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}