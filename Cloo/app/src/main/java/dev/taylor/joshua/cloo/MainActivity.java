package dev.taylor.joshua.cloo;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
    implements
        TabHost.OnTabChangeListener,
        EntryFragment.OnEntryInteractionListener,
        FeedFragment.OnFeedInteractionListener
{
    private static final String ENTRY_TAG = "entry_tag";
    private static final String FEED_TAG = "feed_tag";
    private static final String TAG = MainActivity.class.getSimpleName();

    private EntryFragment mEntryFragment;
    private FeedFragment mFeedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEntryFragment = EntryFragment.newInstance();
        mFeedFragment = FeedFragment.newInstance();

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setOnTabChangedListener(this);
        tabHost.setup();

//        TabHost.TabSpec tab1 = tabHost.newTabSpec(ENTRY_TAG);
//        TabHost.TabSpec tab2 = tabHost.newTabSpec(FEED_TAG);
//
//        // Set the Tab name and Activity
//        // that will be opened when particular Tab will be selected
//        tab1.setIndicator("Entry");
//        tab1.setContent(R.id.tab_entry);
//
//        tab2.setIndicator("Feed");
//        tab2.setContent(R.id.tab_feed);

        /** Add the tabs  to the TabHost to display. */
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);

        setNewTab(this, tabHost, ENTRY_TAG, R.string.tab_entry_title, android.R.drawable.star_on, R.id.tab_entry);
        setNewTab(this, tabHost, FEED_TAG, R.string.tab_feed_title, android.R.drawable.star_on, R.id.tab_feed);

        tabHost.setCurrentTabByTag(ENTRY_TAG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tag)
    {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        
        if (tag.equals(ENTRY_TAG)) {
            fragmentTransaction.replace(R.id.fragment_container, mEntryFragment).commit();
        } else if (tag.equals(FEED_TAG)) {
            fragmentTransaction.replace(R.id.fragment_container, mFeedFragment).commit();
        } else {
            Log.w(TAG, "Tab tag " + tag + " did not match.");
        }
    }

    @Override
    public void onEntryInteraction(Uri uri)
    {

    }

    @Override
    public void onFeedInteraction(String id)
    {

    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setVisibility(ImageView.GONE);
//        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }
}
