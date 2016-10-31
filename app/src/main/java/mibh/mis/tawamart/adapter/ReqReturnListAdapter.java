package mibh.mis.tawamart.adapter;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.dao.ReqReturnDao;
import mibh.mis.tawamart.utils.Utils;
import mibh.mis.tawamart.view.FirstListItem;

/**
 * Created by Ponlakit on 7/21/2016.
 */

public class ReqReturnListAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = "OrderListAdapter";
    private List<ReqReturnDao> detailList;
    private List<ReqReturnDao> originalDetailList;
    private ItemFilter mFilter = new ItemFilter();
    private OnDataUpdatedListener mCallback;

    public ReqReturnListAdapter(OnDataUpdatedListener mCallback) {
        this.mCallback = mCallback;
    }

    public interface OnDataUpdatedListener {
        void dataUpdated();
    }

    public void setListDetail(List<ReqReturnDao> detailList) {
        this.detailList = detailList;
        this.originalDetailList = detailList;
    }

    @Override
    public int getCount() {
        if (detailList == null) return 0;
        else return detailList.size();
    }

    @Override
    public Object getItem(int i) {
        return detailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        FirstListItem item;
        if (view != null)
            item = (FirstListItem) view;
        else
            item = new FirstListItem(viewGroup.getContext());

        item.setListNo(i + 1);
        item.setTextBtnMiddle(detailList.get(i).getProductName().trim() + " : " + Utils.priceWithDecimal(detailList.get(i).getVolProduct()) + " " + detailList.get(i).getUnitName());

        View.OnClickListener onMiddleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(viewGroup.getContext());
                dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dilog_one_input);
                Button btnOkDialogInput = (Button) dialog.findViewById(R.id.btnOkDialogInput);
                Button btnCancelDialogInput = (Button) dialog.findViewById(R.id.btnCancelDialogInput);
                TextView tvUnitDialogInput = (TextView) dialog.findViewById(R.id.tvUnitDialogInput);
                tvUnitDialogInput.setText(detailList.get(i).getUnitName());
                final EditText etValueDialogInput = (EditText) dialog.findViewById(R.id.etValueDialogInput);
                btnCancelDialogInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.hideSoftKeyboard(viewGroup);
                        dialog.dismiss();
                    }
                });
                btnOkDialogInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.hideSoftKeyboard(viewGroup);
                        if (!etValueDialogInput.getText().toString().equals("")) {
                            detailList.get(i).setVolProduct(Double.parseDouble(etValueDialogInput.getText().toString().trim()));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        };

        item.setOnClickMiddle(onMiddleClickListener);

        View.OnClickListener onLastClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(viewGroup.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("คุณต้องการลบรายการ ?")
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
                                detailList.remove(i);
                                notifyDataSetChanged();
                                mCallback.dataUpdated();
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
        };

        item.setOnClickLastButton(onLastClickListener);
        return item;

    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ReqReturnDao> list = originalDetailList;

            if (filterString.equals("")) {
                results.values = list;
                results.count = list.size();
                return results;
            }

            int count = list.size();
            //final ArrayList<String> nlist = new ArrayList<String>(count);
            final List<ReqReturnDao> nlist = new ArrayList<>(count);

            ReqReturnDao filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getProductName().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            detailList = (List<ReqReturnDao>) results.values;
            notifyDataSetChanged();
        }

    }

}
