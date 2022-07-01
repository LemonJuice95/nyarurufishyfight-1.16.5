package io.lemonjuice.nyaruru.client.render.entity.monster;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.lemonjuice.nyaruru.client.model.entity.monster.ForestMst01Model;
import io.lemonjuice.nyaruru.client.render.entity.misc.FishCrossSlashRender;
import io.lemonjuice.nyaruru.entities.misc.FishCrossSlashEntity;
import io.lemonjuice.nyaruru.entities.monster.ForestMonster01Entity;
import io.lemonjuice.nyaruru.utils.StringUtil;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ForestMst01Render extends LivingRenderer<ForestMonster01Entity, ForestMst01Model> {
    public static final ResourceLocation RES_FOREST_MST_01 = StringUtil.resPrefix("textures/entity/forest_mst_01.png");

    public ForestMst01Render(EntityRendererManager manager) {
        super(manager, new ForestMst01Model(), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(ForestMonster01Entity entity) {
        return RES_FOREST_MST_01;
    }

    @Override
    protected boolean shouldShowName(ForestMonster01Entity p_177070_1_) {
        return false;
    }

    public static class RenderFactory implements IRenderFactory<ForestMonster01Entity> {
        @Override
        public EntityRenderer<? super ForestMonster01Entity> createRenderFor(EntityRendererManager manager){
            manager.setRenderShadow(false);
            return new ForestMst01Render(manager);
        }
    }
}
