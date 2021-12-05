package io.github.haykam821.pling;

import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class Pling implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
    }

    public static void playLoadingSound() {
        SoundEvent event = SoundEvents.EXPERIENCE_ORB_PICKUP;
        SimpleSoundInstance sound = SimpleSoundInstance.forUI(event, 1f);
        Minecraft.getInstance().getSoundManager().play(sound);
    }

    public static void playEffects() {
        Pling.playLoadingSound();

        Window window = Minecraft.getInstance().getWindow();
        GLFW.glfwRequestWindowAttention(window.getWindow());
    }
}
