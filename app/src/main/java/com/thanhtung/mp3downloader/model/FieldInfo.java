package com.thanhtung.mp3downloader.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInfo {
    String fieldName();
}
