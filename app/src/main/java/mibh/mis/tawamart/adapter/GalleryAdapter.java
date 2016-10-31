package mibh.mis.tawamart.adapter;

import android.net.Uri;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.List;

import mibh.mis.tawamart.realmobj.ImageStore;
import mibh.mis.tawamart.view.FirstListItem;
import mibh.mis.tawamart.view.GalleryGridItem;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class GalleryAdapter extends BaseAdapter {

    private SparseBooleanArray checkStates;
    private List<ImageStore> listImageStore;

    public void setListImageStore(List<ImageStore> listImageStore) {
        this.listImageStore = listImageStore;
    }

    public void setCheckStates(SparseBooleanArray checkStates) {
        this.checkStates = checkStates;
    }

    @Override
    public int getCount() {
        if (listImageStore == null) return 0;
        else return listImageStore.size();
    }

    @Override
    public Object getItem(int i) {
        return listImageStore.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GalleryGridItem item;
        if (view != null)
            item = (GalleryGridItem) view;
        else
            item = new GalleryGridItem(viewGroup.getContext());

        ImageStore imageStore = (ImageStore) getItem(i);

        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "TAWAMART");
        File output = new File(imagesFolder, imageStore.getFilename());
        item.setThumpImage(Uri.fromFile(output));
        item.setTextDescImage(imageStore.getFilename());
        item.setOverlayItem(checkStates.get(i, false));
        return item;
    }
}
