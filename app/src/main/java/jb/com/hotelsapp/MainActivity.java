package jb.com.hotelsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<Hotel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get_hotels).setOnClickListener(this);

        adapter = new ArrayAdapter<Hotel>(this, android.R.layout.simple_list_item_1);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        new HotelTask().execute();
    }

    public class HotelTask extends AsyncTask<Void, Void, ArrayList<Hotel>>{

        private String baseUrl = "http://10.103.50.228:8080/HotelsProject/HotelsServlet";

        @Override
        protected ArrayList<Hotel> doInBackground(Void... voids) {
            ArrayList<Hotel> hotels = new ArrayList<>();
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(baseUrl);
                connection = (HttpURLConnection) url.openConnection();
                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String result = reader.readLine();

                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    long id = obj.getLong("id");
                    String name = obj.getString("name");
                    String address = obj.getString("address");
                    boolean kosher = obj.getBoolean("isKosher");
                    int rating = obj.getInt("rating");

                    hotels.add( new Hotel(id, name, address, kosher, rating) );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return hotels;
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> hotels) {
            adapter.clear();
            adapter.addAll(hotels);
        }
    }
}
