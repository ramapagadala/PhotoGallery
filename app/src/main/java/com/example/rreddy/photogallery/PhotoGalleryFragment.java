package com.example.rreddy.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rreddy on 1/22/2015.
 */
public class PhotoGalleryFragment extends Fragment {
    private static final String TAG = "PhotoGalleryFragment";
    GridView mGridView;
    ArrayList<GalleryItem> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        new FetchItemsTask().execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mGridView = (GridView)v.findViewById(R.id.gridView);
        setupAdapter();
        return v;
    }

    /* because GridView has no handy GridFragment class, you have to build your own adapter management code */
    void setupAdapter() {
        if (getActivity() == null || mGridView == null) return;
        if (mItems != null) {
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            mGridView.setAdapter(null);
        }
    }

    /* this class should be inside the Fragment class as AsyncTask will
    * report the results back to the Fragment/Activity that started */
    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<GalleryItem>> {
        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
            return (new FlickrFetcher().fetchItems());
        }
        @Override
        protected void onPostExecute(ArrayList<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    /*TODO not sure why this class is defined inside Fragment class*/
    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {
        public GalleryItemAdapter(ArrayList<GalleryItem> items) {
            super(getActivity(), 0, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.gallery_item, parent, false);
            }
            ImageView imageView = (ImageView)convertView
                    .findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.drawable.ic_launcher);
            return convertView;
        }
    }
}
