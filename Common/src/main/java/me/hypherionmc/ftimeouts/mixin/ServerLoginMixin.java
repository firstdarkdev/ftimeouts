package me.hypherionmc.ftimeouts.mixin;

import me.hypherionmc.ftimeouts.ServerInit;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginPacketListenerImpl.class)
public class ServerLoginMixin {

    @Shadow private int tick;

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTick(CallbackInfo ci) {
        if (tick >= ServerInit.config.connectionTimeout) {
            ((ServerLoginPacketListenerImpl) (Object) this).disconnect(
                    Component.translatable("multiplayer.disconnect.slow_login")
            );
        }
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerLoginPacketListenerImpl;disconnect(Lnet/minecraft/network/chat/Component;)V"))
    private void disconnect(ServerLoginPacketListenerImpl instance, Component $$0) {

    }
}
