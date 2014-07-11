package com.androidbegin.jsonparsetutorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItemView extends Activity {
	// Declare Variables
	String rank;
	String country;
	String population;
	String flag;
	String position;
    String comments;
	
	ImageLoader imageLoader = new ImageLoader(this);

	
	Button submit;
	
	EditText et;
	
	Coustxt comment;
	
	Context con;
	
	Intent i;
	
	
	
	ListViewAdapter adapter;

	ArrayList<HashMap<String, String>> arraylist;
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	static String COUNTRY = "title";
	static String POPULATION = "content";
	static String FLAG = "image";

	static String COMMENTS = "comments";
	
	
	
	
	 private ListView mList;
	   
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);

		con=this;
		
		autoUpdate=new Timer();
		
		
		
		
        arraylist = new ArrayList<HashMap<String, String>>();
		
		piclist = new ArrayList<HashMap<String, String>>();
		
		
	//	listview = (ListView) findViewById(R.id.listview);
		
		adapter = new ListViewAdapter(SingleItemView.this, arraylist,piclist);
		
		
		
		mList = (ListView) findViewById(R.id.comview);
		
		
		
		
		
		i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("rank");
		// Get the result of country
		country = i.getStringExtra("country");
		// Get the result of population
		population = i.getStringExtra("population");
		// Get the result of flag
		flag = i.getStringExtra("flag");
		
		comments=i.getStringExtra("comments");
		
		
		
		Display display = getWindowManager().getDefaultDisplay(); 
		int w = display.getWidth();  // deprecated
		int h = display.getHeight();  //
		
		ImageView img = (ImageView) findViewById(R.id.flag);
		
	img.getLayoutParams().height = w;

		// Locate the TextViews in singleitemview.xml
		TextView txtrank = (TextView) findViewById(R.id.rank);
		TextView txtcountry = (TextView) findViewById(R.id.country);
		TextView txtpopulation = (TextView) findViewById(R.id.population);
		
		submit = (Button) findViewById(R.id.comsub);
		
		et = (EditText) findViewById(R.id.editText1);
		
		
		comment=(Coustxt) findViewById(R.id.comment);
		
		
		
		
		submit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if (et.getText().toString().length() < 1) {
		            // out of range
		            Toast.makeText(con, "please enter something", Toast.LENGTH_LONG).show();
		        } else {
		 //           pb.setVisibility(View.VISIBLE);
		           //Old**   new MyAsyncTask().execute(et.getText().toString(),"1");
		        
		            
		            
		            
		            
		            
		            
		            
		        // new MyAsyncTask().execute("{\"cmtmsg\":\""+et.getText().toString()+"\",\"id\":\"1\"}");
			            
		        	 
		        //	 new MyAsyncTask().execute("{\"id\":\"1\",\"cmtmsg\":\""+et.getText().toString()+"\"}");
			          
		        	comment.setText(comments);
		        	
		        }
				
				
			}
			
		});
		
		
		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

	//	population.replace("/\"", "");
		
		
		// Set results to the TextViews
		txtrank.setText(rank);
		txtcountry.setText(country);
		txtpopulation.setText(population);
		
		comment.setText(comments);

		
		
		
		
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
//		imageLoader.DisplayImage(flag, imgflag);
		
		Picasso.with(this).load(flag).into(imgflag);
		
		
		
	}
	
	
	
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
		
	/*		
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Android JSON Parse Tutorial");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();'
			
			*/
		}

		
		JSONObject jsonobject;
		
		JSONArray jsonarray;
		
		
		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
		
			piclist = new ArrayList<HashMap<String, String>>();
			
			
			{
				
		
		
		jsonobject = JSONfunctions.getJSONfromURL("http://noopus.net/noopazine/chat.php");

			
		Log.d("LOGEE", ""+jsonobject);
			
	//		noopus.net/jasontest/count.json
			
			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("noopazine");

			//	for (int i = jsonarray.length()-3; i < jsonarray.length(); i++) {
				
					for (int i = 0; i < jsonarray.length(); i++) {

						map = new HashMap<String, String>();
						
						HashMap<String,String> picmap = new HashMap<String, String>();
						
						
						if(isConnectedToInternet())
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
				
					
					if(map.get("date")!=jsonobject.getString("date"))
						map.put("date", jsonobject.getString("date"));
					
					
					if(map.get("comments")!=jsonobject.getString("comments"))
						map.put("comments", jsonobject.getString("comments").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
					
					if(map.get("title")!=jsonobject.getString("title"))
						map.put("title", jsonobject.getString("title").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
					if(map.get("content")!=jsonobject.getString("content"))
						map.put("content", jsonobject.getString("content").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
		//			
				
					
					
			
					if(map.get("image")!=jsonobject.getString("image"))
						map.put("image", jsonobject.getString("image"));
				
				
					picmap.put("image", jsonobject.getString("image"));
					
					
		//			if(click)
				
				
			//		if(!click)
					piclist.add(picmap);
					
					// Set the JSON Objects into the array
				
					arraylist.add(map);
					
					
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			
			
			index = mList.getFirstVisiblePosition();
			v = mList.getChildAt(0);
			top = (v == null) ? 0 : v.getTop();

			
			}
			// ...

			// restore
		
			
			
			
			return null;
		}
		int index,top;
		View v;
		
		HashMap<String, String> map;
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
		//	listview = (ListView) findViewById(R.id.listview);
			
			     
			
			
			// Pass the results into ListViewAdapter.java
			
		//	adapter.notifyDataSetChanged();
			
		//	if(!click)				
			
		//		if(isConnectedToInternet())     			
			
			
			
			
			if(isConnectedToInternet())
			{		
				
				adapter = new ListViewAdapter(SingleItemView.this, arraylist,piclist);
			// Set the adapter to the ListView
			
		
			
		
				adapter.notifyDataSetChanged();
				
				
			mList.setAdapter(adapter);
			
			
			mList.setSelectionFromTop(index, top);
			}
			// Close the progressdialog
		//	mProgressDialog.dismiss();
		}
	}
	
	/*
	private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0],params[1]);
            return null;
        }

        protected void onPostExecute(Double result) {
        //    pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "command sent",
                    Toast.LENGTH_LONG).show();
       
            comments=i.getStringExtra("comments");
    		
            comment.setText(comments);

        }

        protected void onProgressUpdate(Integer... progress) {
        //    pb.setProgress(progress[0]);
        }

        public void postData(String valueIWantToSend,String id) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://noopus.net/noopazine/noopazinecommentinsert.php");
            
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                
                nameValuePairs.add(new BasicNameValuePair("Comment",valueIWantToSend));
           
                nameValuePairs.add(new BasicNameValuePair("Id",id));
                
                
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }
	*/
	
	
	 public boolean isConnectedToInternet(){
	        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	          if (connectivity != null) 
	          {
	              NetworkInfo[] info = connectivity.getAllNetworkInfo();
	              if (info != null) 
	                  for (int i = 0; i < info.length; i++) 
	                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                      {
	                          return true;
	                      }

	          }
	          return false;
	    }
	 
	 
	 Timer autoUpdate;
	 @Override
	 public void onResume()
	 {
		 super.onResume();
		 new DownloadJSON().execute();
	
		 /*
		 autoUpdate.schedule(new TimerTask() {
		         @Override
		         public void run() {
		           runOnUiThread(new Runnable() {
		             public void run() {
		     	//		if(isConnectedToInternet())     				
		         //      new DownloadJSON().execute(); // this is the class that downloads the data from the server.
		           
		            	if(isConnectedToInternet())     				
		            		new DownloadJSON().execute(); // this is the class that downloads the data from the server.
				           
		            		// 	alertDialog.show();
		  			             
		             }
		           });
		         }
		       }, 0, 2000);*/
	 }
	 
	    
}