package fr.epsi.gostyle;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

class HttpAsyTask extends AsyncTask<Void, Void, Object> {
    interface HttpAsyTaskListener{
        void webServiceDone(String result) throws ParseException;
        void webServiceError(Exception e);
    }

    private HttpAsyTaskListener httpAsyTaskListener;
    private String urlStr = "";

    public HttpAsyTask(String url, HttpAsyTaskListener listener){
        httpAsyTaskListener=listener;
        urlStr = url;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            return call(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        }
    }

    void postExecute(final String urlApi, final JSONObject jsonLogin) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL(urlApi);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    Log.i("JSON", jsonLogin.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonLogin.toString());


                    if(conn.getResponseCode() == 200)
                    {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            Log.i("BR : ", currentLine);
                        }
                        //return currentLine;
                    }
                    else {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            Log.i("BR : ", currentLine);
                        }
                    }
                    os.flush();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));

                    os.close();

                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("ERREUR API : ",e.getMessage());
                    e.printStackTrace();
                }
            }
        });
            thread.start();
        }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception){
            httpAsyTaskListener.webServiceError((Exception)o);
        }
        else {
            try {
                httpAsyTaskListener.webServiceDone(o.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private Object call(String urlStr) throws MalformedURLException {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = null;
            if (urlStr.startsWith("http:")){
                urlConnection = (HttpURLConnection) url.openConnection();
            }
            try {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                return convertStreamToString(inputStream);
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + NL);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
