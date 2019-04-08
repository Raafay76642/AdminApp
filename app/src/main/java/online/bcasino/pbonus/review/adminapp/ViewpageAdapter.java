package online.bcasino.pbonus.review.adminapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentlistTitle = new ArrayList<>();
    public ViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position) ;
    }

    @Override
    public int getCount() {
        return fragmentlistTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlistTitle.get(position);
    }
    public void  Addfragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        fragmentlistTitle.add(Title);
    }
}

