package com.example.basicinternet;

public class ServiceManager
{
    final byte serviceKey[];
    final ClientsList subscribers;

    public ServiceManager(byte serviceKey[]) {

        this.serviceKey = serviceKey;
        this.subscribers = new ClientsList();
    }
}
