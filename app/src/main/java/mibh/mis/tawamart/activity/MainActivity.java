package mibh.mis.tawamart.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.fragment.HomeFragment;
import mibh.mis.tawamart.fragment.OrderFragment;
import mibh.mis.tawamart.fragment.ReceiveFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, HomeFragment.newInstance()).commit();

    }
}
