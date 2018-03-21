package com.iteso.pdm_scrollabletabs.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm_scrollabletabs.beans.Category;
import com.iteso.pdm_scrollabletabs.beans.City;
import com.iteso.pdm_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by hsm-y on 20/03/2018.
 */

public class ItemProductControl {

    private static int id = 0;

    public void addItemProduct (ItemProduct itemProduct, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues products = new ContentValues();
        products.put(DataBaseHandler.KEY_PRODUCT_ID, itemProduct.getCode());
        products.put(DataBaseHandler.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        products.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        products.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, itemProduct.getCategory().getId());
        ContentValues storeProducts = new ContentValues();
        storeProducts.put(DataBaseHandler.KEY_SPRODUCT_ID, id);
        storeProducts.put(DataBaseHandler.KEY_SPRODUCT_IDP, itemProduct.getCode());
        storeProducts.put(DataBaseHandler.KEY_SPRODUCT_IDS, itemProduct.getStore().getId());
        // Inserting Row
        db.insert(DataBaseHandler.TABLE_PRODUCT, null, products);
        db.insert(DataBaseHandler.TABLE_STOREPRODUCT, null, storeProducts);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null; products = null;
        storeProducts = null;
    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh) {
        ArrayList<ItemProduct> products = new ArrayList<>();
        String select = "SELECT " + DataBaseHandler.KEY_PRODUCT_ID + ", "
                + DataBaseHandler.KEY_PRODUCT_TITLE + ", "
                + DataBaseHandler.KEY_PRODUCT_IMAGE + ", "
                + DataBaseHandler.KEY_PRODUCT_CATEGORY + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " WHERE "
                + DataBaseHandler.KEY_PRODUCT_CATEGORY
                + " = " + idCategory;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()) {
            ItemProduct product = new ItemProduct();
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setImage(cursor.getInt(2));
            Category category = new Category();
            category.setId(cursor.getInt(3));
            category.setName(cursor.getString(4));
            product.setCategory(category);
            products.add(product);
        }try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        db = null;
        cursor = null;
        return products;
    }

}
