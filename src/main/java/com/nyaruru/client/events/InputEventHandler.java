package com.nyaruru.client.events;

import net.minecraft.client.GameSettings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputEventHandler {
    @SubscribeEvent
    public void onKeyboardInput(InputEvent.KeyInputEvent ev) {
        if(GameSettings.keyJump)
    }
}