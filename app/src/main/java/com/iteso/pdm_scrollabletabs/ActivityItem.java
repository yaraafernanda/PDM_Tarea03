package com.iteso.pdm_scrollabletabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm_scrollabletabs.beans.Category;
import com.iteso.pdm_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm_scrollabletabs.beans.Store;
import com.iteso.pdm_scrollabletabs.database.CategoryControl;
import com.iteso.pdm_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm_scrollabletabs.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {

    Spinner image, category, store;
    EditText title;
    Button save;
    DataBaseHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        image = findViewById(R.id.activity_item_image);
        category = findViewById(R.id.activity_item_category);
        store = findViewById(R.id.activity_item_store);
        title = findViewById(R.id.activity_item_title);
        save = findViewById(R.id.activity_item_save);

        dh = DataBaseHandler.getInstance(ActivityItem.this);
        CategoryControl categoryControl = new CategoryControl();
        ArrayList<Category> categoryArrayList = categoryControl.getCategories(dh);
        StoreControl storeControl = new StoreControl();
        ArrayList<Store> storeArrayList = storeControl.getStores(dh);

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(
                this, R.layout.support_simple_spinner_dropdown_item, categoryArrayList);
        ArrayAdapter<Store> storeArrayAdapter = new ArrayAdapter<Store>(
                this, R.layout.support_simple_spinner_dropdown_item, storeArrayList);

        categoryArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        storeArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        category.setAdapter(categoryArrayAdapter);
        store.setAdapter(storeArrayAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProduct itemProduct = new ItemProduct();
                ItemProductControl itemProductControl = new ItemProductControl();

                itemProduct.setCategory((Category) category.getSelectedItem());
                itemProduct.setStore((Store) store.getSelectedItem());
                //itemProduct.setCode();
                itemProduct.setImage(getResources().getIdentifier(image.getSelectedItem().toString(), "drawable", getPackageName()));
                itemProduct.setTitle(title.getText().toString());
                itemProduct.setDescription("Esto es una descripción");
                itemProductControl.addItemProduct(itemProduct, dh);

                finish();
            }
        });
    }
}
