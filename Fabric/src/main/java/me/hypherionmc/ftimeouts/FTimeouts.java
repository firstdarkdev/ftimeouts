package me.hypherionmc.ftimeouts;

import net.fabricmc.api.DedicatedServerModInitializer;

public class FTimeouts implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        ServerInit.load();
    }
}
