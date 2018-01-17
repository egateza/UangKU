package com.uangku.ega.uangku.utility;

import android.app.Activity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by egateza on 1/16/2018.
 */

public class Utility extends Activity {
    public static String defaultNoll(String datas) {
        String res = "";
        if (datas.equals("")) {
            res = "0";
        } else {
            res = datas;
        }

        return res;
    }

    public static String currencyFormat(String money) {
        money = defaultNoll(money);
        try {
            NumberFormat formatter = new DecimalFormat("##,###,##0");
            String a = money.replace(",", ".");
            if (a.length() > 3) {
                money = formatter.format(Double.parseDouble(a));
            } else {
                money = a;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


        return money;
    }
}
