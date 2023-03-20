package me.hypherionmc.ftimeouts.mixin;

import me.hypherionmc.ftimeouts.ServerInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = {
        "net/minecraft/network/Connection$1",
        "net/minecraft/server/network/ServerConnectionListener$1"
})
public class ConnectionMixin {

    @ModifyArg(method = "initChannel",
            at = @At(value = "INVOKE", target = "Lio/netty/handler/timeout/ReadTimeoutHandler;<init>(I)V")
    )
    private int getReadTimeout(int timeout) {
        return ServerInit.config.readTimeout;
    }

}
