package fr.epsi.gostyle;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

class HttpAsyTask extends AsyncTask<String, Void, Object> {
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
    protected Object doInBackground(String... strings) {
        try {
            if (strings.length == 1){
                return postReq(urlStr, strings[0]);
            }
            return getReq(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        } catch (IdentifiantMotDePasseException e) {
            return e;
        }
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

    private Object postReq(String urlStr, String jsonParam) throws MalformedURLException, IdentifiantMotDePasseException {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = null;
            if (urlStr.startsWith("http:")){
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setDoOutput(true);
                try(OutputStream os = urlConnection.getOutputStream()) {
                    byte[] input = jsonParam.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                if (urlConnection.getResponseCode() != 200){
                    throw new IdentifiantMotDePasseException("Identifiant ou mot de passe incorrect");
                }
            }
            try(BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
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

    private Object getReq(String urlStr) throws MalformedURLException {
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
