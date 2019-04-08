package online.bcasino.pbonus.review.adminapp;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout=(TabLayout)findViewById(R.id.tablayout_id);
        appBarLayout=( AppBarLayout )findViewById(R.id.appbar_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        ViewpageAdapter adapter= new ViewpageAdapter(getSupportFragmentManager());
        //adding fragment
        adapter.Addfragment(new LoginDoc(), "Login As Doctor");
        adapter.Addfragment(new LoginUser(), "Login As Admin");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}
