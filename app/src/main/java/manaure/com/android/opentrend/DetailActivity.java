package manaure.com.android.opentrend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import manaure.com.android.opentrend.adapter.OpenTrendAdapter;


public class DetailActivity extends Activity {

    OpenTrendAdapter adapter;
    ImageView image;
    TextView textView;
    String text;
    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);


        loadViews();

        Intent intent = getIntent();
        if (intent != null) {

            if (intent.getExtras().get("text") != null) {
                text = intent.getExtras().get("text").toString();
                textView.setText(text);
            }

            if (intent.getExtras().get("image") != null) {
                imageUrl = intent.getExtras().get("image").toString();
                Picasso.with(this).load(imageUrl).into(image);
            }

        }


    }

    private void loadViews() {
        image = (ImageView) findViewById(R.id.imageView_detail);
        textView = (TextView) findViewById(R.id.textView_detail);
    }

    public void onItemClicked(Entity item) {

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
