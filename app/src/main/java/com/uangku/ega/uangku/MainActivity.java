package com.uangku.ega.uangku;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import com.uangku.ega.uangku.data.dataEntry;
import com.uangku.ega.uangku.data.dataHelper;
import com.uangku.ega.uangku.utility.Utility;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    protected Cursor cursor;

    private SQLiteDatabase mDb;

    private MoneyAdapter mAdapter;
    dataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt_pemasukan = (TextView) findViewById(R.id.textView9);
        TextView txt_pengeluaran = (TextView) findViewById(R.id.textView10);
        TextView txt_total = (TextView) findViewById(R.id.textView14);

//        test.setText("200.000.000");

//        dbcenter = new dataHelper(this);

        dataHelper dbHelper = new dataHelper(this);
        mDb=dbHelper.getWritableDatabase();

        Cursor pemasukan = mDb.rawQuery("SELECT SUM(nominal) as total from "+dataEntry.entryAdapter.TABLE_NAME+" where "+dataEntry.entryAdapter.STATUS+" = 'masukan'", null);
        String rets = null;
        if(pemasukan.moveToNext()) {
            rets = String.valueOf(pemasukan.getLong(0));
        }

        txt_pemasukan.setText(Utility.currencyFormat(rets));



        Cursor pengeluaran= mDb.rawQuery("SELECT SUM(nominal) as total from "+dataEntry.entryAdapter.TABLE_NAME+" where "+dataEntry.entryAdapter.STATUS+" = 'keluaran'", null);
        String ret = null;
        if(pengeluaran.moveToNext()) {
            ret = String.valueOf(pengeluaran.getLong(0));
        }
        txt_pengeluaran.setText(Utility.currencyFormat(ret));
        Long a = Long.parseLong(rets);
        Long b = Long.parseLong(ret);
        Long c = a - b;
        txt_total.setText(Utility.currencyFormat(c.toString()));

//        Toast.makeText(addReport.this, ret, Toast.LENGTH_SHORT).show();

        cursor = getAllData();

        RecyclerView dataRecycler;
        dataRecycler = (RecyclerView) findViewById(R.id.rpt);
        dataRecycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MoneyAdapter(this, cursor);
        dataRecycler.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeData(id);
                mAdapter.swapCursor(getAllData());
            }
        }).attachToRecyclerView(dataRecycler);
    }

    private Cursor getAllData() {
        return mDb.query(
                dataEntry.entryAdapter.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                dataEntry.entryAdapter._ID+" desc"
        );
    }

    public boolean removeData(long id) {
        return mDb.delete(dataEntry.entryAdapter.TABLE_NAME,
                dataEntry.entryAdapter._ID+"="+id, null) >0;
    }
}
