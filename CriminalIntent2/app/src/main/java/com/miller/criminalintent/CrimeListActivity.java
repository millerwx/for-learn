package com.miller.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by miller on 16/6/23.
 */
public class CrimeListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
