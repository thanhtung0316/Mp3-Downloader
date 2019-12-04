package com.thanhtung.mp3downloader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.thanhtung.mp3downloader.model.FieldInfo;
import com.thanhtung.mp3downloader.model.OfflineSong;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SystemDataUtils {
    private ContentResolver resolver;

    public SystemDataUtils(Context context) {
        resolver = context.getContentResolver();
    }

    private OfflineSong getRow(Cursor cursor, Class<OfflineSong> clz) throws InstantiationException, IllegalAccessException {
        Field[] fields = clz.getDeclaredFields();
        OfflineSong instance = clz.newInstance();
        for (Field f : fields) {
            f.setAccessible(true);
            FieldInfo info = f.getAnnotation(FieldInfo.class);
            if (info == null) {
                continue;
            }
            int index = cursor.getColumnIndex(info.fieldName());
            String value = cursor.getString(index);
            mapValue(instance, value, f);
        }

        return instance;
    }

    private void mapValue(OfflineSong instance, String value, Field f) throws IllegalAccessException {
        String type = f.getType().getSimpleName();
        if (type.equalsIgnoreCase("int")) {
            f.setInt(instance, Integer.parseInt(value));
            return;
        }
        if (type.equalsIgnoreCase(Long.class.getSimpleName())) {
            f.setLong(instance, Long.parseLong(value));
            return;
        }
        if (type.equalsIgnoreCase(Boolean.class.getSimpleName())) {
            f.setBoolean(instance, Boolean.parseBoolean(value));
            return;
        }
        if (type.equalsIgnoreCase(Float.class.getSimpleName())) {
            f.setFloat(instance, Float.parseFloat(value));
            return;
        }
        if (type.equalsIgnoreCase(Double.class.getSimpleName())) {
            f.setDouble(instance, Double.parseDouble(value));
            return;
        }
        f.set(instance, value);
    }

    private ArrayList<OfflineSong> getData(Cursor cursor, Class<OfflineSong> clz) {
        ArrayList<OfflineSong> data = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            try {
                OfflineSong item = getRow(cursor, clz);
                data.add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        return data;
    }
}
