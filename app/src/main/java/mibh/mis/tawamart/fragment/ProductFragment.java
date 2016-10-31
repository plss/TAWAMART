package mibh.mis.tawamart.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.adapter.SecondListAdapter;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.dao.ProductDao;
import mibh.mis.tawamart.dao.ReqReturnDao;
import mibh.mis.tawamart.manager.DataManager;
import mibh.mis.tawamart.utils.Utils;

public class ProductFragment extends Fragment implements SecondListAdapter.OnStateChangedListener {

    private ListView listView;
    private List<ProductDao> productList;
    private Button btnProductFreshFood;
    private Button btnProductMeat;
    private Button btnProductFruits;
    private Button btnProductOther;
    private Button btnSearchProduct;
    private EditText etProductSearch;
    private Button btnCancelProduct;
    private Button btnOkProduct;
    private HashMap<String, Double> checkState;
    private SecondListAdapter secondListAdapter;

    public ProductFragment() {
        super();
    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        this.listView = (ListView) rootView.findViewById(R.id.listViewSecond);
        this.btnOkProduct = (Button) rootView.findViewById(R.id.btnOkProduct);
        this.btnCancelProduct = (Button) rootView.findViewById(R.id.btnCancelProduct);
        this.etProductSearch = (EditText) rootView.findViewById(R.id.etProductSearch);
        this.btnProductOther = (Button) rootView.findViewById(R.id.btnProductOther);
        this.btnProductFruits = (Button) rootView.findViewById(R.id.btnProductFruits);
        this.btnProductMeat = (Button) rootView.findViewById(R.id.btnProductMeat);
        this.btnProductFreshFood = (Button) rootView.findViewById(R.id.btnProductFreshFood);
        this.btnSearchProduct = (Button) rootView.findViewById(R.id.btnSearchProduct);

        productList = DataManager.getInstance().getListProduct();
        checkState = new HashMap<>(productList.size());
        List<ProductDao> tempList = getListByGroupType(("GP001"));
        secondListAdapter = new SecondListAdapter(this);
        secondListAdapter.setProductList(tempList);
        secondListAdapter.setCheckState(checkState);
        listView.setAdapter(secondListAdapter);

        btnProductFreshFood.setOnClickListener(onClickListener);
        btnProductMeat.setOnClickListener(onClickListener);
        btnProductFruits.setOnClickListener(onClickListener);
        btnProductOther.setOnClickListener(onClickListener);
        btnOkProduct.setOnClickListener(onClickListener);
        btnCancelProduct.setOnClickListener(onClickListener);
        btnSearchProduct.setOnClickListener(onClickListener);

        etProductSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                secondListAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

    private List<ProductDao> getListByGroupType(String groupType) {
        List<ProductDao> tempList = new ArrayList<>(productList.size());
        for (ProductDao product : productList) {
            if (product.getProductGroupId().equals(groupType))
                tempList.add(product);
        }
        return tempList;
    }

    private void setNewListToListView(String type) {
        List<ProductDao> tempList = getListByGroupType(type);
        //secondListAdapter = new SecondListAdapter(this);
        secondListAdapter.setProductList(tempList);
        secondListAdapter.setCheckState(checkState);
        secondListAdapter.notifyDataSetChanged();
        //listAdapter = secondListAdapter;
        //listView.setAdapter(listAdapter);
    }

    private void setDisableBtnAfterClicked(Button btn) {
        btnProductMeat.setEnabled(true);
        btnProductFruits.setEnabled(true);
        btnProductOther.setEnabled(true);
        btnProductFreshFood.setEnabled(true);
        if (btn == btnProductFreshFood) {
            btnProductFreshFood.setEnabled(false);
        } else if (btn == btnProductMeat) {
            btnProductMeat.setEnabled(false);
        } else if (btn == btnProductFruits) {
            btnProductFruits.setEnabled(false);
        } else if (btn == btnProductOther) {
            btnProductOther.setEnabled(false);
        }
    }

    public List<PoDetailDao> getDetailListForResult() {
        List<PoDetailDao> poDetailList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : checkState.entrySet()) {
            for (int i = 0; i < productList.size(); i++) {
                if (entry.getKey().equalsIgnoreCase(productList.get(i).getProductId())) {
                    PoDetailDao poDetailDao = new PoDetailDao();
                    poDetailDao.setTransectionId("NEW");
                    poDetailDao.setVolProduct(entry.getValue());
                    poDetailDao.setProductName(productList.get(i).getProductName());
                    poDetailDao.setProductCode(productList.get(i).getProductCode());
                    poDetailDao.setProductId(entry.getKey());
                    poDetailDao.setUnitId(productList.get(i).getUnitId());
                    poDetailDao.setUnitName(productList.get(i).getInitName());
                    poDetailList.add(poDetailDao);
                }
            }
        }
        return poDetailList;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnProductFreshFood) {
                setDisableBtnAfterClicked(btnProductFreshFood);
                setNewListToListView("GP001");
            } else if (view == btnProductMeat) {
                setDisableBtnAfterClicked(btnProductMeat);
                setNewListToListView("GP002");
            } else if (view == btnProductFruits) {
                setDisableBtnAfterClicked(btnProductFruits);
                setNewListToListView("GP003");
            } else if (view == btnProductOther) {
                setDisableBtnAfterClicked(btnProductOther);
                setNewListToListView("GP004");
            } else if (view == btnCancelProduct) {
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            } else if (view == btnOkProduct) {
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putString("ListPoDetailResult", new Gson().toJson(getDetailListForResult()));
                intent.putExtras(extras);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            } else if (view == btnSearchProduct) {
                Utils.hideSoftKeyboard(view);
            }
        }
    };

    @Override
    public void stateChanged(HashMap<String, Double> checkState) {
        this.checkState = checkState;
    }


}
