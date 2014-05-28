package com.dreamslab.tdprinting;

import com.dreamslab.tdprinting.mine.MineFragment;
import com.dreamslab.tdprinting.order.ShoppingCartFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final int TAB_INDEX_MAIN = 0;
    private static final int TAB_INDEX_SHOPPING_CART = 1;
    private static final int TAB_INDEX_MINE = 2;

    private static final int TAB_INDEX_COUNT = 3;

    private View mMainTabUnderline;
    private View mShoppingCartTabUnderline;
    private View mMineTabUnderline;
    private View mCurrentTabUnderline;

    private ViewPageChangeListener mViewPageChangeListener;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mMainTabUnderline = findViewById(R.id.main_tab_underline);
        mShoppingCartTabUnderline = findViewById(R.id.shopping_cart_tab_underline);
        mMineTabUnderline = findViewById(R.id.mine_tab_underline);

        mViewPageChangeListener = new ViewPageChangeListener();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));
        mViewPager.setOnPageChangeListener(mViewPageChangeListener);

        findViewById(R.id.main_tab).setOnClickListener(this);
        findViewById(R.id.shopping_cart_tab).setOnClickListener(this);
        findViewById(R.id.mine_tab).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPageChangeListener.onPageSelected(mViewPager.getCurrentItem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.myorder) {
            Intent intent = new Intent(TdPrintingConstants.ACTION_MYORDER);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.main_tab:
            mViewPager.setCurrentItem(TAB_INDEX_MAIN);
            break;
        case R.id.shopping_cart_tab:
            mViewPager.setCurrentItem(TAB_INDEX_SHOPPING_CART);
            break;
        case R.id.mine_tab:
            mViewPager.setCurrentItem(TAB_INDEX_MINE);
            break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
            case TAB_INDEX_MAIN:
                fragment = new MainFragment();
                break;
            case TAB_INDEX_SHOPPING_CART:
                fragment = new ShoppingCartFragment();
                break;
            case TAB_INDEX_MINE:
                fragment = new MineFragment();
                break;
            default:
                throw new IllegalStateException("No fragment at position " + position);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_INDEX_COUNT;
        }
    }

    class ViewPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            View tabUnderline;

            switch (position) {
            case TAB_INDEX_MAIN:
                tabUnderline = mMainTabUnderline;
                break;
            case TAB_INDEX_SHOPPING_CART:
                tabUnderline = mShoppingCartTabUnderline;
                break;
            case TAB_INDEX_MINE:
                tabUnderline = mMineTabUnderline;
                break;
            default:
                throw new IllegalStateException("No fragment at position " + position);
            }

            if (mCurrentTabUnderline != null) {
                mCurrentTabUnderline.setVisibility(View.INVISIBLE);
            }
            mCurrentTabUnderline = tabUnderline;
            mCurrentTabUnderline.setVisibility(View.VISIBLE);
        }

    }
}
