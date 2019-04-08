package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Doc_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_main);
        BottomNavigationView bottomnav =findViewById(R.id.btm_nav_doc);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_profile:
                            KillThisActivity();
                            openprofile();
                            break;
                        case R.id.nav_doc_appointments:
                            KillThisActivity();
                            openDoc_aptmnts();
                            break;
                    }
                    return true;
                }

            };
    public void openprofile()
    {
        Intent intentdocter = new Intent(this, Profile_.class);
        startActivity(intentdocter);
    }
    public void openDoc_aptmnts()
    {
        Intent intentdocter = new Intent(this, Doc_apntments.class);
        startActivity(intentdocter);
    }
    public void KillThisActivity()
    {
        this.finish();
    }
}
