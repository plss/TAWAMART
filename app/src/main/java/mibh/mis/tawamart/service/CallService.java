package mibh.mis.tawamart.service;

import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import LibClass.SOAPWebserviceProperty;

/**
 * Created by kanyawat on 01/08/2016.
 */
public class CallService {

    private final String NAMESPACE = "http://tempuri.org/";
    private final String URL = "http://www.mibholding.com/TAWA_MART.asmx";
    private String SOAP_ACTION = "http://tempuri.org/";
    private String METHOD_NAME = "";
    private SOAPWebserviceProperty soap_property = null;

    public String checkLogin(String username, String password) {
        try {
            METHOD_NAME = "Login";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("Username", username);
            polydata.put("Password", password);
            request.addProperty("JsLogin", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error login", e.toString());
            return "error";
        }
    }

    public String getCookingZoneById(String id) {
        try {
            METHOD_NAME = "GetCookingZone_POHD";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("COCKINGZONE_ID", id);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getCookingZone", e.toString());
            return "error";
        }
    }

    public String getAllCookingZone() {
        try {
            METHOD_NAME = "Get_ListCOOKINGZONE";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getAllCookingZone", e.toString());
            return "error";
        }
    }

    public String getAllPoDt() {
        try {
            METHOD_NAME = "GetALL_PODT";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getAllPoDt", e.toString());
            return "error";
        }
    }

    public String getAllPoHd() {
        try {
            METHOD_NAME = "GetALL_POHD";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getAllPoHd", e.toString());
            return "error";
        }
    }

    public String getListFunction() {
        try {
            METHOD_NAME = "Get_ListFunction";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListFunction", e.toString());
            return "error";
        }
    }

    public String getDateTranReqReturn(String yyMMdd) {
        try {
            METHOD_NAME = "GetDateTran_ReqReturn";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("yyMMdd", yyMMdd);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getDateTran", e.toString());
            return "error";
        }
    }

    public String getTranIdPoDt(String id) {
        try {
            METHOD_NAME = "GetTRANSECTION_ID_PODT";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("TRANSECTION_ID", id);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getTranIdPoDt", e.toString());
            return "error";
        }
    }

    public String getTranIdPoHd(String id) {
        try {
            METHOD_NAME = "GetTRANSECTION_ID_POHD";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("TRANSECTION_ID", id);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getTranIdPoHd", e.toString());
            return "error";
        }
    }

    public String getListGroupProduct() {
        try {
            METHOD_NAME = "Get_ListGroupProduct";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListProduct", e.toString());
            return "error";
        }
    }

    public String getListUnit() {
        try {
            METHOD_NAME = "Get_ListUnit";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListUnit", e.toString());
            return "error";
        }
    }

    public String getListTypeWithdraw() {
        try {
            METHOD_NAME = "Get_ListReqRetrun";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListTypeWD", e.toString());
            return "error";
        }
    }

    public String getDetailByVendorAndCookingZone(String vendorId, String cookingZoneId) {
        try {
            METHOD_NAME = "GetDTByVendor_CookingZone";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Vendor", vendorId);
            request.addProperty("CookingZone", cookingZoneId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error DTByVendor&CookId", e.toString());
            return "error";
        }
    }

    public String getPoReceive(String yyMMdd, String vendorId, String cookingZoneId) {
        try {
            METHOD_NAME = "GetPR_ReciveV2";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("COCKINGZONE_ID", cookingZoneId);
            polydata.put("VENDOR_ID", vendorId);
            polydata.put("DATE_CRE", yyMMdd);
            request.addProperty("Json_PR", polydata.toString());
            Log.d("TEST", "getPoReceive: " + request.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error GetPR_Recive", e.toString());
            return "error";
        }
    }

    public String getHdReceive(String yyMMdd, String vendorId, String cookingZoneId) {
        try {
            METHOD_NAME = "GetReceive_POHD";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("COCKINGZONE_ID", cookingZoneId);
            polydata.put("VENDOR_ID", vendorId);
            polydata.put("DATE_CRE", yyMMdd);
            request.addProperty("JSon", polydata.toString());
            Log.d("TEST", "getHdReceive: " + request.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getHdReceive", e.toString());
            return "error";
        }
    }

    public String getListProduct() {
        try {
            METHOD_NAME = "Get_Product";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListProduct", e.toString());
            return "error";
        }
    }

    public String getListVendor() {
        try {
            METHOD_NAME = "Get_Vendor";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListVendor", e.toString());
            return "error";
        }
    }

    public String getListCostCenter() {
        try {
            METHOD_NAME = "Get_List";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("GROUP_TYPE", "COSTCENTER_TAWA");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error getListCostCenter", e.toString());
            return "error";
        }
    }

    public String getReqPoHd(String cookingZone, String vendor, String dateOrder, String dateShipped) {
        try {
            METHOD_NAME = "GetReq_POHD";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("COCKINGZONE_ID", cookingZone);
            polydata.put("VENDOR_ID", vendor);
            polydata.put("DATE_CRE", dateOrder);
            polydata.put("DATE_SHIPPED", dateShipped);
            request.addProperty("JSon", polydata.toString());
            //Log.d("TEST", "getReqPoHd: " + request.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getReqPoHd", e.toString());
            return "error";
        }
    }

    public String getReqPoDt(String cookingZone, String vendor, String dateOrder, String dateShipped) {
        try {
            METHOD_NAME = "GetReq_PODT";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("COCKINGZONE_ID", cookingZone);
            polydata.put("VENDOR_ID", vendor);
            polydata.put("DATE_CRE", dateOrder);
            polydata.put("DATE_SHIPPED", dateShipped);
            request.addProperty("JSonDT", polydata.toString());
            //Log.d("TEST", "getReqPoDt: " + request.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getReqPoHd", e.toString());
            return "error";
        }
    }

    public String savePoHdDt(String jsonHd, String jsonDt) {
        try {
            METHOD_NAME = "Save_PO_HD_DT";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("Json_HD", jsonHd);
            request.addProperty("Json_DT", jsonDt);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error savePoHdDt", e.toString());
            return "error";
        }
    }

    public String saveReqReturn(String jsonReqReturn) {
        try {
            METHOD_NAME = "Save_RequestReturn";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("Json_ReqReturn", jsonReqReturn);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            Log.d("Resulr saveReqReturn", resultData);
            return resultData;
        } catch (Exception e) {
            Log.d("Error saveReqReturn", e.toString());
            return "error";
        }
    }

    public String saveReceive(String jsonReceive) {
        try {
            METHOD_NAME = "Save_Receive";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("Json_Receive", jsonReceive);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error saveReceive", e.toString());
            return "error";
        }
    }

    public String saveStateImage(String TRANS_ID, String LAT_LNG, String LOCATION_NAME, String TYPE_IMG, String EMP_ID, String EMP_NAME, String FILE_NAME, String COMMENT_PHOTO) {
        try {
            METHOD_NAME = "Save_StatePO";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("TRANS_ID", TRANS_ID);
            polydata.put("LAT_LNG", LAT_LNG);
            polydata.put("LOCATION_NAME", LOCATION_NAME);
            polydata.put("TYPE_IMG", TYPE_IMG);
            polydata.put("EMP_ID", EMP_ID);
            polydata.put("EMP_NAME", EMP_NAME);
            polydata.put("FILE_NAME", FILE_NAME);
            polydata.put("COMMENT_PHOTO", COMMENT_PHOTO);
            request.addProperty("Json_StatePO", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error Save_StatePO", e.toString());
            return "error";
        }
    }

    public String savePic(String json_photo, String json_Img_ct) {
        try {
            String URL_SAVEPIC = "http://www.mibholding.com/dabt.asmx";
            METHOD_NAME = "SavePhoto_json";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL_SAVEPIC;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("json_photo", json_photo);
            request.addProperty("json_Img_ct", json_Img_ct);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_SAVEPIC);
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            Log.d("SavePhoto", resultData);
            return resultData;
        } catch (Exception e) {
            Log.d("Error SavePhoto", e.toString());
            return "error";
        }
    }

    public String getActiveVersion() {
        try {
            String URL = "http://www.mibholding.com/InterfaceTmsView.svc";
            METHOD_NAME = "GetActiveVersion";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("AppId", "M008");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call("http://tempuri.org/IInterfaceTmsView/" + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error version", e.toString());
        }
        return "error";
    }
}
