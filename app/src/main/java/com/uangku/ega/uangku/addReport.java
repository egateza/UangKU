package com.uangku.ega.uangku;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.uangku.ega.uangku.data.dataEntry;
import com.uangku.ega.uangku.data.dataHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addReport extends AppCompatActivity {
    private SQLiteDatabase mDb;
    String status;
    RadioGroup rGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataHelper dbHelper = new dataHelper(this);
        mDb=dbHelper.getWritableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        final EditText nominal = (EditText) findViewById(R.id.nominal);
        final EditText keterangan = (EditText) findViewById(R.id.keterangan);
        RadioButton masukan = (RadioButton) findViewById(R.id.masuk);
        RadioButton keluar = (RadioButton) findViewById(R.id.keluar);
         rGroup = (RadioGroup) findViewById(R.id.rGroup);





        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ids = rGroup.getCheckedRadioButtonId();

                if (ids == R.id.masuk) {
                    status = "masukan";
                }

                else if (ids == R.id.keluar) {
                    status = "keluaran";
                } else {
                    status = "salah";
                }

                addReport(keterangan.getText().toString(), Double.parseDouble(nominal.getText().toString()), status);
                Toast.makeText(addReport.this, status, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public long addReport(String keterangan, double nominal, String status) {

        try {
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy, HH:mm");
            String strdate = sdf1.format(c1.getTime());

            ContentValues cv = new ContentValues();
            cv.put(dataEntry.entryAdapter.KETERANGAN, keterangan);
            cv.put(dataEntry.entryAdapter.NOMINAL, nominal);
            cv.put(dataEntry.entryAdapter.STATUS, status);
            cv.put(dataEntry.entryAdapter.DATE, strdate);

            //        Toast.makeText(addReport.this, "ini tanggal : "+strdate+" ini nominal : "+ nominal+ "ini keterangan "+keterangan, Toast.LENGTH_SHORT).show();
            Toast.makeText(addReport.this, "Penambahan Berhasil!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(addReport.this, navigation.class);
            startActivity(i);
            return mDb.insert(dataEntry.entryAdapter.TABLE_NAME, null, cv);
        } catch (Exception e) {
            Toast.makeText(addReport.this, "Gagal Mendambah Transaksi!", Toast.LENGTH_SHORT).show();
        }

        return 0;
    }
}
