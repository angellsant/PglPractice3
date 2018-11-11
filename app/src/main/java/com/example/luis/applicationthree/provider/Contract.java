package com.example.luis.applicationthree.provider;


import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final String AUTHORITY = "com.example.luis.applicationthree.DataProvider";

    public static final class Constructor implements BaseColumns {

        // Table
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/Constructor");

        // Table columns
        public static final String NAMECONSTRUCTOR = "nameConstructor";
        public static final String YEARBIRDTH = "yearBirdth";

    }

}
