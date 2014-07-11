package com.androidbegin.jsonparsetutorial;

import java.util.ArrayList;
import java.util.HashMap;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	
	ArrayList<HashMap<String, String>> imagedata;
	
	ImageLoader imageLoader;

	HashMap<String, String> resultp = new HashMap<String, String>();

	HashMap<String, String> peepee = new HashMap<String, String>();

	
	public ListViewAdapter(Context context,ArrayList<HashMap<String, String>> arraylist,ArrayList<HashMap<String, String>> piclist) {
		this.context = context;
		data = arraylist;
	
		imagedata = piclist;
		
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		Coustxt country;
		Coustxt population;
		ImageView flag;
		
		
	
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		
		resultp = data.get(position);

		
		peepee = imagedata.get(position);
		
		
		// Locate the TextViews in listview_item.xml
		rank = (TextView) itemView.findViewById(R.id.rank);
		country = (Coustxt) itemView.findViewById(R.id.country);
		population = (Coustxt) itemView.findViewById(R.id.population);

		// Locate the ImageView in listview_item.xml
		flag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		rank.setText(resultp.get(MainActivity.RANK));
		country.setText(resultp.get(MainActivity.COUNTRY));
		//population.setText(resultp.get(MainActivity.POPULATION));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
//		imageLoader.DisplayImage(peepee.get(MainActivity.FLAG), flag);

		
	//	Picasso f;
		
		Picasso.with(context).load(peepee.get(MainActivity.FLAG)).into(flag);
		
		
		
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("rank", resultp.get(MainActivity.RANK));
				// Pass all data country
				intent.putExtra("country", resultp.get(MainActivity.COUNTRY));
				// Pass all data population
				intent.putExtra("population",resultp.get(MainActivity.POPULATION));
				// Pass all data flag
				intent.putExtra("flag", resultp.get(MainActivity.FLAG));
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
