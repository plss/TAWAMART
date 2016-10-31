package mibh.mis.tawamart.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.activity.CameraActivity;
import mibh.mis.tawamart.adapter.ReceiveAdapter;
import mibh.mis.tawamart.dao.EmpDao;
import mibh.mis.tawamart.dao.ListDao;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.dao.PoHeaderDao;
import mibh.mis.tawamart.dao.VendorDao;
import mibh.mis.tawamart.manager.DataManager;
import mibh.mis.tawamart.manager.PreferencesManage;
import mibh.mis.tawamart.service.CallService;
import mibh.mis.tawamart.utils.Utils;

import static android.app.Activity.RESULT_OK;

public class ReceiveFragment extends Fragment {

    private static final String TAG = "ReceiveFragment";
    private Button btnSelectCookingZoneReceive;
    private TextView tvDateOrderAndShippedReceive;
    private Button btnSelectPoReceive;
    private ListView listViewReceive;
    private Button btnConfirmReceive;
    private Button btnSaveReceive;
    private Button btnCameraReceive;
    private Button btnScanPoReceive;
    private Button btnShareReceive;
    private List<PoDetailDao> originalDetailList, currentDetailList;
    private VendorDao vendor;
    private ListDao cookingZone;
    private String dateOrder, poSelected, dateShipped;
    private Double sumPrice;
    private ReceiveAdapter listAdapter;
    private HashMap<String, String> checkState;
    private List<PoHeaderDao> headerList;

    public ReceiveFragment() {
        super();
    }

