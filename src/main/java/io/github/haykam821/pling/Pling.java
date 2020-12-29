package io.github.haykam821.pling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import io.github.haykam821.pling.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.registry.Registry;

public class Pling implements ModInitializer {
	private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
	private static final Logger LOGGER = LogManager.getLogger("Pling");
	private static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitialize() {
		return;
	}

	public static void playLoadingSound() {
		SoundEvent soundEvent = Pling.getLoadingSound();
		if (soundEvent != null) {
			PositionedSoundInstance sound = PositionedSoundInstance.master(soundEvent, Pling.getConfig().pitch);
			CLIENT.getSoundManager().play(sound);
		}
	}

	/**
	 * Plays effects if the window has the proper focus.
	 */
	public static void playEffects() {
		if (Pling.hasProperFocus()) {
			Pling.playLoadingSound();
			if (CONFIG.requestWindowAttention) {
				GLFW.glfwRequestWindowAttention(CLIENT.getWindow().getHandle());
			}
		}
	}

	public static boolean hasProperFocus() {
		return !CONFIG.requireUnfocused || !CLIENT.isWindowFocused();
	}

	public static SoundEvent getLoadingSound() {
		try {
			Identifier soundId = new Identifier(CONFIG.sound);
			return Registry.SOUND_EVENT.get(soundId);
		} catch (InvalidIdentifierException exception) {
			LOGGER.warn("Invalid loading sound event ID: '{}'", CONFIG.sound);
			return null;
		}
	}
	
	public static ModConfig getConfig() {
		return CONFIG;
	}
}