/*
* This will hold our list of ImageRecords and adapt them to ImagesActivityFragment UI.
* */

package com.llama.pics.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.llama.pics.R;
import com.llama.pics.model.ImageRecord;
import com.llama.pics.utils.VolleySingleton;

import java.util.List;

public class ImageRecordsAdapter extends RecyclerView.Adapter<ImageRecordsAdapter
        .ImageViewHolder> {

    private Context mCtx;
    private List<ImageRecord> mImageRecords;

    private ImageLoader mImageLoader;
    private ImageItemClickListener imageItemClickListener;

    // to handle clicks on images
    public interface ImageItemClickListener {
        void onItemClick(int position);
    }

    public void setImageItemClickListener(ImageItemClickListener listener) {
        imageItemClickListener = listener;
    }

    public ImageRecordsAdapter(@NonNull Context mCtx, List<ImageRecord> mImageRecords) {

        this.mCtx = mCtx;
        this.mImageRecords = mImageRecords;

        mImageLoader = VolleySingleton.getInstance(mCtx).getImageLoader();
    }

    @NonNull
    @Override
    public ImageRecordsAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        view = layoutInflater.inflate(R.layout.image_list_item, parent, false);
        return new ImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ImageRecordsAdapter.ImageViewHolder holder, int position) {

        final ImageRecord imageRecord = this.mImageRecords.get(position);

        if (imageRecord != null) {
            holder.imageView.setImageUrl(imageRecord.getUrl(), mImageLoader);
        }
    }

    public void swap(List<ImageRecord> imageRecords)
    {
        if(imageRecords == null || imageRecords.size()==0)
            return;
        if (mImageRecords != null && mImageRecords.size()>0)
            mImageRecords.clear();
        assert mImageRecords != null;
        mImageRecords.addAll(imageRecords);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mImageRecords.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            imageItemClickListener.onItemClick(position) ;
                        }
                    }
                }
            });
        }
    }
}
