package com.iteso.pdm_scrollabletabs.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm_scrollabletabs.beans.Category;
import com.iteso.pdm_scrollabletabs.beans.City;
import com.iteso.pdm_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by hsm-y on 20/03/2018.
 */

public class CategoryControl {

    public ArrayList<Category> getCategories(DataBaseHandler dh) {
        ArrayList<Category> categories = new ArrayList<>();
        String select = "SELECT " + DataBaseHandler.KEY_CATEGORY_ID + ", "
                + DataBaseHandler.KEY_CATEGORY_NAME + " FROM "
                + DataBaseHandler.TABLE_CATEGORY;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        db = null;
        cursor = null;
        return categories;
    }

}
