package com.example.bf.kf.function;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bf.kf.R;
import com.example.bf.kf.common.BaseActivity;
import com.example.bf.kf.function.fragment.CircleFragment;
import com.example.bf.kf.function.fragment.InformationFragment;
import com.example.bf.kf.function.fragment.LookCosmeticsFragment;
import com.example.bf.kf.widget.HomePageTabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;

/**
 * 首页
 */
public class HomePageActivity extends BaseActivity {
    private HomePageTabLayout informationTl,lookCosmeticsTl,circleTl;
    private InformationFragment informationFragment;
    private LookCosmeticsFragment lookCosmeticsFragment;
    private CircleFragment circleFragment;
    private int mTabIndex = -1;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.a_home_page_layout);
        super.onCreate(savedInstanceState);
    }

    /**
     * 开始homePage界面
     */
    public static void  startHomePageActivity(Context context){
        Intent intent=new Intent(context,HomePageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        informationTl=findViewById(R.id.information_tl);
        lookCosmeticsTl=findViewById(R.id.look_cosmetics_tl);
        circleTl=findViewById(R.id.circle_tl);
        informationTl.setTextViewText("");
        lookCosmeticsTl.setTextViewText("");
        circleTl.setTextViewText("");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        changeToFragment(1);
    }
    public void changeToFragment(int mType) {
        if (mType == mTabIndex) {
            return;
        }
        mTabIndex = mType;
        //管理fragment
        fragmentManager = getSupportFragmentManager();
        //Fragment事务
        transaction = fragmentManager.beginTransaction();
        hide(transaction);
        switch (mType) {

            case 0:
                if (null != informationFragment) {
                    transaction.show(informationFragment);
                } else {
                    informationFragment = InformationFragment.newInstance();
                    transaction.add(R.id.home_fragment_layout, informationFragment);
                }
                break;
            case 1:
                if (null != lookCosmeticsFragment) {
                    transaction.show(lookCosmeticsFragment);
                } else {
                    lookCosmeticsFragment = LookCosmeticsFragment.newInstance();
                    transaction.add(R.id.home_fragment_layout, lookCosmeticsFragment);
                }

                break;
            case 2:
                if (null != circleFragment) {
                    transaction.show(circleFragment);
                } else {
                    circleFragment = CircleFragment.newInstance();
                    transaction.add(R.id.home_fragment_layout, circleFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
        setTableVew(mType);
    }

    /**
     * hide 所有的fragment
     *
     * @param ft
     */
    private void hide(FragmentTransaction ft) {
        if (null != informationFragment) {
            ft.hide(informationFragment);
        }
        if (null != lookCosmeticsFragment) {
            ft.hide(lookCosmeticsFragment);
        }
        if (null != circleFragment) {
            ft.hide(circleFragment);
        }
    }

    private void setTableVew(int index) {
        Resources resources = getResources();
        switch (index) {
            case 0:
//                informationTl.setViewColorAndBackground(R.mipmap.home_today_prs_icon, resources.getColor(R.color.green_21AC38));
//                lookCosmeticsTl.setViewColorAndBackground(R.mipmap.home_cutomer_nor_icon, resources.getColor(R.color.black_B3B3B3));
//                circleTl.setViewColorAndBackground(R.mipmap.home_myself_icom_nor, resources.getColor(R.color.black_B3B3B3));
                break;
            case 1:
//                informationTl.setViewColorAndBackground(R.mipmap.home_today_nor_icon, resources.getColor(R.color.black_B3B3B3));
//                lookCosmeticsTl.setViewColorAndBackground(R.mipmap.home_cutomer_prs_icon, resources.getColor(R.color.green_21AC38));
//                circleTl.setViewColorAndBackground(R.mipmap.home_myself_icom_nor, resources.getColor(R.color.black_B3B3B3));
                break;

            case 2:
//                informationTl.setViewColorAndBackground(R.mipmap.home_today_nor_icon, resources.getColor(R.color.black_B3B3B3));
//                lookCosmeticsTl.setViewColorAndBackground(R.mipmap.home_cutomer_nor_icon, resources.getColor(R.color.black_B3B3B3));
//                circleTl.setViewColorAndBackground(R.mipmap.home_myself_icom_prs, resources.getColor(R.color.green_21AC38));
                break;
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                hanldeKeyBack();
                return true;
            }
        }
        return super.onKeyUp(keyCode,event);
    }

    private void hanldeKeyBack(){
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.is_exit_again), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}