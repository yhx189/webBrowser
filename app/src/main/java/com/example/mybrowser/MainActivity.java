package com.example.mybrowser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private WebView mWebView;
	private EditText mAddressbarText;
	private Button mGoButton;
	private Button.OnClickListener MyBrowserGoButtonListener =
			new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					mWebView.loadUrl(mAddressbarText.getText().toString());
					
				}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mWebView = (WebView)findViewById(R.id.main_view);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        
        mAddressbarText = (EditText)findViewById(R.id.addressbar_view);
        mGoButton = (Button)findViewById(R.id.addressbar_button);
        mGoButton.setOnClickListener(MyBrowserGoButtonListener);
        
        String data = getIntent().getDataString();
        System.out.println("MY_BROWSER_STATE: onCreate: "+data);
        mWebView.loadUrl(data);
        
        
    }
    @Override
    protected void onPause(){
    	super.onPause();
    	Log.i("MY_BROWSER_STATE","onPause");
    }
    @Override
    protected void onResume(){
    	super.onResume();
    	Log.i("MY_BROWSER_STATE","onResume");
    }
    @Override
    protected void onStop(){
    	super.onStop();
    	Log.i("MY_BROWSER_STATE","onStop");
    }
    @Override
    protected void onStart(){
    	super.onStart();
    	Log.i("MY_BROWSER_STATE","onStart");
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private class MyWebViewClient extends WebViewClient{
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView webview, String url){
    		Log.i("MY_BROWSER_URL: [shouldOverrideUrlLoading]",url);
    		//if(url.equalsIgnoreCase("about:blank"))
    		//	return true;
    		webview.loadUrl(url);
    		return true;
    	}
    	
    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) {
    		// TODO Auto-generated method stub
    		Log.i("MY_BROWSER_URL: [onPagestart]",url);
    		super.onPageStarted(view, url, favicon);
    	}
    	
    	@Override
    	public void onPageFinished(WebView view, String url)
    	{
    	    if ("about:blank".equals(url) && view.getTag() != null)
    	    {
    	    	Log.i("MY_BROWSER_URL: [onPageFinished]",view.getTag().toString());
    	        view.loadUrl(view.getTag().toString());
    	        mAddressbarText.setText(view.getTag().toString());
    	    }
    	    else
    	    {
    	    	Log.i("MY_BROWSER_URL: [onPageFinished]", url);
    	        view.setTag(url);
    	        mAddressbarText.setText(url);
    	    }
    	}
    	
    	@Override
    	public void onLoadResource(WebView view, String url)
    	{
    		Log.i("MY_BROWSER_DEBUG: [onLoadResource]",url);
    		super.onLoadResource(view, url);        
    	}
    }
}
