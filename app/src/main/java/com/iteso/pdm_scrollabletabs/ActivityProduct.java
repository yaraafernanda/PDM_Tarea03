package com.iteso.pdm_scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.pdm_scrollabletabs.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    EditText title, store, location, phone;
    ImageView image;
    Button save, cancel;
    ItemProduct itemProduct, item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        title = (EditText) findViewById(R.id.activity_product_name);
        store = (EditText) findViewById(R.id.activity_product_store);
        location = (EditText) findViewById(R.id.activity_product_location);
        phone = (EditText) findViewById(R.id.activity_product_phone);
        image = (ImageView) findViewById(R.id.activity_product_image);
        save = findViewById(R.id.activity_product_save);
        cancel = findViewById(R.id.activity_product_cancel);

        if (getIntent().getExtras() != null){
            itemProduct = getIntent().getParcelableExtra("ITEM");
            if(itemProduct != null){
                title.setText(itemProduct.getTitle());
                store.setText(itemProduct.getStore());
                location.setText(itemProduct.getLocation());
                phone.setText(itemProduct.getPhone());
                switch(itemProduct.getImage()){
                    case 0:
                        image.setImageResource(R.drawable.mac); break;
                    case 1:
                        image.setImageResource(R.drawable.alienware); break;
                    case 2:
                        image.setImageResource(R.drawable.mac); break;
                }
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                item = new ItemProduct();
                item.setTitle(title.getText().toString());
                item.setStore(store.getText().toString());
                item.setLocation(location.getText().toString());
                item.setPhone(phone.getText().toString());
                item.setImage(itemProduct.getImage());
                item.setCode(itemProduct.getCode());

                Intent result = new Intent();
                result.putExtra("ITEM", item);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
