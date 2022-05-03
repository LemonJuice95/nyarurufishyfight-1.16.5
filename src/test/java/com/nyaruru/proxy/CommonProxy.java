package com.nyaruru.proxy;

import net.minecraft.entity.player.PlayerEntity;

public class CommonProxy {

    public void init() {
    }

    public void postInit() {

    }

    public void setUp() {
    }

    public void setUpClient() {

    }

    /**
     * climp up on client side.
     */
    public void climbUp() {
    }

    public PlayerEntity getPlayer(){
        return ClientProxy.MC.player;
    }
}
