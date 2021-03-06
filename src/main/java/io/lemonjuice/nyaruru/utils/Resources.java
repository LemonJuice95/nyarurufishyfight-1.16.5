package io.lemonjuice.nyaruru.utils;

import io.lemonjuice.nyaruru.capability.player.PlayerDataManager;
import net.minecraft.util.text.TranslationTextComponent;

public enum Resources {
    MONEY(0,9999999),
    SP(0,200),
    POWER(0,100),

    ADDING_MONEY(0,9999999),
    ADDING_MONEY2(0,9999999),
    ADDING_POWER(-100,100),

    SPRINT_TICKS(0, 10),
    SPRINT_TIMES(0,100),
    DRAW_FISH_CHOPPING_TICKS(0, 10),
    FLYING_FLAG(0,1),
    CAT_GAZE_FLAG(0,1),
    GAZE_OVERLAY_SCALE(0, 10),
    GAZE_TICK(0, 10),
    DOUBLE_SLASH_TICK(0, 10),
    SHIELD_TICK(0, 1200),
    SPRINT_UP_TIMES(0, 5),
    SPRINT_UP_TICKS(0, 10),
    SPRINT_UP_SWITCH(0, 1),

    FIRST_GET_INTO_THE_WORLD(0,1),
    RENDER_AS_CAT(0, 1),
    RENDER_HUD(0, 1),

    HAS_DAGGER(0,1),
    HAS_POTION(0, 1),
    RED_JADE_FISH_EYE(0, 1),
    AQUAMARINE_FISH_EYE(0,1),
    FLUORITE_FISH_EYE(0, 1),
    HAS_SHIELD(0, 1),
    HAS_BOWKNOT(0, 1);

    public static final int INF = 9999999;
    public final int min;
    public final int max;

    /**
     * {@link PlayerDataManager#PlayerDataManager(net.minecraft.entity.player.PlayerEntity)}
     */
    public static int getInitialValue(Resources res) {
        if(res == ADDING_POWER) {
            return 0;
        }
        return res.min;
    }

    private Resources(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public TranslationTextComponent getText() {
        return new TranslationTextComponent("resource.nyaruru." + this.toString().toLowerCase());
    }
}
