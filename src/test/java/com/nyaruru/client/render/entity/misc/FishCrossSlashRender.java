package com.nyaruru.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nyaruru.client.model.entity.misc.FishCrossSlashModel;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import com.nyaruru.utils.StringUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class FishCrossSlashRender extends EntityRenderer<FishCrossSlashEntity> {
    public static final ResourceLocation RES_SLASH = StringUtil.resPrefix("textures/entity/fish_cross_slash.png");

    private EntityModel<FishCrossSlashEntity> model;

    public FishCrossSlashRender(EntityRendererManager p_i48828_1_) {
        super(p_i48828_1_);
        model = new FishCrossSlashModel();
    }

    @Override
    public void render(FishCrossSlashEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
        p_225623_4_.pushPose();
        p_225623_4_.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(p_225623_3_, p_225623_1_.yRotO, p_225623_1_.yRot) -
                90.0F));
        p_225623_4_.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(p_225623_3_, p_225623_1_.xRotO, p_225623_1_.xRot) + 180.0F));
        IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getFoilBuffer(p_225623_5_, this.model.renderType(this.getTextureLocation(p_225623_1_)), false, false);
        this.model.renderToBuffer(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        p_225623_4_.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(FishCrossSlashEntity p_110775_1_) {
        return RES_SLASH;
    }

    public static class RenderFactory implements IRenderFactory<FishCrossSlashEntity> {
        @Override
        public EntityRenderer<? super FishCrossSlashEntity> createRenderFor(EntityRendererManager manager){
            manager.setRenderShadow(false);
            return new FishCrossSlashRender(manager);
        }
    }
}
