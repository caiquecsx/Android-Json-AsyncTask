package caiquecsx.json;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by caique on 23/04/16.
 */
public class getCategoriasWS extends AsyncTask<String, Void, JSONArray> {
    String url;
    getCategoriasWS(String url){
        this.url = url;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray jArray = null;
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            HttpResponse response = httpclient.execute(httppost);
            String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            JSONObject object = new JSONObject(jsonResult);


            jArray = (JSONArray)object.getJSONArray("categorias");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return jArray;
    }

    private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}