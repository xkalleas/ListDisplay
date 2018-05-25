package com.example.listdisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mDataSource = null;

    public AlbumAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDataSource(String[] items) {
        mDataSource = items;
    }

    @Override
    public int getCount() {
        if (mDataSource == null) {
            return 0;
        }
        else {
            return mDataSource.length;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mDataSource == null) {
            return null;
        }
        else {
            return mDataSource[position];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_album, parent, false);

        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.album_list_title);

        // Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.album_list_thumbnail);

        String album = (String) getItem(position);

        titleTextView.setText(album);
        thumbnailImageView.setImageResource(R.mipmap.ic_launcher);

        return rowView;
    }
}
