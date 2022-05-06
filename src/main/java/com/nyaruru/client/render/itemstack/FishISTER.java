package com.nyaruru.client.render.itemstack;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nyaruru.client.model.itemstack.FishModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class FishISTER extends ItemStackTileEntityRenderer {
    private final FishModel model = new FishModel();

    public void renderByItem(ItemStack p_239207_1_, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack p_239207_3_, IRenderTypeBuffer p_239207_4_, int p_239207_5_, int p_239207_6_) {
        p_239207_3_.pushPose();
        IVertexBuilder vertexBuilder = ItemRenderer.getFoilBufferDirect(p_239207_4_, this.model.renderType(FishModel.TEXTURE), false, p_239207_1_.hasFoil());
        this.model.renderToBuffer(p_239207_3_, vertexBuilder, p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
        p_239207_3_.popPose();
    }
}