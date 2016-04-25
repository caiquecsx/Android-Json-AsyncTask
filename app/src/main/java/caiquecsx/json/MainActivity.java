package caiquecsx.json;

        import android.app.Activity;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.concurrent.ExecutionException;

        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] arr = new String[0];
        ListView lv = (ListView) findViewById(R.id.listView);

        try {
            JSONArray  jArray = new getCategoriasWS("http://192.168.42.19/WebServiceImagens/JsonCategorias.php").execute().get();
            arr=new String[jArray.length()];
            for(int i=0;i<jArray.length();i++)
                try {
                    JSONObject object = jArray.getJSONObject(i);
                    arr[i] = object.getString("categoria");
                    //arr[i]=jArray.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arr);
        lv.setAdapter(adapter);
    }
}