    public static ReceiveFragment newInstance() {
        ReceiveFragment fragment = new ReceiveFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_receive, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.btnCameraReceive = (Button) rootView.findViewById(R.id.btnCameraReceive);
        this.btnSaveReceive = (Button) rootView.findViewById(R.id.btnSaveReceive);
        this.btnConfirmReceive = (Button) rootView.findViewById(R.id.btnConfirmReceive);
        this.listViewReceive = (ListView) rootView.findViewById(R.id.listViewReceive);
        this.btnSelectPoReceive = (Button) rootView.findViewById(R.id.btnSelectPoReceive);
        this.tvDateOrderAndShippedReceive = (TextView) rootView.findViewById(R.id.tvDateOrderAndShippedReceive);
        this.btnSelectCookingZoneReceive = (Button) rootView.findViewById(R.id.btnSelectCookingZoneReceive);
        this.btnScanPoReceive = (Button) rootView.findViewById(R.id.btnScanPoReceive);
        this.btnShareReceive = (Button) rootView.findViewById(R.id.btnShareReceive);

        btnCameraReceive.setOnClickListener(onClickListener);
        btnSaveReceive.setOnClickListener(onClickListener);
        btnConfirmReceive.setOnClickListener(onClickListener);
        btnSelectCookingZoneReceive.setOnClickListener(onClickListener);
        btnSelectPoReceive.setOnClickListener(onClickListener);
        btnScanPoReceive.setOnClickListener(onClickListener);
        btnShareReceive.setOnClickListener(onClickListener);

        vendor = getDefaultVendor(PreferencesManage.getInstance().getVendorDefault());
        cookingZone = getDefaultCookingZone(PreferencesManage.getInstance().getCookingZonrDefault());

        originalDetailList = new ArrayList<>();
        listAdapter = new ReceiveAdapter();
        listAdapter.setDetailList(originalDetailList);
        listViewReceive.setAdapter(listAdapter);
        listViewReceive.setEmptyView(rootView.findViewById(R.id.emptyElementReceive));
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode + " " + resultCode + " " + data);
        if (requestCode == 11 && resultCode == RESULT_OK) {

        } else if (requestCode == 0 && resultCode == -1) {
            String contents = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            new GetPoDataByTranId(contents.trim()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void setupDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_third);
        final Button btnCancelDialogReceive = (Button) dialog.findViewById(R.id.btnCancelDialogReceive);
        final Button btnOkDialogReceive = (Button) dialog.findViewById(R.id.btnOkDialogReceive);
        final Button btnSelectVendorDialogReceive = (Button) dialog.findViewById(R.id.btnSelectVendorDialogReceive);
        final Button btnDatePickerDialogReceive = (Button) dialog.findViewById(R.id.btnDatePickerDialogReceive);
        final Button btnSelectCookZoneDialogReceive = (Button) dialog.findViewById(R.id.btnSelectCookZoneDialogReceive);

        if (cookingZone != null)
            btnSelectCookZoneDialogReceive.setText(cookingZone.getThDesc());
        if (vendor != null)
            btnSelectVendorDialogReceive.setText(vendor.getVendorShortName());
        if (dateOrder != null)
            btnDatePickerDialogReceive.setText(dateOrder);

        View.OnClickListener onBtnDialogClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnCancelDialogReceive) {
                    dialog.dismiss();
                } else if (view == btnOkDialogReceive) {
                    if (cookingZone != null && vendor != null && dateOrder != null) {
                        dialog.dismiss();
                        PreferencesManage.getInstance().setVendorDefault(vendor.getVendorId());
                        PreferencesManage.getInstance().setCookingZoneDefault(cookingZone.getRunId());
                        new GetPoData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                } else if (view == btnDatePickerDialogReceive) {
                    Calendar c = Calendar.getInstance();
                    final int mYear = c.get(Calendar.YEAR);
                    final int mMonth = c.get(Calendar.MONTH);
                    final int mDay = c.get(Calendar.DAY_OF_MONTH);
                    new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            dateOrder = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1) + "/" + year;
                            btnDatePickerDialogReceive.setText("สั่ง : " + dateOrder);
                        }
                    }, mYear, mMonth, mDay).show();
                } else if (view == btnSelectVendorDialogReceive) {
                    final List<VendorDao> listVendor = DataManager.getInstance().getListVendor();
                    String[] arr = new String[listVendor.size()];
                    int count = 0;
                    for (VendorDao vendor : listVendor) {
                        arr[count++] = vendor.getVendorShortName();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            vendor = listVendor.get(which);
                            btnSelectVendorDialogReceive.setText(listVendor.get(which).getVendorShortName());
                        }
                    });
                    builder.show();
                } else if (view == btnSelectCookZoneDialogReceive) {
                    final List<ListDao> listCookingZone = DataManager.getInstance().getListCookingZone();
                    String[] arr = new String[listCookingZone.size()];
                    int count = 0;
                    for (ListDao cookingZone : listCookingZone) {
                        arr[count++] = cookingZone.getThDesc();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            cookingZone = listCookingZone.get(which);
                            btnSelectCookZoneDialogReceive.setText(listCookingZone.get(which).getThDesc());
                        }
                    });
                    builder.show();
                }
            }
        };

        btnSelectCookZoneDialogReceive.setOnClickListener(onBtnDialogClickListener);
        btnSelectVendorDialogReceive.setOnClickListener(onBtnDialogClickListener);
        btnDatePickerDialogReceive.setOnClickListener(onBtnDialogClickListener);
        btnOkDialogReceive.setOnClickListener(onBtnDialogClickListener);
        btnCancelDialogReceive.setOnClickListener(onBtnDialogClickListener);

        dialog.show();

    }

    private void setAllCompleteCheckState() {
        for (int i = 0; i < originalDetailList.size(); i++) {
            if (!checkState.containsKey(originalDetailList.get(i).getTransectionId() + originalDetailList.get(i).getItemId())) {
                checkState.put(originalDetailList.get(i).getTransectionId() + originalDetailList.get(i).getItemId(), "COMPLETE");
                originalDetailList.get(i).setVolReceive(originalDetailList.get(i).getVolProduct());
            }
        }
        listAdapter.notifyDataSetChanged();
    }

    private void splitPoDetailList(String resultDetail, String resultHeader) {

        if (resultDetail.equals("error") || resultHeader.equals("error")) {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("ผิดพลาด")
                    .setContentText("ไม่สามารถเชื่อมต่อกับ server กรุณาลองใหม่อีกครัง")
                    .show();
            return;
        }

        Type listType = new TypeToken<List<PoHeaderDao>>() {
        }.getType();
        headerList = new Gson().fromJson(resultHeader, listType);
        listType = new TypeToken<List<PoDetailDao>>() {
        }.getType();
        originalDetailList = new Gson().fromJson(resultDetail, listType);
        changePoReceive(0);

    }

    private void changePoReceive(int index) {
        if (headerList.size() <= 0) return;
        List<PoDetailDao> tempList = new ArrayList<>();
        cookingZone = getDefaultCookingZone(headerList.get(index).getCookingzoneId());
        vendor = getDefaultVendor(headerList.get(index).getVendorId());
        dateOrder = Utils.toDateFormat(headerList.get(index).getDateCreate());
        for (int i = 0; i < originalDetailList.size(); i++) {
            if (headerList.get(index).getTransectionId().equals(originalDetailList.get(i).getTransectionId())) {
                tempList.add(originalDetailList.get(i));
            }
        }
        btnSelectPoReceive.setText(headerList.get(index).getTransectionId());
        poSelected = headerList.get(index).getTransectionId();

        currentDetailList = tempList;
        checkState = new HashMap<>(tempList.size());
        listAdapter.setDetailList(currentDetailList);
        listAdapter.setCheckState(checkState);
        listAdapter.notifyDataSetChanged();

        if (cookingZone != null)
            btnSelectCookingZoneReceive.setText(cookingZone.getThDesc());
        if (dateOrder != null)
            tvDateOrderAndShippedReceive.setText("วันที่สั่ง " + dateOrder);
    }

    private void shareDataToLine() {
        if (cookingZone != null && vendor != null && dateOrder != null && currentDetailList != null) {
            String Sh;
            Sh = "เรียน Chef/และผู้เกี่ยวข้องทุกท่าน" + "\n";
            Sh = Sh + "FYI: RC. " + poSelected + " รายการรับวัตถุดิบ\n";
            Sh = Sh + "ครัว " + cookingZone.getThDesc() + "\n";
            Sh = Sh + "Supplier " + vendor.getVendorShortName() + "\n";
            Sh = Sh + "วันที่สั่ง " + dateOrder + "\n";
            Calendar c = Calendar.getInstance();
            final int mYear = c.get(Calendar.YEAR);
            final int mMonth = c.get(Calendar.MONTH);
            final int mDay = c.get(Calendar.DAY_OF_MONTH);
            Sh = Sh + "วันที่รับ " + (String.format("%02d", mDay) + "/" + String.format("%02d", mMonth + 1) + "/" + mYear) + "\n";
            Sh = Sh + "-โดยรายการวัตถุดิบมีดังนี้" + "\n";
            sumPrice = 0.0;
            for (int i = 0; i < currentDetailList.size(); i++) {
                if (currentDetailList.get(i).getVolReceive() != null) {
                    Sh = Sh + (i + 1) + ". " + currentDetailList.get(i).getProductName() + " " + Utils.priceWithDecimal(currentDetailList.get(i).getVolReceive()) + " " + currentDetailList.get(i).getUnitName() + "\n";
                    sumPrice += currentDetailList.get(i).getPricePo() * currentDetailList.get(i).getVolReceive();
                }
            }
            Sh = Sh + "รวม " + currentDetailList.size() + " รายการ\n";
            Sh = Sh + "ราคารวม " + Utils.priceWithDecimal(sumPrice) + " บาท\n";
            EmpDao empDao = DataManager.getInstance().getEmpDao();
            Sh = Sh + "ผู้ขอ " + empDao.getFirstNameTh() + " " + empDao.getLastNameTh() + "\n";
            Sh = Sh + "ติดต่อ " + empDao.getMobile() + "\n";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage("jp.naver.line.android");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, Sh);
            startActivity(Intent.createChooser(intent, "Intent"));
        } else {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("ไม่พบข้อมูล")
                    .setContentText("กรุณาเลือกข้อมูลเริ่มต้นก่อน")
                    .show();
        }
    }

    private void setDataBeforeSave() {
        EmpDao empDao = DataManager.getInstance().getEmpDao();
        for (PoDetailDao poDetailDao : currentDetailList) {
            poDetailDao.setEmpReceiveId(empDao.getEmpId());
            poDetailDao.setEmpReceive(empDao.getFirstNameTh() + " " + empDao.getLastNameTh());
        }
    }

    private boolean isCallable(Intent intent) {
        List<ResolveInfo> list = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private VendorDao getDefaultVendor(String vendorId) {
        List<VendorDao> listVendor = DataManager.getInstance().getListVendor();
        for (int i = 0; i < listVendor.size(); i++) {
            if (listVendor.get(i).getVendorId().equals(vendorId))
                return listVendor.get(i);
        }
        return null;
    }

    private ListDao getDefaultCookingZone(String cookingZoneId) {
        List<ListDao> listCookingZone = DataManager.getInstance().getListCookingZone();
        for (int i = 0; i < listCookingZone.size(); i++) {
            if (listCookingZone.get(i).getRunId().equals(cookingZoneId))
                return listCookingZone.get(i);
        }
        return null;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnCameraReceive) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                intent.putExtra("POSELECTED", poSelected == null ? "NOTHING" : poSelected);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else if (view == btnSaveReceive) {
                setDataBeforeSave();
                new SaveReceive(new Gson().toJson(currentDetailList)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btnConfirmReceive) {
                setAllCompleteCheckState();
            } else if (view == btnSelectCookingZoneReceive) {
                setupDialog();
            } else if (view == btnSelectPoReceive) {
                if (headerList != null) {
                    String[] arr = new String[headerList.size()];
                    int count = 0;
                    for (PoHeaderDao poHeaderDao : headerList)
                        arr[count++] = poHeaderDao.getTransectionId();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            changePoReceive(which);
                            btnSelectPoReceive.setText(headerList.get(which).getTransectionId());
                        }
                    });
                    builder.show();
                }
            } else if (view == btnScanPoReceive) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.setPackage("com.google.zxing.client.android");
                intent.putExtra("SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR,EAN_13,EAN_8,UPC_A,QR_CODE");
                if (isCallable(intent)) {
                    startActivityForResult(intent, 0);
                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("ไม่พบแอพพลิเคชั่นแสกน")
                            .setContentText("กรุณาทำการติดตั้งแอพพลิเคชั่นแสกน")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    final String appPackageName = "com.google.zxing.client.android";
                                    try {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                    }
                                }
                            })
                            .show();
                }
            } else if (view == btnShareReceive) {
                shareDataToLine();
            }
        }
    };

    private class GetPoData extends AsyncTask<String, Void, String> {

        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        String resultDetail, resultHeader;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            resultDetail = new CallService().getPoReceive(Utils.yyMMddFormatDate(dateOrder), vendor.getVendorId(), cookingZone.getRunId());
            //resultHeader = new CallService().getPoReceive(Utils.yyMMddFormatDate(dateOrder), vendor.getVendorId(), cookingZone.getRunId());
            resultHeader = new CallService().getHdReceive(Utils.yyMMddFormatDate(dateOrder), vendor.getVendorId(), cookingZone.getRunId());
            Log.d(TAG, "doInBackground: " + resultHeader);
            Log.d(TAG, "doInBackground: " + resultDetail);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog != null) {
                dialog.dismiss();
            }

            splitPoDetailList(resultDetail, resultHeader);

        }
    }

    private class GetPoDataByTranId extends AsyncTask<String, Void, String> {

        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        String resultDetail, resultHeader, tranId;

        public GetPoDataByTranId(String tranId) {
            this.tranId = tranId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            resultDetail = new CallService().getTranIdPoDt(tranId);
            resultHeader = new CallService().getTranIdPoHd(tranId);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog != null) {
                dialog.dismiss();
            }

            splitPoDetailList(resultDetail, resultHeader);

        }
    }

    private class SaveReceive extends AsyncTask<String, Void, String> {

        private SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        private String jsonReceive;

        public SaveReceive(String jsonReceive) {
            this.jsonReceive = jsonReceive;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            return new CallService().saveReceive(jsonReceive);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (dialog != null)
                dialog.dismiss();

            if (s.equals("True")) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("สำเร็จ")
                        .setContentText("ทำการบันทึกรายการเสร็จสิ้น")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                /*getActivity().finish();
                                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);*/
                            }
                        })
                        .show();
            } else {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("ผิดพลาด")
                        .setContentText("บันทึกรายการผิดพลาด กรุณาลองใหม่อีกครัง")
                        .show();
            }
        }

    }

}
