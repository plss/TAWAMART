package mibh.mis.tawamart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.fragment.LoginFragment;

/**
 * Created by Ponlakit on 7/28/2016.
 */

public class LoginActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.loginContainer, LoginFragment.newInstance()).commit();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        View parentLayout = findViewById(R.id.loginContainer);
        Snackbar.make(parentLayout, "กด Back อีกครั้งเพื่อออก", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);

    }
}
