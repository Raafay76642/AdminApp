package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vidyo.VidyoClient.Connector.Connector;
import com.vidyo.VidyoClient.Connector.ConnectorPkg;

public class Calling extends AppCompatActivity implements Connector.IConnect {
    private Connector vc;
    private FrameLayout videoFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout)findViewById(R.id.videoFrame);
    }
    public void Start(View v) {
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 2, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }
    public void Connect(View v) {
        String token = "cHJvdmlzaW9uAHVzZXIxQGZjZmQwOC52aWR5by5pbwA2MzcyMTI4MjExMwAAN2U4MGJhMzdiOWY4OTk5ZTBiMTkyNzViOGU4NWM5YWQyZGM1NDI4NDM3ZTg3YmMwMGE3N2JkNTA4NzgyMmMzZDg5YmNjYTMyMWY1OTI3NmU0MjhlZjExYzY4NmQzNjIw";
        vc.connect("prod.vidyo.io", token, "Zainab", "Room", this);
    }

    public void Disconnect(View v) {
        vc.disconnect();
//        Intent intent=new Intent(this,Booked_apntments.class);
//        startActivity(intent);
    }
    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailure(Connector.ConnectorFailReason connectorFailReason) {

    }

    @Override
    public void onDisconnected(Connector.ConnectorDisconnectReason connectorDisconnectReason) {

    }
}
