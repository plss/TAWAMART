package mibh.mis.tawamart.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.dao.ProductDao;
import mibh.mis.tawamart.manager.Contextor;
import mibh.mis.tawamart.utils.Utils;
import mibh.mis.tawamart.view.SecondListItem;

/**
 * Created by Ponlakit on 7/21/2016.
 */

public class SecondListAdapter extends BaseAdapter implements Filterable {

    private OnStateChangedListener mCallback;
    private List<ProductDao> productList;
    private List<ProductDao> originalProductList;
    private HashMap<String, Double> checkState;
    private SecondListAdapter.ItemFilter mFilter = new SecondListAdapter.ItemFilter();

    public SecondListAdapter(OnStateChangedListener mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public interface OnStateChangedListener {
        void stateChanged(HashMap<String, Double> checkState);
    }

    public void setProductList(List<ProductDao> productList) {
        this.productList = productList;
        this.originalProductList = productList;
    }

    public void setCheckState(HashMap<String, Double> checkState) {
        this.checkState = checkState;
    }

    @Override
    public int getCount() {
        if (productList == null) return 0;
        else return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {
        final SecondListItem item;
        if (view != null)
            item = (SecondListItem) view;
        else
            item = new SecondListItem(viewGroup.getContext());

        final ProductDao product = (ProductDao) getItem(i);
        item.setTextBtnMiddle(product.getProductName().trim() + " : " + Utils.priceWithDecimal(getValueByProductId(product.getProductId())) + " " + product.getInitName() + "\n"
                + product.getProductCode() + " : " + product.getVendorName());

        item.setSetBgSelectedBtnMiddle(inCheckState(product.getProductId()));

        View.OnClickListener onBtnMiddleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inCheckState(product.getProductId())) {
                    removeCheckStateByProductId(product.getProductId());
                } else {
                    final Dialog dialog = new Dialog(viewGroup.getContext());
                    dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dilog_one_input);
                    Button btnOkDialogInput = (Button) dialog.findViewById(R.id.btnOkDialogInput);
                    Button btnCancelDialogInput = (Button) dialog.findViewById(R.id.btnCancelDialogInput);
                    TextView tvUnitDialogInput = (TextView) dialog.findViewById(R.id.tvUnitDialogInput);
                    tvUnitDialogInput.setText(product.getInitName());
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
                            if (!etValueDialogInput.getText().toString().equals(""))
                                setValueCheckState(product.getProductId(), Double.parseDouble(etValueDialogInput.getText().toString().trim()));
                            dialog.dismiss();
                            mCallback.stateChanged(checkState);
                        }
                    });
                    dialog.show();
                }
                item.setSetBgSelectedBtnMiddle(inCheckState(product.getProductId()));
            }
        };

        item.setOnClickBtnMiddle(onBtnMiddleClickListener);

        View.OnClickListener onBtnLastClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inCheckState(product.getProductId())) {
                    final Dialog dialog = new Dialog(viewGroup.getContext());
                    dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dilog_one_input);
                    Button btnOkDialogInput = (Button) dialog.findViewById(R.id.btnOkDialogInput);
                    Button btnCancelDialogInput = (Button) dialog.findViewById(R.id.btnCancelDialogInput);
                    TextView tvUnitDialogInput = (TextView) dialog.findViewById(R.id.tvUnitDialogInput);
                    tvUnitDialogInput.setText(product.getInitName());
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
                        public void onClick(final View view) {
                            Utils.hideSoftKeyboard(viewGroup);
                            if (!etValueDialogInput.getText().toString().equals(""))
                                setValueCheckState(product.getProductId(), Double.parseDouble(etValueDialogInput.getText().toString().trim()));
                            dialog.dismiss();
                            mCallback.stateChanged(checkState);
                        }
                    });
                    dialog.show();
                }
            }
        };

        item.setOnClickBtnLast(onBtnLastClickListener);

        return item;
    }

    private void setValueCheckState(String productId, double val) {
        checkState.put(productId, val);
        notifyDataSetChanged();
    }

    private double getValueByProductId(String productId) {
        if (checkState.containsKey(productId))
            return checkState.get(productId);
        else return 0;
    }

    private boolean inCheckState(String productId) {
        return checkState.containsKey(productId);
    }

    private void removeCheckStateByProductId(String productId) {
        checkState.remove(productId);
        notifyDataSetChanged();
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ProductDao> list = originalProductList;

            if (filterString.equals("")) {
                results.values = list;
                results.count = list.size();
                return results;
            }

            int count = list.size();
            //final ArrayList<String> nlist = new ArrayList<String>(count);
            final List<ProductDao> nlist = new ArrayList<>(count);

            ProductDao filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getProductName().contains(filterString) || filterableString.getProductCode().toUpperCase().contains(filterString.toUpperCase())) {
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
            productList = (List<ProductDao>) results.values;
            notifyDataSetChanged();
        }

    }

}
