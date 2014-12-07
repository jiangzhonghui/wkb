package com.apj.wkb;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class SearchActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private Button btn;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(this.getResources().getString(R.string.app_name));
        btn = (Button) findViewById(R.id.btnclick);
        Log.d("hello","bb");

        Thread t=new Thread(r);
        t.start();
    }

    Runnable r=new Runnable() {
        @Override
        public void run() {
            Log.d("sleep","start");
            try {
                Thread.sleep(6000);
            }catch (Exception ex) {
                Log.e("ex",ex.getMessage());
            }
            Log.d("sleep","end");
            Message msg=new Message();
            msg.arg1=1;
            handler.sendMessage(msg);

        }
    };

    private Handler handler=new Handler(){
           public void handleMessage(Message msg){
                switch (msg.arg1) {
                    case 1:
                        Log.d("aa","bb");
                          break;
                    case 2:
                            Log.d("bb","bb");
                        default:
                          break;
                }
             }
     };


    private SearchView mSearchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
       MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint(this.getResources().getString(R.string.search_hint));
        mSearchView.setIconifiedByDefault(false);
        MenuItemCompat.expandActionView(searchItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_search:
//                Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
//                startActivityIfNeeded(intent,0);
                //startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Intent intent =new Intent(this,SearchResultActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
