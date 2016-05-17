package com.cyberri.findroid;

/**
 * Created by It's me! on 17-05-2016.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by abhinav on 7/10/15.
 */
public class AdapterImages extends BaseAdapter {

    private int [] mImageId;
    Context context;

    public AdapterImages(int[] mImageId, Context context) {
        this.mImageId = mImageId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImageId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView==null)
        {
            imageView=new ImageView(context);
            imageView.setLayoutParams(new ListView.LayoutParams(300,300));
        }
        else
        {
            imageView= (ImageView) convertView;
        }
        imageView.setImageResource(mImageId[position]);
        return imageView;
    }
}