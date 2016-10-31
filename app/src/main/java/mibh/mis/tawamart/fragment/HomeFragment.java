package mibh.mis.tawamart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.activity.GalleryActivity;
import mibh.mis.tawamart.activity.LoginActivity;
import mibh.mis.tawamart.activity.OrderActivity;
import mibh.mis.tawamart.activity.ReceiveActivity;
import mibh.mis.tawamart.activity.ReqReturnActivity;
import mibh.mis.tawamart.dao.EmpDao;
import mibh.mis.tawamart.manager.DataManager;
import mibh.mis.tawamart.manager.PreferencesManage;

public class HomeFragment extends Fragment {

    private LinearLayout btnMenuOrder;
    private LinearLayout btnMenuReceive;
    private LinearLayout btnMenuWithdraw;
    private LinearLayout btnMenuLogout;
    private TextView tvLoginName;

    public HomeFragment() {
        super();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        this.btnMenuLogout = (LinearLayout) rootView.findViewById(R.id.btnMEnuLogout);
        this.btnMenuWithdraw = (LinearLayout) rootView.findViewById(R.id.btnMenuWithdraw);
        this.btnMenuReceive = (LinearLayout) rootView.findViewById(R.id.btnMenuReceive);
        this.btnMenuOrder = (LinearLayout) rootView.findViewById(R.id.btnMenuOrder);
        this.tvLoginName = (TextView) rootView.findViewById(R.id.tvLoginName);

        EmpDao empDao = DataManager.getInstance().getEmpDao();
        if (empDao != null) {
            tvLoginName.setText(empDao.getFirstNameTh() + " " + empDao.getLastNameTh());
        }

        btnMenuLogout.setOnClickListener(onClickListener);
        btnMenuWithdraw.setOnClickListener(onClickListener);
        btnMenuReceive.setOnClickListener(onClickListener);
        btnMenuOrder.setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            if (view == btnMenuLogout) {
                intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                startActivity(intent);
                getActivity().finish();
            } else if (view == btnMenuOrder) {
                intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else if (view == btnMenuReceive) {
                intent = new Intent(getActivity(), ReceiveActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else if (view == btnMenuWithdraw) {
                intent = new Intent(getActivity(), ReqReturnActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        }
    };

}
