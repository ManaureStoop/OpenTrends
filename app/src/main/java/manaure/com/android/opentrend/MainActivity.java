package manaure.com.android.opentrend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import manaure.com.android.opentrend.adapter.OpenTrendAdapter;


public class MainActivity extends Activity {
    private String URL_BASE = "https://randomapi.com/api/?key=LMW0-SW97-ISC4-FF25&id=t60ldyb&results=20";

    OpenTrendAdapter adapter;
    ListView list;
    private LayoutInflater inflater;
    List<Entity> entities;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadViews();

        entities = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("PRUEBA", response.toString());
                        parseJson(response);
                        inflater = (LayoutInflater) MainActivity.this
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        adapter = new OpenTrendAdapter(MainActivity.this, inflater,entities);
                        list.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAG PRUEBA", "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        requestQueue.add(jsArrayRequest);
    }

    private void loadViews() {
        list = (ListView) findViewById(R.id.listView_main);
    }

    public void onItemClicked(Entity item) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra("text",item.getDescription());
        i.putExtra("image",item.getPicture());
        startActivity(i);
    }

    public void parseJson(JSONObject jsonObject) {

        if (jsonObject != null) {
            try {


                // Getting JSON Array node
                JSONArray results = jsonObject.getJSONArray("results");

                // looping through All Contacts
                for (int i = 0; i < results.length(); i++) {

                    JSONObject result = results.getJSONObject(i);
                    JSONObject entity = result.getJSONObject("entity");

                    String description = entity.getString("descritpion");
                    String thumbnail = entity.getString("thumbnail");
                    String picture = entity.getString("picture");

                    Entity entityO = new Entity();
                    entityO.setDescription(description);
                    entityO.setPicture(picture);
                    entityO.setThumbnail(thumbnail);


                    entities.add(entityO);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
