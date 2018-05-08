package com.example.listdisplay;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        GetAlbumsTask task = new GetAlbumsTask();
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu

        return true;
    }

    private class GetAlbumsTask extends AsyncTask<Void, Void, String[]> {

        protected String[] doInBackground(Void... voids) {
            // Array of strings...
            String[] albumArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
                    "WebOS", "Ubuntu", "Windows7", "Max OS X"};

            String albumJsonStr = null;

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;

            try {
                final String baseUrl = "http://jsonplaceholder.typicode.com/albums";

                URL url = new URL(baseUrl);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }
                } finally {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                    }
                }

                albumJsonStr = buffer.toString();
            } catch (IOException e) {
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            Log.d("XKAL", albumJsonStr);

            ////return getWeatherDataFromJson(forecastJsonStr, numDays);
            return albumArray;


            // This will only happen if there was an error getting or parsing the forecast.
            ////return null;
        }

        protected void onPostExecute(String[] albums) {
            adapter.clear();

            adapter.addAll(albums);
        }
    }

}
