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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.activity.ProductActivity;
import mibh.mis.tawamart.adapter.OrderListAdapter;
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

public class OrderFragment extends Fragment implements OrderListAdapter.OnDataUpdatedListener {

    private static final String TAG = "OrderFragment";

    private ListDao cookingZone;
    private VendorDao vendor;
    private String dateOrder, dateShipped;
    private List<PoDetailDao> detailList;
    private List<PoHeaderDao> headerList;
    private Button btnSelectCookingZoneOrder;
    private TextView tvDateOrderAndShippedOrder;
    private EditText etSearchListOrder;
    private ListView listViewOrder;
    private Button btnAddOrder;
    private Button btnSaveOrder;
    private Button btnShareOrder;
    private Button btnSearchListOrder;
    private TextView tvSummaryListOrder;
    private OrderListAdapter listAdapter;
    private String poSelected;

    public OrderFragment() {
        super();
    }

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        this.tvSummaryListOrder = (TextView) rootView.findViewById(R.id.tvSummaryListOrder);
        this.btnSaveOrder = (Button) rootView.findViewById(R.id.btnSaveOrder);
        this.btnAddOrder = (Button) rootView.findViewById(R.id.btnAddOrder);
        this.btnShareOrder = (Button) rootView.findViewById(R.id.btnShareOrder);
        this.listViewOrder = (ListView) rootView.findViewById(R.id.listViewOrder);
        this.etSearchListOrder = (EditText) rootView.findViewById(R.id.etSearchListOrder);
        this.tvDateOrderAndShippedOrder = (TextView) rootView.findViewById(R.id.tvDateOrderAndShippedOrder);
        this.btnSelectCookingZoneOrder = (Button) rootView.findViewById(R.id.btnSelectCookingZoneOrder);
        this.btnSearchListOrder = (Button) rootView.findViewById(R.id.btnSearchListOrder);

        btnSelectCookingZoneOrder.setOnClickListener(btnClickListener);
        btnAddOrder.setOnClickListener(btnClickListener);
        btnSaveOrder.setOnClickListener(btnClickListener);
        etSearchListOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnShareOrder.setOnClickListener(btnClickListener);
        btnSearchListOrder.setOnClickListener(btnClickListener);

        vendor = getDefaultVendor(PreferencesManage.getInstance().getVendorDefault());
        cookingZone = getDefaultCookingZone(PreferencesManage.getInstance().getCookingZonrDefault());

        detailList = new ArrayList<>();
        listAdapter = new OrderListAdapter(this);
        listAdapter.setListDetail(detailList);
        listViewOrder.setAdapter(listAdapter);
        listViewOrder.setEmptyView(rootView.findViewById(R.id.emptyElementOrder));

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

