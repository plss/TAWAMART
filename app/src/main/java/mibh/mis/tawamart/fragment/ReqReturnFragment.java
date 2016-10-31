package mibh.mis.tawamart.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import mibh.mis.tawamart.adapter.ReqReturnListAdapter;
import mibh.mis.tawamart.dao.EmpDao;
import mibh.mis.tawamart.dao.ListDao;
import mibh.mis.tawamart.dao.ReqReturnDao;
import mibh.mis.tawamart.manager.DataManager;
import mibh.mis.tawamart.manager.PreferencesManage;
import mibh.mis.tawamart.service.CallService;
import mibh.mis.tawamart.utils.Utils;

import static android.app.Activity.RESULT_OK;

public class ReqReturnFragment extends Fragment implements ReqReturnListAdapter.OnDataUpdatedListener {

    private Button btnSelectCookingZoneOrder;
    private TextView tvDateOrderAndShippedOrder;
    private EditText etSearchListOrder;
    private ListView listViewOrder;
    private RelativeLayout emptyElementOrder;
    private Button btnAddOrder;
    private Button btnSaveOrder;
    private Button btnSearchList;
    private Button btnShareContent;
    private TextView tvSummaryListOrder;
    private ListDao cookingZone, refFunction;
    private String refCode, dateSelected;
    private ListDao type, costCenter;
    private List<ReqReturnDao> reqReturnList;
    private ReqReturnListAdapter listAdapter;

    public ReqReturnFragment() {
        super();
    }

    public static ReqReturnFragment newInstance() {
        ReqReturnFragment fragment = new ReqReturnFragment();
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
        this.emptyElementOrder = (RelativeLayout) rootView.findViewById(R.id.emptyElementOrder);
        this.listViewOrder = (ListView) rootView.findViewById(R.id.listViewOrder);
        this.etSearchListOrder = (EditText) rootView.findViewById(R.id.etSearchListOrder);
        this.tvDateOrderAndShippedOrder = (TextView) rootView.findViewById(R.id.tvDateOrderAndShippedOrder);
        this.btnSelectCookingZoneOrder = (Button) rootView.findViewById(R.id.btnSelectCookingZoneOrder);
        this.btnSearchList = (Button) rootView.findViewById(R.id.btnSearchListOrder);
        this.btnShareContent = (Button) rootView.findViewById(R.id.btnShareOrder);

        tvDateOrderAndShippedOrder.setText("วันที่เบิก/คืน");
        btnSelectCookingZoneOrder.setOnClickListener(onClickListener);
        btnAddOrder.setOnClickListener(onClickListener);
        btnSaveOrder.setOnClickListener(onClickListener);
        btnSearchList.setOnClickListener(onClickListener);
        btnShareContent.setOnClickListener(onClickListener);

        cookingZone = getDefaultCookingZone(PreferencesManage.getInstance().getCookingZonrDefault());

        reqReturnList = new ArrayList<>();
        listAdapter = new ReqReturnListAdapter(this);
        listAdapter.setListDetail(reqReturnList);
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

    private void setDialogInit() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_req_return_init);
        Button btnOkDialogOrder = (Button) dialog.findViewById(R.id.btnOkDialogOrder);
        Button btnCancelDialogOrder = (Button) dialog.findViewById(R.id.btnCancelDialogOrder);
        final Button btnSelectFunctionDialogReqReturn = (Button) dialog.findViewById(R.id.btnSelectFunctionDialogReqReturn);
        final EditText etFunctionDialogReqReturn = (EditText) dialog.findViewById(R.id.etFunctionDialogReqReturn);
        final Button btnSelectDateDialogReqReturn = (Button) dialog.findViewById(R.id.btnSelectDateDialogReqReturn);
        final Button btnSelectCookZoneDialogReqReturn = (Button) dialog.findViewById(R.id.btnSelectCookZoneDialogReqReturn);
        final Button btnSelectTypeDialogReqReturn = (Button) dialog.findViewById(R.id.btnSelectTypeDialogReqReturn);
        final Button btnSelectCostCenterDialogReqReturn = (Button) dialog.findViewById(R.id.btnSelectCostCenterDialogReqReturn);

