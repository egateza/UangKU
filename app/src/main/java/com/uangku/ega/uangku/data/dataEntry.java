package com.uangku.ega.uangku.data;

import android.provider.BaseColumns;

/**
 * Created by egateza on 1/16/2018.
 */

public class dataEntry {

    public static final class entryAdapter implements BaseColumns{
        public static final String TABLE_NAME ="keuangan";
        public static final String SALDO ="saldo";
        public static final String NOMINAL ="nominal";
        public static final String STATUS ="status";
        public static final String KETERANGAN ="keterangan";
        public static final String DATE = "create_date";
    }
}
