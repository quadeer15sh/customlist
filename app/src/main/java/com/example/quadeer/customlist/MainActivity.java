package com.example.quadeer.customlist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Items> itemsList;
    ListView listView;

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                Log.i("JSON",s);
                JSONObject jsonObject = new JSONObject(s);
                System.out.println(jsonObject);
                ArrayList<String> list = new ArrayList<String>();

                //initializing objects
                itemsList = new ArrayList<>();
                listView = findViewById(R.id.listView);

                JSONArray jsonList1 = jsonObject.getJSONArray("names");
                JSONArray jsonList2 = jsonObject.getJSONArray("prices");
                JSONArray jsonList3 = jsonObject.getJSONArray("units");

                Log.i("Product",jsonList1.getString(0));


                for (int i = 0; i < 5; i++){
                    itemsList.add(new Items(jsonList1.getString(i), jsonList2.getString(i), jsonList3.getString(i)));
                }

                //creating the adapter
                MyCustomListAdapter adapter = new MyCustomListAdapter(MainActivity.this, R.layout.my_list_item, itemsList);

                //attaching adapter to the listview
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class DownloadTask2 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                Log.i("JSON",s);
                JSONObject jsonObject = new JSONObject(s);
                System.out.println(jsonObject);
                ArrayList<String> list = new ArrayList<String>();

                //initializing objects
                itemsList = new ArrayList<>();
                listView = findViewById(R.id.listView2);

                JSONArray jsonList1 = jsonObject.getJSONArray("names");
                JSONArray jsonList2 = jsonObject.getJSONArray("prices");
                JSONArray jsonList3 = jsonObject.getJSONArray("units");

                Log.i("Product",jsonList1.getString(0));


                for (int i = 0; i < 5; i++){
                    itemsList.add(new Items(jsonList1.getString(i), jsonList2.getString(i), jsonList3.getString(i)));
                }

                //creating the adapter
                MyCustomListAdapter adapter = new MyCustomListAdapter(MainActivity.this, R.layout.my_list_item, itemsList);

                //attaching adapter to the listview
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("https://recommendationapi.herokuapp.com/api/content_recommendation/3");

        DownloadTask2 task2 = new DownloadTask2();
        task2.execute("https://recommendationapi.herokuapp.com/api/popular_products");
    }
}
