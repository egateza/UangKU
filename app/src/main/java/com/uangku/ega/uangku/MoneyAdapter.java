package com.uangku.ega.uangku;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uangku.ega.uangku.data.dataEntry;
import com.uangku.ega.uangku.utility.Utility;

/**
 * Created by egateza on 1/16/2018.
 */

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyHolder> {

    private Context mContext;
    private Cursor mCursor;

    public MoneyAdapter (Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }
    @Override
    public MoneyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_report, parent, false);
        return new MoneyHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(MoneyHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;
//        String nama =mCursor.getString(mCursor.getColumnIndex(oStoreContract.oStoreEntry.COLUMNS_NAMA_BARANG));
//        double harga = mCursor.getDouble(mCursor.getColumnIndex(oStoreContract.oStoreEntry.COLUMNS_HARGA_BARANG));
//        long id = mCursor.getLong(mCursor.getColumnIndex(oStoreContract.oStoreEntry._ID));
//        holder.namaBarang.setText(nama);
//        holder.hargaBarang.setText(String.valueOf(harga));
//        holder.itemView.setTag(id);

        Long nominal = (long) mCursor.getDouble(mCursor.getColumnIndex(dataEntry.entryAdapter.NOMINAL));
        String keterangan = mCursor.getString(mCursor.getColumnIndex(dataEntry.entryAdapter.KETERANGAN));
        String tanggal= mCursor.getString(mCursor.getColumnIndex(dataEntry.entryAdapter.DATE));
        String status = mCursor.getString(mCursor.getColumnIndex(dataEntry.entryAdapter.STATUS));
        long id = mCursor.getLong(mCursor.getColumnIndex(dataEntry.entryAdapter._ID));
        if (status.equals("masukan")) {
            holder.a_nominal.setText(Utility.currencyFormat(nominal.toString()));
        } else {
            holder.a_nominal.setText("-"+Utility.currencyFormat(nominal.toString()));
        }
        holder.a_tanggal.setText(tanggal);
        holder.a_keterangan.setText(keterangan);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor!=null) mCursor.close();
        mCursor=newCursor;
        if(newCursor!=null){
            this.notifyDataSetChanged();
        }
    }

    class MoneyHolder extends RecyclerView.ViewHolder{
        TextView a_nominal;
        TextView a_tanggal;
        TextView a_keterangan;
        public MoneyHolder (View itemView){
            super(itemView);
            a_nominal = (TextView) itemView.findViewById(R.id.nominal);
            a_tanggal = (TextView) itemView.findViewById(R.id.tgl);
            a_keterangan= (TextView) itemView.findViewById(R.id.ket);
        }
    }
}
