package com.nyaruru.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.item.ItemModelsProperties;

public class ClientProxy extends CommonProxy{
    public static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void init() {
    };

    @Override
    public void postInit() {
        this.addLayersForRender();
    };

    @Override
    public void setUpClient() {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addLayersForRender() {
    }
}
