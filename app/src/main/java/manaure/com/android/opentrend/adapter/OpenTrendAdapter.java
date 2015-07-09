package manaure.com.android.opentrend.adapter;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import manaure.com.android.opentrend.Entity;
import manaure.com.android.opentrend.MainActivity;
import manaure.com.android.opentrend.R;

/**
 * @author Manaure Stoop
 *         <p/>
 *         Adaptador para la lista Objetos Open Trend
 */

public class OpenTrendAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    List<Entity> objects;
    Location myLocation;


    public OpenTrendAdapter(Context context, LayoutInflater inflater, List<Entity> objects) {
        this.inflater = inflater;
        this.context = context;
        this.myLocation = myLocation;
        this.objects = objects;

    }

    public View getItemView(final Entity object, View convertView, ViewGroup parent) {


        final ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_view, parent, false);
            holder = new ViewHolder();

            holder.txt_plain_text = (TextView) convertView
                    .findViewById(R.id.textView);
            holder.image = (ImageView) convertView
                    .findViewById(R.id.imageView);

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.txt_plain_text.setText(object.getDescription());

        String url = object.getThumbnail();


        Picasso.with(context).load(url).into(holder.image);

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity) context).onItemClicked(object);
            }
        });

        return convertView;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Entity getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(getItem(position), convertView, parent);
    }


    public void setRestaurants(ArrayList<Entity> objects) {
        this.objects = objects;
    }

    public List<Entity> getRestaurants() {
        return this.objects;
    }

    static class ViewHolder {
        TextView txt_plain_text;
        ImageView image;

    }
}
