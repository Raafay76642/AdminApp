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
    private String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout)findViewById(R.id.videoFrame);
        get_intent();

    }
    public void get_intent(){
        Intent intent=getIntent();
        room=intent.getStringExtra("room");
    }
    public void Start(View v) {
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 10, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }
    public void Start() {
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 10, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }
    public void Connect(View v) {
        String token = "cHJvdmlzaW9uAHVzZXIxQGIxNGExYy52aWR5by5pbwA2MzcyNTY4MjcwNgAANjAzOTRlMmM4ZDRkZDQ2ZjVlZWI0NjQ4MzI1ZWU4MWQyNWU5YTQ1OTM4ZjBhYmZjYWMwN2U5OTA5YzljZDcwMjZkMDljYTE2YWFhMDA5ODU1ODg0MDc4ZTljYTQ1OWI0";
        vc.connect("prod.vidyo.io", token, "DemoUser", room, this);
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
