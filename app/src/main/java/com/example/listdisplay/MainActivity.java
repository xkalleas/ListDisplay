package com.example.listdisplay;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            String[] albumArray = {"Android","IPhone","WindowsMobile","Blackberry",
                    "WebOS","Ubuntu","Windows7","Max OS X"};

            return albumArray;
        }

        protected void onPostExecute(String[] albums) {
            adapter.clear();

            adapter.addAll(albums);
        }
    }

}
