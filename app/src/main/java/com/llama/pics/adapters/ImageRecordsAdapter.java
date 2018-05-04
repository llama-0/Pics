/*
* This will hold our list of ImageRecords and adapt them to ImagesActivityFragment UI.
* */

package com.llama.pics.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.llama.pics.R;
import com.llama.pics.model.ImageRecord;
import com.llama.pics.utils.VolleySingleton;

import java.util.List;

public class ImageRecordsAdapter extends ArrayAdapter<ImageRecord> {

    private final ImageLoader mImageLoader;

    public ImageRecordsAdapter(@NonNull Context context) {
        super(context, R.layout.image_list_item);

        mImageLoader = VolleySingleton.getInstance(getContext()).getImageLoader();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_list_item, parent, false);
        }

        // instead of using viewholder
        NetworkImageView imageView = convertView.findViewById(R.id.image);

        ImageRecord imageRecord = getItem(position);

        if (imageRecord != null) {
            imageView.setImageUrl(imageRecord.getUrl(), mImageLoader);
        }

        return convertView;
    }

    //This clears the existing data, adds all the new records,
    // and notifies the adapter that the underlying data has been updated.
    public void swapImageRecords(List<ImageRecord> objects) {
        clear();

        for(ImageRecord object : objects) {
            add(object);
        }

        notifyDataSetChanged();
    }
}
