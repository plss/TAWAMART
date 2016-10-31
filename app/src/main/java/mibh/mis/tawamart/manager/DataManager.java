package mibh.mis.tawamart.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import mibh.mis.tawamart.dao.EmpDao;
import mibh.mis.tawamart.dao.ListDao;
import mibh.mis.tawamart.dao.PoDetailDao;
import mibh.mis.tawamart.dao.PoHeaderDao;
import mibh.mis.tawamart.dao.ProductDao;
import mibh.mis.tawamart.dao.VendorDao;

/**
 * Created by Ponlakit on 11/7/2559.
 */

public class DataManager {

    private static DataManager instance;

    private List<ProductDao> listProduct;
    private List<VendorDao> listVendor;
    private List<PoDetailDao> listPoDetail;
    private List<PoHeaderDao> listPoHeader;
    private List<ListDao> listGroupProduct;
    private List<ListDao> listUnit;
    private List<ListDao> listCookingZone;
    private List<ListDao> listFunctionType;
    private List<ListDao> listTypeWithdraw;
    private List<ListDao> listCostCenter;

    private EmpDao empDao;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private Context mContext;

    private DataManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }

    public EmpDao getEmpDao() {
        return empDao;
    }

    public List<ProductDao> getListProduct() {
        return listProduct;
    }

    public void setListProduct(String jsonProduct) {
        if (jsonProduct.equals("[]") || jsonProduct.equals("error"))
            jsonProduct = "[]";
        Type listType = new TypeToken<List<ProductDao>>() {
        }.getType();
        this.listProduct = new Gson().fromJson(jsonProduct, listType);
    }

    public void setListFunction(String jsonFunction) {
        if (jsonFunction.equals("[]") || jsonFunction.equals("error"))
            jsonFunction = "[]";
        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listFunctionType = new Gson().fromJson(jsonFunction, listType);
    }

    public List<ListDao> getListFunction() {
        return this.listFunctionType;
    }

    public List<VendorDao> getListVendor() {
        return listVendor;
    }

    public void setListVendor(String jsonVendor) {
        if (jsonVendor.equals("[]") || jsonVendor.equals("error"))
            jsonVendor = "[]";
        Type listType = new TypeToken<List<VendorDao>>() {
        }.getType();
        this.listVendor = new Gson().fromJson(jsonVendor, listType);
    }

    public List<PoDetailDao> getListPoDatil() {
        return listPoDetail;
    }

    public void setListPoDatil(String jsonDetail) {
        if (jsonDetail.equals("[]") || jsonDetail.equals("error"))
            jsonDetail = "[]";
        Type listType = new TypeToken<List<PoDetailDao>>() {
        }.getType();
        this.listPoDetail = new Gson().fromJson(jsonDetail, listType);
    }

    public List<PoHeaderDao> getListPoHeader() {
        return listPoHeader;
    }

    public void setListPoHeader(String jsonHeader) {
        if (jsonHeader.equals("[]") || jsonHeader.equals("error"))
            jsonHeader = "[]";
        Type listType = new TypeToken<List<PoHeaderDao>>() {
        }.getType();
        this.listPoHeader = new Gson().fromJson(jsonHeader, listType);
    }

    public List<ListDao> getListGroupProduct() {
        return listGroupProduct;
    }

    public void setListGroupProduct(String jsonGroupProduct) {
        if (jsonGroupProduct.equals("[]") || jsonGroupProduct.equals("error"))
            jsonGroupProduct = "[]";
        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listGroupProduct = new Gson().fromJson(jsonGroupProduct, listType);
    }

    public List<ListDao> getListUint() {
        return listUnit;
    }

    public void setListUint(String jsonUint) {
        if (jsonUint.equals("[]") || jsonUint.equals("error"))
            jsonUint = "[]";

        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listUnit = new Gson().fromJson(jsonUint, listType);
    }

    public List<ListDao> getListCookingZone() {
        return listCookingZone;
    }

    public void setListCookingZone(String jsonCookingZone) {
        if (jsonCookingZone.equals("[]") || jsonCookingZone.equals("error"))
            jsonCookingZone = "[]";

        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listCookingZone = new Gson().fromJson(jsonCookingZone, listType);
    }

    public void setListTypeWithdraw(String jsonType) {
        if (jsonType.equals("[]") || jsonType.equals("error"))
            jsonType = "[]";

        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listTypeWithdraw = new Gson().fromJson(jsonType, listType);
    }

    public List<ListDao> getTypeWithdraw() {
        return listTypeWithdraw;
    }

    public void setListCostCenter(String jsonCostCenter) {
        if (jsonCostCenter.equals("[]") || jsonCostCenter.equals("error"))
            jsonCostCenter = "[]";

        Type listType = new TypeToken<List<ListDao>>() {
        }.getType();
        this.listCostCenter = new Gson().fromJson(jsonCostCenter, listType);
    }

    public List<ListDao> getListCostCenter() {
        return listCostCenter;
    }

}
