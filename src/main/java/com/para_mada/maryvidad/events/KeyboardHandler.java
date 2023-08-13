package com.para_mada.maryvidad.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.para_mada.maryvidad.networking.NetworkManager;

public class KeyboardHandler {
    public static final String KEY_CATEGORY_MARYVIDAD = "key.category.maryvidad";
    public static final String KEY_FIRST_SKILL = "key.mary-mod.Q";
    public static final String KEY_SECOND_SKILL = "key.mary-mod.E";
    public static final String KEY_THIRD_SKILL = "key.mary-mod.R";
    public static final String KEY_RADAR_SKILL = "key.mary-mod.radar";

    public static KeyBinding firstSkillKey;
    public static KeyBinding secondSkillKey;
    public static KeyBinding radarSkillKey;

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(firstSkillKey.wasPressed()) {
                ClientPlayNetworking.send(NetworkManager.FIRST_SKILL_ID, PacketByteBufs.create());
            }
            if(secondSkillKey.wasPressed()) {
                ClientPlayNetworking.send(NetworkManager.SECOND_SKILL_ID, PacketByteBufs.create());
            }
            if(radarSkillKey.wasPressed()) {
                ClientPlayNetworking.send(NetworkManager.RADAR_SKILL_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        firstSkillKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FIRST_SKILL,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Q,
                KEY_CATEGORY_MARYVIDAD
        ));
        secondSkillKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SECOND_SKILL,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_E,
                KEY_CATEGORY_MARYVIDAD
        ));
        radarSkillKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_RADAR_SKILL,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY_MARYVIDAD
        ));

        registerKeyInputs();
    }
}