        if (cookingZone != null)
            btnSelectCookZoneDialogReqReturn.setText(cookingZone.getThDesc());
        if (dateSelected != null)
            btnSelectDateDialogReqReturn.setText("วันที่ : " + dateSelected);
        if (refCode != null) {
            etFunctionDialogReqReturn.setText(refCode);
        }

        Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        btnSelectFunctionDialogReqReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<ListDao> listFunction = DataManager.getInstance().getListFunction();
                String[] arr = new String[listFunction.size()];
                int count = 0;
                for (ListDao func : listFunction) {
                    arr[count++] = func.getThDesc();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        refFunction = listFunction.get(which);
                        etFunctionDialogReqReturn.setHint(listFunction.get(which).getRemark());
                        btnSelectFunctionDialogReqReturn.setText(listFunction.get(which).getThDesc());
                    }
                });
                builder.show();
            }
        });

        btnSelectCookZoneDialogReqReturn.setOnClickListener(new View.OnClickListener() {
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
                        btnSelectCookZoneDialogReqReturn.setText(cookingZone.getThDesc());
                    }
                });
                builder.show();
            }
        });
        btnSelectDateDialogReqReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateSelected = String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1) + "/" + year;
                        btnSelectDateDialogReqReturn.setText("วันที่ : " + dateSelected);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
        btnSelectTypeDialogReqReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<ListDao> listType = DataManager.getInstance().getTypeWithdraw();
                final String[] arr = new String[listType.size()];
                int count = 0;
                for (ListDao listDao : listType)
                    arr[count++] = listDao.getThDesc();
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        type = listType.get(which);
                        btnSelectTypeDialogReqReturn.setText(arr[which]);
                    }
                });
                builder.show();
            }
        });
        btnSelectCostCenterDialogReqReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<ListDao> listCostCenter = DataManager.getInstance().getListCostCenter();
                final String[] arr = new String[listCostCenter.size()];
                int count = 0;
                for (ListDao listDao : listCostCenter)
                    arr[count++] = listDao.getThDesc();
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        costCenter = listCostCenter.get(which);
                        btnSelectCostCenterDialogReqReturn.setText(arr[which]);
                    }
                });
                builder.show();
            }
        });
        btnOkDialogOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type != null && dateSelected != null && cookingZone != null && costCenter != null && !etFunctionDialogReqReturn.getText().toString().trim().equals("")) {
                    dialog.dismiss();
                    PreferencesManage.getInstance().setCookingZoneDefault(cookingZone.getRunId());
                    refCode = etFunctionDialogReqReturn.getText().toString().trim();
                    btnSelectCookingZoneOrder.setText(cookingZone.getThDesc());
                    tvDateOrderAndShippedOrder.setText(type.getThDesc() + " วันที่ : " + dateSelected);
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

    private String getTypeEn(String typeTh) {
        switch (typeTh) {
            case "เบิก":
                return "REQUEST";
            case "คืน":
                return "RETURN";
            default:
                return "";
        }
    }

    private void setListBeforeSave() {
        for (ReqReturnDao reqReturnDao : reqReturnList) {
            reqReturnDao.setRefId(refFunction.runId);
            reqReturnDao.setRefName(refFunction.getThDesc());
            reqReturnDao.setCostCenter(costCenter.getRunId());
            reqReturnDao.setRefCode(refCode);
            reqReturnDao.setPartZone(cookingZone.getRunId());
            reqReturnDao.setPartName(cookingZone.getThDesc());
            reqReturnDao.setTypeInOut(type.getRunId());
            reqReturnDao.setRemarkReceive(type.getThDesc());
            EmpDao empDao = DataManager.getInstance().getEmpDao();
            reqReturnDao.setEmpId(empDao.getEmpId());
            reqReturnDao.setEmpName(empDao.getFirstNameTh() + " " + empDao.getLastNameTh());
        }
    }

    private void shareDataToLine() {
        if (cookingZone != null && type != null && refCode != null && dateSelected != null && refFunction != null) {
            String Sh;
            Sh = "เรียน Chef/และผู้เกี่ยวข้องทุกท่าน" + "\n";
            Sh = Sh + "FYI: " + type.getThDesc() + " ( " + refFunction.getThDesc() + " : " + refCode + " )\n";
            Sh = Sh + "Cost Center: " + costCenter.getThDesc() + "\n";
            Sh = Sh + "ครัว " + cookingZone.getThDesc() + "\n";
            Sh = Sh + "วันที่ " + dateSelected + "\n";
            Sh = Sh + "-โดยรายการวัตถุดิบมีดังนี้" + "\n";
            for (int i = 0; i < reqReturnList.size(); i++) {
                Sh = Sh + (i + 1) + ". " + reqReturnList.get(i).getProductName() + " " + Utils.priceWithDecimal(reqReturnList.get(i).getVolProduct()) + " " + reqReturnList.get(i).getUnitName() + "\n";
            }
            Sh = Sh + "รวม " + reqReturnList.size() + " รายการ\n";
            EmpDao empDao = DataManager.getInstance().getEmpDao();
            Sh = Sh + "ผู้" + type.getThDesc() + " " + empDao.getFirstNameTh() + " " + empDao.getLastNameTh() + "\n";
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

    @Override
    public void dataUpdated() {
        tvSummaryListOrder.setText("รวม " + reqReturnList.size());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == RESULT_OK) {
            String result = data.getExtras().getString("ListPoDetailResult");
            if (result != null) {
                Type listType = new TypeToken<List<ReqReturnDao>>() {
                }.getType();
                List<ReqReturnDao> poDetailList = new Gson().fromJson(result, listType);
                reqReturnList.addAll(poDetailList);
                listAdapter.notifyDataSetChanged();
                tvSummaryListOrder.setText("รวม " + reqReturnList.size());
            }
        }
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
            if (view == btnSelectCookingZoneOrder) {
                setDialogInit();
            } else if (view == btnAddOrder) {
                startActivityForResult(new Intent(getActivity(), ProductActivity.class), 11);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else if (view == btnSaveOrder) {
                setListBeforeSave();
                new SaveReqReturn(new Gson().toJson(reqReturnList)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btnSearchList) {
                Utils.hideSoftKeyboard(view);
            } else if (view == btnShareContent) {
                shareDataToLine();
            }
        }
    };

    private class SaveReqReturn extends AsyncTask<String, Void, String> {

        private SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        private String jsonReqReturn;

        public SaveReqReturn(String jsonReqReturn) {
            this.jsonReqReturn = jsonReqReturn;
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
            return new CallService().saveReqReturn(jsonReqReturn);
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
