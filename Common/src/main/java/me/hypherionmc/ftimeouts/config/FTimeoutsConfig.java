package me.hypherionmc.ftimeouts.config;

import me.hypherionmc.craterlib.common.config.ModuleConfig;
import me.hypherionmc.ftimeouts.ServerInit;
import me.hypherionmc.ftimeouts.Constants;
import me.hypherionmc.moonconfig.core.conversion.Path;
import me.hypherionmc.moonconfig.core.conversion.SpecComment;

public class FTimeoutsConfig extends ModuleConfig {

    @Path("connectionTimeout")
    @SpecComment("The login timeout in ticks")
    public int connectionTimeout = 2400;

    @Path("readTimeout")
    @SpecComment("The connection read timeout in seconds")
    public int readTimeout = 120;

    @Path("keepAlive")
    @SpecComment("How long (in seconds) before server keep alive ends")
    public int keepAlive = 60;

    @Path("keepAlivePacket")
    @SpecComment("""
            The KeepAlive timeout in seconds.
            This is how long the server waits for a player to return a KeepAlive packet\s
            before disconnecting them.
            This is automatically rounded up to a multiple of the KeepAlive packet interval.""")
    public int keepAlivePacket = 120;

    public FTimeoutsConfig() {
        super(Constants.MOD_ID, "ftimeouts");
        registerAndSetup(this);
    }

    @Override
    public void configReloaded() {
        ServerInit.config = loadConfig(this);
    }
}
