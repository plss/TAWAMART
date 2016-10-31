package mibh.mis.tawamart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.fragment.ReceiveFragment;
import mibh.mis.tawamart.fragment.ReqReturnFragment;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class ReqReturnActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_return);
        initInstances();
        getSupportFragmentManager().beginTransaction().replace(R.id.reqReturnContainer, ReqReturnFragment.newInstance()).commit();

    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("เบิก/คืน");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
