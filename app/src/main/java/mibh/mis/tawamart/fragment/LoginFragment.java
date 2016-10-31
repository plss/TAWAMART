package mibh.mis.tawamart.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hkm.ui.processbutton.iml.ActionProcessButton;

import java.lang.reflect.Type;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.activity.HomeActivity;
import mibh.mis.tawamart.dao.EmpDao;
import mibh.mis.tawamart.manager.DataManager;
import mibh.mis.tawamart.manager.PreferencesManage;
import mibh.mis.tawamart.service.CallService;
import mibh.mis.tawamart.utils.Utils;

public class LoginFragment extends Fragment {

    private TextInputEditText etPassword;
    private TextInputEditText etUsername;
    private ActionProcessButton btnSignIn;
    private TextView tvVersion;

    private static final String TAG = "LoginFragment";

    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.btnSignIn = (ActionProcessButton) rootView.findViewById(R.id.btnSignIn);
        this.etUsername = (TextInputEditText) rootView.findViewById(R.id.etUsername);
        this.etPassword = (TextInputEditText) rootView.findViewById(R.id.etPassword);
        this.tvVersion = (TextView) rootView.findViewById(R.id.tvVersion);

        etUsername.setText(PreferencesManage.getInstance().getUsername());
        etPassword.setText(PreferencesManage.getInstance().getPassword());
        tvVersion.setText("V : " + Utils.getVersionName());

        btnSignIn.setOnClickNormalState(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CheckLogin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }).build();

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

    private class CheckLogin extends AsyncTask<String, Void, String> {

        String username, password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            username = etUsername.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            etUsername.setEnabled(false);
            etPassword.setEnabled(false);
            btnSignIn.setEnabled(false);
            btnSignIn.setProgress(1);
        }

        @Override
        protected String doInBackground(String... strings) {
            DataManager.getInstance().setListVendor(new CallService().getListVendor());
            DataManager.getInstance().setListCookingZone(new CallService().getAllCookingZone());
            DataManager.getInstance().setListProduct(new CallService().getListProduct());
            DataManager.getInstance().setListFunction(new CallService().getListFunction());
            DataManager.getInstance().setListTypeWithdraw(new CallService().getListTypeWithdraw());
            DataManager.getInstance().setListCostCenter(new CallService().getListCostCenter());
            return new CallService().checkLogin(username, password);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("error")) {
                Type listType = new TypeToken<List<EmpDao>>() {
                }.getType();
                List<EmpDao> empDaoList = new Gson().fromJson(s, listType);
                if (empDaoList.size() > 0) {
                    DataManager.getInstance().setEmpDao(empDaoList.get(0));
                    PreferencesManage.getInstance().setUsername(username);
                    PreferencesManage.getInstance().setPassword(password);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    getActivity().finish();
                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("เข้าสู่ระบบผิดพลาด").setContentText("username หรือ password ผิดพลาด กรุณาลองใหม่อีกครั้ง").show();
                }
            } else {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("เข้าสู่ระบบผิดพลาด").setContentText("ไม่สามารถเชื่องต่อกับเเซิฟเวอร์ได้ กรุณาลองใหม่อีกครั้ง").show();
            }

            etUsername.setEnabled(true);
            etPassword.setEnabled(true);
            btnSignIn.setEnabled(true);
            btnSignIn.setProgress(0);

        }
    }

}
