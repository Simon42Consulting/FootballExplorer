package fr.a42consulting.footballexplorer;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public abstract class HttpGetRequest extends AsyncTask<String, Void, String> implements IHttpGetRequest {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 1000;

    @Override
    public abstract void onResponseReceived(String result);

    @Override
    protected String doInBackground(String... params){
        String urlString = params[0];
        String urlParamName = params[1];
        String urlParamValue = params[2];

        String result;
        String inputLine;
        try {
            URL myUrl = new URL(urlString + "?" + urlParamName + "=" + URLEncoder.encode(urlParamValue, "UTF-8"));
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    protected void onPostExecute(String result){
        onResponseReceived(result);
    }
}