package me.hypherionmc.ftimeouts;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class FTimeouts {

    public FTimeouts() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {
        ServerInit.load();
    }

}
