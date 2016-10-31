package mibh.mis.tawamart.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.adapter.GalleryAdapter;
import mibh.mis.tawamart.manager.RealmManager;
import mibh.mis.tawamart.realmobj.ImageStore;

public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";
    private GalleryAdapter galleryAdapter;
    private android.widget.GridView gvGallery;
    private SparseBooleanArray checkStates;
    private List<ImageStore> listImageStore;

    public GalleryFragment() {
        super();
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        setHasOptionsMenu(true);
        this.gvGallery = (GridView) rootView.findViewById(R.id.gvGallery);
        listImageStore = RealmManager.getInstance().getAllImage();
        checkStates = new SparseBooleanArray(listImageStore.size());
        galleryAdapter = new GalleryAdapter();
        galleryAdapter.setCheckStates(checkStates);
        galleryAdapter.setListImageStore(listImageStore);
        gvGallery.setAdapter(galleryAdapter);
        gvGallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "TAWAMART");
                File output = new File(imagesFolder, listImageStore.get(i).getFilename());
                Uri uri2 = Uri.fromFile(output);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(uri2, "image/*");
                startActivity(intent);
                return true;
            }
        });
        gvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (checkStates.get(i, false)) {
                    checkStates.put(i, false);
                } else {
                    checkStates.put(i, true);
                }
                galleryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void shareLine() {
        ArrayList<Uri> imageUris = new ArrayList<>();
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "TAWAMART");
        Uri newUri;

        for (int i = 0; i < listImageStore.size(); i++) {
            if (checkStates.get(i, false)) {
                File output = new File(imagesFolder, listImageStore.get(i).getFilename());
                if (output.exists()) {
                    System.gc();
                    newUri = Uri.fromFile(output);
                    imageUris.add(newUri);
                }
            }
        }
        try {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.setPackage("jp.naver.line.android");
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "Share images to.."));
        } catch (Exception e) {
        }
    }

    private void deleteImages() {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("คุณต้องการลบรูปภาพ ?")
                .setContentText("เมื่อทำการลบรายการนี้จะหายไป และไม่สามารถคืนค่าได้")
                .setConfirmText("ตกลง")
                .showCancelButton(true)
                .setCancelText("ยกเลิก")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "TAWAMART");
                        for (int i = 0; i < listImageStore.size(); i++) {
                            if (checkStates.get(i, false)) {
                                File output = new File(imagesFolder, listImageStore.get(i).getFilename());
                                RealmManager.getInstance().deleteImageData(listImageStore.get(i).getFilename());
                                if (output.exists()) {
                                    output.delete();
                                    System.gc();
                                }
                            }
                        }
                        listImageStore = RealmManager.getInstance().getAllImage();
                        checkStates = new SparseBooleanArray(listImageStore.size());
                        galleryAdapter.setCheckStates(checkStates);
                        galleryAdapter.setListImageStore(listImageStore);
                        galleryAdapter.notifyDataSetChanged();

                        sDialog.setTitleText("สำเร็จ !!")
                                .setConfirmText("ตกลง")
                                .setContentText("ทำการลบเรียบร้อย")
                                .showCancelButton(false)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shareImage:
                shareLine();
                return true;
            case R.id.deleteImage:
                deleteImages();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