    private void setDialogOrderInit() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_order_init);
        Button btnOkDialogOrder = (Button) dialog.findViewById(R.id.btnOkDialogOrder);
        Button btnCancelDialogOrder = (Button) dialog.findViewById(R.id.btnCancelDialogOrder);
        final Button btnSelectDateShippedDialogOrder = (Button) dialog.findViewById(R.id.btnSelectDateShippedDialogOrder);
        final Button btnSelectDateOrderDialogOrder = (Button) dialog.findViewById(R.id.btnSelectDateOrderDialogOrder);
        final Button btnSelectVendorDialogOrder = (Button) dialog.findViewById(R.id.btnSelectVendorDialogOrder);
        final Button btnSelectCookZoneDialogOrder = (Button) dialog.findViewById(R.id.btnSelectCookZoneDialogOrder);

        if (cookingZone != null) btnSelectCookZoneDialogOrder.setText(cookingZone.getThDesc());
        if (vendor != null) btnSelectVendorDialogOrder.setText(vendor.getVendorShortName());
        if (dateOrder != null) btnSelectDateOrderDialogOrder.setText("สั่ง : " + dateOrder);
        if (dateShipped != null) btnSelectDateShippedDialogOrder.setText("ส่ง : " + dateShipped);

        Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        btnSelectVendorDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<VendorDao> listVendor = DataManager.getInstance().getListVendor();
                final String[] arr = new String[listVendor.size()];
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
                        btnSelectVendorDialogOrder.setText(vendor.getVendorShortName());
                    }
                });
                builder.show();
            }
        });
        btnSelectCookZoneDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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
                        btnSelectCookZoneDialogOrder.setText(cookingZone.getThDesc());
                    }
                });
                builder.show();
            }
        });
        btnSelectDateOrderDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOrder = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1) + "/" + year;
                        btnSelectDateOrderDialogOrder.setText("สั่ง : " + dateOrder);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
        btnSelectDateShippedDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateShipped = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1) + "/" + year;
                        btnSelectDateShippedDialogOrder.setText("ส่ง : " + dateShipped);
                    }
                }, mYear, mMonth, mDay + 1).show();
            }
        });
        btnOkDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cookingZone != null && vendor != null && dateShipped != null && dateOrder != null) {
                    dialog.dismiss();
                    PreferencesManage.getInstance().setCookingZoneDefault(cookingZone.getRunId());
                    PreferencesManage.getInstance().setVendorDefault(vendor.getVendorId());
                    new GetDefaultListValue().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        });
        btnCancelDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void shareDataToLine() {
        if (cookingZone != null && vendor != null && dateOrder != null && dateShipped != null && detailList != null) {
            String Sh;
            Sh = "เรียน Chef/ผู้อนุมัติ" + "\n";
            Sh = Sh + "FYA: ขออนุมัติรายการสั่งซื้อวัตถุ" + "\n";
            Sh = Sh + "PO : " + poSelected + "\n";
            Sh = Sh + "ครัว " + cookingZone.getThDesc() + "\n";
            Sh = Sh + "Supplier " + vendor.getVendorShortName() + "\n";
            Sh = Sh + "วันที่สั่ง " + dateOrder + "\n";
            Sh = Sh + "วันที่ส่ง " + dateShipped + "\n";
            Sh = Sh + "-โดยรายการวัตถุดิบมีดังนี้" + "\n";
            for (int i = 0; i < detailList.size(); i++) {
                Sh = Sh + (i + 1) + ". " + detailList.get(i).getProductName() + " " + Utils.priceWithDecimal(detailList.get(i).getVolProduct()) + " " + detailList.get(i).getUnitName() + "\n";
            }
            Sh = Sh + "รวม " + detailList.size() + " รายการ\n";
            EmpDao empDao = DataManager.getInstance().getEmpDao();
            Sh = Sh + "ผู้ขอ " + empDao.getFirstNameTh() + " " + empDao.getLastNameTh() + "\n";
            Sh = Sh + "ติดต่อ " + empDao.getMobile() + "\n";
            Sh = Sh + "\n" + "คลิ๊ก link www.mibholding.com/tawa_mart/PO_Appr.aspx?dqry=" + Utils.yyMMddFormatDate(dateOrder) + " \n";
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
        for (PoDetailDao poDetailDao : detailList) {
            poDetailDao.setEmpCreateId(empDao.getEmpId());
            poDetailDao.setEmpCreate(empDao.getFirstNameTh() + " " + empDao.getLastNameTh());
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

    private void addToListDetail(String jsonDetail) {
        if (jsonDetail != null && !jsonDetail.equals("error")) {
            Type listType = new TypeToken<List<PoDetailDao>>() {
            }.getType();
            List<PoDetailDao> poDetailList = new Gson().fromJson(jsonDetail, listType);

            for (int i = 0; i < poDetailList.size(); i++) {
                int count = 0;
                for (int j = 0; j < detailList.size(); j++) {
                    if (poDetailList.get(i).getProductId().equals(detailList.get(j).getProductId()))
                        count++;
                }
                if (count == 0) {
                    poDetailList.get(i).setTransectionId("NEW");
                    detailList.add(poDetailList.get(i));
                }

            }

            //detailList.addAll(poDetailList);
            listAdapter.notifyDataSetChanged();
            tvSummaryListOrder.setText("รวม " + detailList.size());
        }
    }

    @Override
    public void dataUpdated() {
        tvSummaryListOrder.setText("รวม " + detailList.size());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            String result = data.getExtras().getString("ListPoDetailResult");
            addToListDetail(result);
        } else if (requestCode == 12 && resultCode == RESULT_OK) {
            String contents = data.getStringExtra("SCAN_RESULT");
            new GetPoDataByTranId(contents.trim()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private class GetDefaultListValue extends AsyncTask<String, Void, String> {

        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        String resultReqPoDt, resultReqPoHd;

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

            resultReqPoDt = new CallService().getReqPoDt(cookingZone.getRunId(), vendor.getVendorId(), Utils.yyMMddFormatDate(dateOrder), Utils.yyMMddFormatDate(dateShipped));
            resultReqPoHd = new CallService().getReqPoHd(cookingZone.getRunId(), vendor.getVendorId(), Utils.yyMMddFormatDate(dateOrder), Utils.yyMMddFormatDate(dateShipped));
            Log.d(TAG, "doInBackground: " + resultReqPoHd);
            Log.d(TAG, "doInBackground: " + resultReqPoDt);
            //if (resultReqPoDt.equals("error")) resultReqPoDt = "[]";
            if (resultReqPoHd.equals("error")) resultReqPoHd = "[]";

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog != null)
                dialog.dismiss();

            if (resultReqPoHd.equals("error")) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("ไม่สามารถเชื่อมต่อกับ Server ได้")
                        .setContentText("กรุณาลองอีกครั้ง")
                        .show();
            } else {
                Type listType = new TypeToken<List<PoDetailDao>>() {
                }.getType();
                detailList = new Gson().fromJson(resultReqPoDt, listType);

                listType = new TypeToken<List<PoHeaderDao>>() {
                }.getType();
                headerList = new Gson().fromJson(resultReqPoHd, listType);

                if (headerList.size() == 0) {
                    PoHeaderDao poHeaderDao = new PoHeaderDao();
                    poHeaderDao.setTransectionId("NEW");
                    poHeaderDao.setCookingzoneId(cookingZone.getRunId());
                    poHeaderDao.setCookingzoneName(cookingZone.getThDesc());
                    poHeaderDao.setVendorId(vendor.getVendorId());
                    poHeaderDao.setVendorName(vendor.getVendorName());
                    poHeaderDao.setVendorAddr(vendor.getAddress());
                    poHeaderDao.setDateCreate(Utils.yyMMddFormatDate(dateOrder));
                    poHeaderDao.setDateOrder(Utils.yyMMddFormatDate(dateShipped));
                    EmpDao empDao = DataManager.getInstance().getEmpDao();
                    poHeaderDao.setEmpCreateId(empDao.getEmpId());
                    poHeaderDao.setEmpCreate(empDao.getFirstNameTh() + " " + empDao.getLastNameTh());
                    headerList.add(poHeaderDao);
                    poSelected = "NEW";
                } else {
                    poSelected = headerList.get(0).getTransectionId();
                }

                listAdapter.setListDetail(detailList);
                listAdapter.notifyDataSetChanged();

                btnSelectCookingZoneOrder.setText(cookingZone.getThDesc());
                tvDateOrderAndShippedOrder.setText("สั่ง " + dateOrder + " , ส่ง " + dateShipped);
                tvSummaryListOrder.setText("รวม " + detailList.size());
            }
        }

    }

    private class SaveJsonPo extends AsyncTask<String, Void, String> {

        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        private String resultReqPoDt;
        private String resultReqPoHd;

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
            String resultService = new CallService().savePoHdDt(
                    new Gson().toJson(headerList),
                    new Gson().toJson(detailList));

            if (resultService.equalsIgnoreCase("True")) {
                resultReqPoDt = new CallService().getReqPoDt(cookingZone.getRunId(), vendor.getVendorId(), Utils.yyMMddFormatDate(dateOrder), Utils.yyMMddFormatDate(dateShipped));
                resultReqPoHd = new CallService().getReqPoHd(cookingZone.getRunId(), vendor.getVendorId(), Utils.yyMMddFormatDate(dateOrder), Utils.yyMMddFormatDate(dateShipped));
                Log.d(TAG, "doInBackground: " + resultReqPoHd);
                Log.d(TAG, "doInBackground: " + resultReqPoDt);
                if (resultReqPoDt.equals("error")) resultReqPoDt = "[]";
                if (resultReqPoHd.equals("error")) resultReqPoHd = "[]";
            }

            return resultService;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (dialog != null)
                dialog.dismiss();

            if (s.equals("True")) {

                Type listType = new TypeToken<List<PoDetailDao>>() {
                }.getType();
                detailList = new Gson().fromJson(resultReqPoDt, listType);

                listType = new TypeToken<List<PoHeaderDao>>() {
                }.getType();
                headerList = new Gson().fromJson(resultReqPoHd, listType);
                if (headerList.size() > 0) {
                    poSelected = headerList.get(0).getTransectionId();
                }

                listAdapter.setListDetail(detailList);
                listAdapter.notifyDataSetChanged();

                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("สำเร็จ")
                        .setContentText("ทำการบันทึกรายการเสร็จสิ้น")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
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

    private class GetPoDataByTranId extends AsyncTask<String, Void, String> {

        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        String resultDetail, tranId;

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
            return resultDetail;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog != null) {
                dialog.dismiss();
            }
            if (s.equals("error")) s = "[]";
            addToListDetail(s);

        }
    }

    private View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnSelectCookingZoneOrder) {
                setDialogOrderInit();
            } else if (view == btnAddOrder) {
                if (cookingZone != null && vendor != null && dateOrder != null && dateShipped != null && detailList != null) {
                    final String[] arr = new String[2];
                    arr[0] = "เพิ่มใหม่";
                    arr[1] = "เพิ่มจาก PO เดิม";
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setItems(arr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (which == 0) {
                                startActivityForResult(new Intent(getActivity(), ProductActivity.class), 10);
                                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (which == 1) {
                                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                                intent.setPackage("com.google.zxing.client.android");
                                intent.putExtra("SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR,EAN_13,EAN_8,UPC_A,QR_CODE");
                                if (isCallable(intent)) {
                                    startActivityForResult(intent, 12);
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
                            }
                        }
                    });
                    builder.show();
                } else {
                    setDialogOrderInit();
                }
            } else if (view == btnSaveOrder) {
                setDataBeforeSave();
                new SaveJsonPo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btnShareOrder) {
                shareDataToLine();
            } else if (view == btnSearchListOrder) {
                Utils.hideSoftKeyboard(view);
            }
        }
    };
}
