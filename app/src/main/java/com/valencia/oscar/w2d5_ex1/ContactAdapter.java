package com.valencia.oscar.w2d5_ex1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private ArrayList<ContactItem> dataSet;
    private static final String LOG = ContactAdapter.class.getSimpleName()+"_TAG";

    public ContactAdapter(ArrayList<ContactItem> dataSet) {
        this.dataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContact;
        public ImageView tvPhoto;
        public ViewHolder(View v) {
            super(v);
            tvContact = v.findViewById(R.id.tvContact);
            tvPhoto = v.findViewById(R.id.tvPhoto);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactItem item = dataSet.get(position);
        holder.tvContact.setText(
                item.getName()+" "+
                item.getLastName()+" "+
                item.getEmail()+" "+
                item.getPhone());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(item.getPhoto(), options);
        holder.tvPhoto.setImageBitmap(bitmap);
        Log.d(LOG,"Binging item "+item.getName()+" "+item.getLastName());
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 :dataSet.size();
    }


}
