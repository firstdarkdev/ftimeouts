package me.hypherionmc.ftimeouts.mixin;

import me.hypherionmc.ftimeouts.ServerInit;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketMixin {

    @Shadow private long keepAliveTime;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;disconnect(Lnet/minecraft/network/chat/Component;)V"))
    private void disconnect(ServerGamePacketListenerImpl instance, Component $$0) {
        final long keepAliveTimeoutMillis = ServerInit.config.keepAlive * 1000L;

        if (Util.getMillis() - keepAliveTime >= keepAliveTimeoutMillis) {
            instance.disconnect($$0);
        }
    }

    @ModifyConstant(method = "tick", constant = { @Constant(longValue = 15000L), @Constant(longValue = 25000L) })
    private long getKeepAlivePacketInterval(long interval) {
        return ServerInit.config.keepAlivePacket * 1000L;
    }
}
