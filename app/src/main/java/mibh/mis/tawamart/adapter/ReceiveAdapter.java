package mibh.mis.tawamart.adapter;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.utils.Utils;
import mibh.mis.tawamart.view.FirstListItem;
import mibh.mis.tawamart.view.ReceiveListItem;

/**
 * Created by Ponlakit on 8/17/2016.
 */

public class ReceiveAdapter extends BaseAdapter {

    private List<PoDetailDao> detailList;
    private HashMap<String, String> checkState;

    public void setDetailList(List<PoDetailDao> detailList) {
        this.detailList = detailList;
    }

    public void setCheckState(HashMap<String, String> checkState) {
        this.checkState = checkState;
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

        final ReceiveListItem item;
        if (view != null)
            item = (ReceiveListItem) view;
        else
            item = new ReceiveListItem(viewGroup.getContext());

        final PoDetailDao poDetailDao = (PoDetailDao) getItem(i);

        item.setListNo(i + 1 + "");
        if (poDetailDao.getVolReceive() != null) {
            item.setTextBtnTitle(poDetailDao.getProductName().trim() + " : " + Utils.priceWithDecimal(poDetailDao.getVolReceive()) + " " + poDetailDao.getUnitName());
        } else {
            item.setTextBtnTitle(poDetailDao.getProductName().trim() + " : " + Utils.priceWithDecimal(poDetailDao.getVolProduct()) + " " + poDetailDao.getUnitName());
        }

        final String state = checkState.get(poDetailDao.getTransectionId() + poDetailDao.getItemId());
        item.setStateBtn(state);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == item.getBtnComplete()) {
                    checkState.put(poDetailDao.getTransectionId() + poDetailDao.getItemId(), "COMPLETE");
                    detailList.get(i).setVolReceive(poDetailDao.getVolProduct());
                } else if (view == item.getBtnIncomplete()) {
                    final Dialog dialog = new Dialog(viewGroup.getContext());
                    dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dilog_one_input);
                    Button btnOkDialogInput = (Button) dialog.findViewById(R.id.btnOkDialogInput);
                    Button btnCancelDialogInput = (Button) dialog.findViewById(R.id.btnCancelDialogInput);
                    TextView tvUnitDialogInput = (TextView) dialog.findViewById(R.id.tvUnitDialogInput);
                    tvUnitDialogInput.setText(poDetailDao.getUnitName());
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
                            checkState.put(poDetailDao.getTransectionId() + poDetailDao.getItemId(), "INCOMPLETE");
                            Utils.hideSoftKeyboard(viewGroup);
                            if (!etValueDialogInput.getText().toString().equals(""))
                                detailList.get(i).setVolReceive(Double.parseDouble(etValueDialogInput.getText().toString().trim()));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (view == item.getBtnDeny()) {
                    checkState.put(poDetailDao.getTransectionId() + poDetailDao.getItemId(), "DENY");
                    detailList.get(i).setVolReceive(0.0);
                }
                notifyDataSetChanged();
            }
        };

        item.setOnClickBtn(onClickListener);

        return item;
    }
}
