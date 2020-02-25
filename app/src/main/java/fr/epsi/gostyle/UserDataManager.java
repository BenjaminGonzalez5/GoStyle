package fr.epsi.gostyle;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;


public class UserDataManager extends GoStyleActivity{

    private String urlStr;

    public UserDataManager(){
        urlStr = "http://10.0.2.2:8080/API_GoStyle_war/";
    }

    public void updateUserData(String urlAdd){
        String url = updateUrl(urlAdd);
        new HttpAsyTask(url, new HttpAsyTask.HttpAsyTaskListener() {
            @Override
            public void webServiceDone(String result) throws ParseException {
                initData(result);
            }

            @Override
            public void webServiceError(Exception e) {
                displayToast(e.getMessage());
            }
        }).execute();
    }

    private String updateUrl(String add){
        return urlStr = urlStr + add;
    }

    private void initData(String data) throws ParseException {
        try {
            JSONArray jsonArray=new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
