package com.example.basicinternet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hypelabs.hype.Hype;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Collections;
import java.util.ListIterator;

public class SubscriptionsListActivity extends AppCompatActivity
{
    private ListView subscriptionsListView;

    public void readSubsFromSharedPreferences(HypePubSub hps){
        SharedPreferences prefs = getSharedPreferences("mySubs", Context.MODE_PRIVATE);
        String jsonArrayString = prefs.getString("mySubs", "");
        if (!TextUtils.isEmpty(jsonArrayString)) {
            try {
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String service= jsonArray.getString(i);
                        hps.issueSubscribeReq(service);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Subscriptions");
        setContentView(R.layout.activity_subscriptions_list);


        // Get ListView object from xml
        HypePubSub hps = HypePubSub.getInstance();
        //readSubsFromSharedPreferences(hps);
        subscriptionsListView = findViewById(R.id.activity_subscriptions_list_view);
        subscriptionsListView.setAdapter(hps.ownSubscriptions.getSubscriptionsAdapter(SubscriptionsListActivity.this));

        subscriptionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Subscription subscription = (Subscription) subscriptionsListView.getItemAtPosition(position);

                Intent intent = new Intent(SubscriptionsListActivity.this, MessagesActivity.class);

                intent.putExtra(MessagesActivity.EXTRA_SUBSCRIPTION_KEY, subscription.serviceKey);

                startActivity(intent);

            }
        });
    }
}
