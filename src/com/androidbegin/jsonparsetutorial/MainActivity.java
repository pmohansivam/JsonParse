package com.androidbegin.jsonparsetutorial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	static String COUNTRY = "title";
	static String POPULATION = "content";
	static String FLAG = "image";

	
	
	

    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

   // private JazzyListView mList;
    
    private ListView mList;
    
    private HashMap<String, Integer> mEffectMap;
   // private int mCurrentTransitionEffect = JazzyHelper.SLIDE_IN;

    
    Button but;
    
    boolean click=false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listview_main);
		
		
		///but=(Button) this.findViewById(R.id.button1);
		
		
	
		
		
		arraylist = new ArrayList<HashMap<String, String>>();
		
		piclist = new ArrayList<HashMap<String, String>>();
		
		
	//	listview = (ListView) findViewById(R.id.listview);
		
		adapter = new ListViewAdapter(MainActivity.this, arraylist,piclist);
		
		
		 // mList = (JazzyListView) findViewById(R.id.listview);
		   
		mList = (ListView) findViewById(R.id.listview);
		  
		  
		// Execute DownloadJSON AsyncTask
	//	new DownloadJSON().execute();
		
		
		
		
	}

	// DownloadJSON AsyncTask
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

		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
		
			piclist = new ArrayList<HashMap<String, String>>();
			
			
			// Retrieve JSON Objects from the given URL address
//			jsonobject = JSONfunctions
//					.getJSONfromURL("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");

		//	http://192.168.1.68/myjson/test.json
	
			
		//	jsonobject = JSONfunctions.getJSONfromURL("http://192.168.1.68/myjson/count.json");

			

			  
			  
			
	//		if(isConnectedToInternet())
			{
				
		
	//	jsonobject = JSONfunctions.getJSONfromURL("http://noopus.net/jasontest/count.json");
		
		jsonobject = JSONfunctions.getJSONfromURL("http://noopus.net/noopazine/chat.php");

			
			
	//		noopus.net/jasontest/count.json
			
			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("noopazine");

				for (int i = jsonarray.length()-10; i < jsonarray.length(); i++) {
					
						map = new HashMap<String, String>();
						
						HashMap<String,String> picmap = new HashMap<String, String>();
						
						
						if(isConnectedToInternet())
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
				
					
					if(map.get("date")!=jsonobject.getString("date"))
						map.put("date", jsonobject.getString("date"));
					
					if(map.get("title")!=jsonobject.getString("title"))
						map.put("title", jsonobject.getString("title"));
					
					if(map.get("content")!=jsonobject.getString("content"))
						map.put("content", jsonobject.getString("content"));
				
			
					if(map.get("image")!=jsonobject.getString("image"))
						map.put("image", jsonobject.getString("image"));
				
				
					picmap.put("image", jsonobject.getString("image"));
					
					
		//			if(click)
				
				
					if(!click)
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
				
				adapter = new ListViewAdapter(MainActivity.this, arraylist,piclist);
			// Set the adapter to the ListView
			
		
			
		
				adapter.notifyDataSetChanged();
				
				
			mList.setAdapter(adapter);
			
			
			mList.setSelectionFromTop(index, top);
			}
			// Close the progressdialog
		//	mProgressDialog.dismiss();
		}
	}
	
	
	
	Timer autoUpdate;
	
	@Override
    public void onResume() {
      super.onResume();
      autoUpdate = new Timer();
    
      
     /* 
      autoUpdate.schedule(new TimerTask() {
         @Override
         public void run() {
           runOnUiThread(new Runnable() {
             public void run() {
     			if(isConnectedToInternet())     				
               new DownloadJSON().execute(); // this is the class that downloads the data from the server.
             }
           });
         }
       }, 0, 5000); // updates each 10 secs
     
	*/
      new DownloadJSON().execute(); // this is the class that downloads the data from the server.
      
	}


    @Override
    public void onPause() {
       autoUpdate.cancel();
       super.onPause();
    }

    
    
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
    
}