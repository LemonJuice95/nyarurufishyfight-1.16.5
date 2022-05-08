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
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishISTER extends ItemStackTileEntityRenderer {
    private final FishModel model = new FishModel();

    @Override
    public void renderByItem(ItemStack p_239207_1_, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack p_239207_3_, IRenderTypeBuffer p_239207_4_, int p_239207_5_, int p_239207_6_) {
        p_239207_3_.pushPose();
        p_239207_3_.scale(1.5F, 0.8F, 0.5F);
        p_239207_3_.mulPose(Vector3f.XN.rotationDegrees(-90));
        p_239207_3_.mulPose(Vector3f.YN.rotationDegrees(-90));
        p_239207_3_.translate(0.8, 0.15, 0.35);
        IVertexBuilder vertexBuilder = ItemRenderer.getFoilBufferDirect(p_239207_4_, this.model.renderType(FishModel.TEXTURE), false, p_239207_1_.hasFoil());
        this.model.renderToBuffer(p_239207_3_, vertexBuilder, p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
        p_239207_3_.popPose();
    }
}