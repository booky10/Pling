package io.github.haykam821.pling.mixin;

import io.github.haykam821.pling.Pling;
import net.minecraft.client.Game;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Game.class)
public class GameMixin {

    @Inject(method = "onStartGameSession", at = @At("HEAD"))
    private void onWorldLoaded(CallbackInfo info) {
        Pling.playEffects();
    }
}
