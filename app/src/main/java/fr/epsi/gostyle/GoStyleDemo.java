package fr.epsi.gostyle;

import android.app.Application;

class GoStyleDemo extends Application {

    private String title = "";

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
