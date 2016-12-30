package com.techpenta.camera;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class CollectionListAdapter extends ArrayAdapter<CollectionProduct> {
    public CollectionListAdapter(Context context, int resource, List<CollectionProduct> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listitem, null);
        }
        CollectionProduct collectionProduct = getItem(position);
       // ImageView img = (ImageView) v.findViewById(R.id.ivListItem);
       // TextView txt=(TextView)v.findViewById(R.id.tvlistItem);

       // img.setImageResource(collectionProduct.getImageId());
      //  txt.setText(collectionProduct.getTitle());

        return v;
    }
}
