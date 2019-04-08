package online.bcasino.pbonus.review.adminapp;

import android.app.Application;

public class Global_class extends Application {

    private int selected;
    private String cateogory;

    public String getCateogory() {
        return cateogory;
    }

    public void setCateogory(String cateogory) {
        this.cateogory = cateogory;
    }


    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

}
