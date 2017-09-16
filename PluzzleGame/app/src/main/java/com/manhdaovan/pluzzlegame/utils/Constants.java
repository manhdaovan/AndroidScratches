package com.manhdaovan.pluzzlegame.utils;

import android.graphics.Bitmap;

public class Constants {
    public static final String INTENT_COLUMN_PIECES = "column_pieces";
    public static final String INTENT_ROW_PIECES = "row_pieces";

    public static final int REQUEST_SELECT_PICTURE = 0x01;

    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final Bitmap.CompressFormat DEFAULT_IMG_FORMAT = Bitmap.CompressFormat.JPEG;

    public static final String[] ROW_NUM_PIECES = {"3 X", "4 X", "5 X", "6 X", "7 X", "8 X", "9 X", "10 X"};
    public static final String[] COLUMN_NUM_PIECES = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    public static final int COLUMN_OFFSET = 2;
    public static final int ROW_OFFSET = 2;
}
