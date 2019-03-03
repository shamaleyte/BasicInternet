package com.example.basicinternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.lang.ref.WeakReference;


public class ClientsListActivity extends AppCompatActivity
{
    final private ClientsList activityClientsList = new ClientsList();
    private ListView clientsListView;
    private static WeakReference<ClientsListActivity> defaultInstance;
    private ClientsAdapter activityClientsAdapter;
    private Network network = Network.getInstance();
    private static final String TAG =  HypePubSub.class.getName();
    private static final String HYPE_PUB_SUB_LOG_PREFIX = HpsConstants.LOG_PREFIX + "<shamaleyte> ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Hype Devices");
        Log.i(TAG, String.format("%s Client List Created.",
                HYPE_PUB_SUB_LOG_PREFIX));

        setContentView(R.layout.activity_clients_list);

        clientsListView = findViewById(R.id.activity_clients_list_view);
        setClientsAdapterFromNetworkClients();
        clientsListView.setAdapter(getClientsAdapter());

        setClientsListActivity(this);

        Log.i(TAG, String.format("%s ClientListActivity INSTANCE Created.",
                HYPE_PUB_SUB_LOG_PREFIX));
    }

    private void setClientsAdapterFromNetworkClients() {
        Log.i(TAG, String.format("%s setClientsAdapterFromNetworkClients.: %d",
                HYPE_PUB_SUB_LOG_PREFIX, network.networkClients.size()));
        ClientsAdapter clientAdapter = getClientsAdapter();
        clientAdapter.clear();
        for(int i=0; i<network.networkClients.size();i++) {
            clientAdapter.add(network.networkClients.get(i));
            Log.i(TAG, String.format("%s setClientsAdapterFromNetworkClients. Next %s",
                    HYPE_PUB_SUB_LOG_PREFIX, network.networkClients.get(i)));
        }
    }

    private ClientsAdapter getClientsAdapter() {
        Log.i(TAG, String.format("%s getClientsAdapter.",
                HYPE_PUB_SUB_LOG_PREFIX));
        if (activityClientsAdapter == null) {
            activityClientsAdapter = activityClientsList.getClientsAdapter(ClientsListActivity.this);
        }

        return activityClientsAdapter;
    }

    public static ClientsListActivity getDefaultInstance() {

        return defaultInstance != null ? defaultInstance.get() : null;
    }

    private static void setClientsListActivity(ClientsListActivity instance) {
        Log.i(TAG, String.format("%s setClientsListActivity.",
                HYPE_PUB_SUB_LOG_PREFIX));
        defaultInstance = new WeakReference<>(instance);
    }

    protected void updateUI() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setClientsAdapterFromNetworkClients();
            }
        });
    }

}
