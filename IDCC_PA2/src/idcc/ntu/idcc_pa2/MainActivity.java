package idcc.ntu.idcc_pa2;

import idcc.ntu.idcc_pa2.EditFragment.OnEditListener;
import idcc.ntu.idcc_pa2.ListFragment.OnChooseItemListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements OnEditListener,
    OnPageChangeListener, OnChooseItemListener {

    private ViewPager viewPager;
    private HomePagerAdapter mAdapter;
    private DBHelper mDBHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(this);
        mDBHelper = new DBHelper(this);
        
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private static String makeFragmentName(int id, int index) {
        return "android:switcher:" + id + ":" + index;
    }
    
    public void addItem(String string) {
        
    }
    
    class HomePagerAdapter extends FragmentPagerAdapter {

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
            case 0:
                return ListFragment.newInstance(mDBHelper.getData(mDBHelper.getReadableDatabase()));
            case 1:
                return EditFragment.newInstance();
            default:
                return null;
            }
        }
    }

    @Override
    public void onSave(String title, String content) {
        mDBHelper.saveData(mDBHelper.getWritableDatabase(), title, content);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
        case 0:
            setTitles();
            break;

        default:
            break;
        }
    }
    
    private String getTitleFromDB(int position) {
        return mDBHelper.getTitle(position);
    }
    
    private String getContentFromDB(int position) {
        return mDBHelper.getContent(position);
    }
    
    private void setTitles() {
        String[] strs = mDBHelper.getData(mDBHelper.getReadableDatabase());
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), 0));
        ((ListFragment) fragment).setTitles(strs);
    }

    @Override
    public void onChooseItem(int position) {
        DialogFragment dialog = 
                ContentDialogFragment.newInstance(getTitleFromDB(position), getContentFromDB(position));
        dialog.show(getSupportFragmentManager(), "TAG_CONTENT_DIALOG");
    }
}
