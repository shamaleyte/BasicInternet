package com.example.basicinternet;

import com.hypelabs.hype.Instance;

public class Client
{
    final Instance instance;
    final byte key[];

    public Client(Instance instance) {
        this.instance = instance;
        this.key = HpsGenericUtils.byteArrayHash(instance.getIdentifier());
    }
}
