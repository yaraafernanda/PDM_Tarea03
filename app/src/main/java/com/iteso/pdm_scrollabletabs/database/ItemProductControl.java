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

    public void addItemProduct (ItemProduct itemProduct, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues products = new ContentValues();
        products.put(DataBaseHandler.KEY_PRODUCT_ID, itemProduct.getCode());
        products.put(DataBaseHandler.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        products.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        products.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, itemProduct.getCategory().getId());
        ContentValues storeProducts = new ContentValues();
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
        String select = "SELECT P." + DataBaseHandler.KEY_PRODUCT_ID + ", P."
                + DataBaseHandler.KEY_PRODUCT_TITLE + ", P."
                + DataBaseHandler.KEY_PRODUCT_IMAGE + ", C."
                + DataBaseHandler.KEY_CATEGORY_ID + ", C."
                + DataBaseHandler.KEY_CATEGORY_NAME + ", S."
                + DataBaseHandler.KEY_STORE_ID + ", S."
                + DataBaseHandler.KEY_STORE_NAME + ", S."
                + DataBaseHandler.KEY_STORE_PHONE + ", S."
                + DataBaseHandler.KEY_STORE_THUMBNAIL + ", S."
                + DataBaseHandler.KEY_STORE_LAT + ", S."
                + DataBaseHandler.KEY_STORE_LNG + ", Ci."
                + DataBaseHandler.KEY_CITY_ID + ", Ci."
                + DataBaseHandler.KEY_CITY_NAME + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " P INNER JOIN "
                + DataBaseHandler.TABLE_CATEGORY + " C ON P."
                + DataBaseHandler.KEY_PRODUCT_CATEGORY + " = C."
                + DataBaseHandler.KEY_CATEGORY_ID + " INNER JOIN "
                + DataBaseHandler.TABLE_STOREPRODUCT + " SP ON SP."
                + DataBaseHandler.KEY_SPRODUCT_IDP + " = P."
                + DataBaseHandler.KEY_PRODUCT_ID + " INNER JOIN "
                + DataBaseHandler.TABLE_STORE + " S ON S."
                + DataBaseHandler.KEY_STORE_ID + " = SP."
                + DataBaseHandler.KEY_SPRODUCT_IDS + " INNER JOIN "
                + DataBaseHandler.TABLE_CITY + " Ci ON Ci."
                + DataBaseHandler.KEY_CITY_ID + " = S."
                + DataBaseHandler.KEY_STORE_CITY + " WHERE P."
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
            Store store = new Store();
            store.setId(cursor.getInt(5));
            store.setName(cursor.getString(6));
            store.setPhone(cursor.getString(7));
            store.setThumbnail(cursor.getInt(8));
            store.setLatitude(cursor.getDouble(9));
            store.setLongitude(cursor.getDouble(10));
            City city = new City();
            city.setId(cursor.getInt(11));
            city.setName(cursor.getString(12));


            store.setCity(city);
            product.setStore(store);
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
