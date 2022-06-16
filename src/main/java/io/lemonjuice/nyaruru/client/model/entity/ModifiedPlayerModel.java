package io.lemonjuice.nyaruru.client.model.entity;

import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModifiedPlayerModel<T extends LivingEntity> extends PlayerModel<T> {
    public static ModelRenderer cube_r1;
    public static ModelRenderer cube_r2;
    public static ModelRenderer cube_r3;
    public static ModelRenderer cube_r4;
    public static ModelRenderer tail1;
    public static ModelRenderer tail2;

    public ModifiedPlayerModel(float p_i46304_1_, boolean p_i46304_2_) {
        super(p_i46304_1_, p_i46304_2_);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(2.75F, -7.25F, -2.25F);
        head.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.255F, -0.525F, 0.013F);
        cube_r1.texOffs(8, 7).addBox(-2.0F, -1.25F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-1.0F, -1.25F, 0.02F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-1.0F, -2.25F, 0.02F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-2.0F, -2.25F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(0.0F, -1.25F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(0.0F, -2.25F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-1.5F, -2.75F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-0.5F, -2.75F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(8, 7).addBox(-1.0F, -3.25F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.texOffs(10, 13).addBox(-1.0F, -2.25F, -0.99F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-4.0F, -7.75F, -2.75F);
        head.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.2706F, 0.3378F, -0.1213F);
        cube_r2.texOffs(8, 7).addBox(0.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(1.0F, -0.5F, 0.52F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(1.0F, -1.5F, 0.52F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(0.0F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(2.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(2.0F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(0.5F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(1.5F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(8, 7).addBox(1.0F, -2.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r2.texOffs(10, 13).addBox(1.0F, -1.75F, -0.49F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        tail1 = new ModelRenderer(this);
        tail1.setPos(0.0F, 12.0F, 1.75F);
        body.addChild(tail1);


        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(0.0F, 0.5F, 0.25F);
        tail1.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.829F, 0.0F, 0.0F);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 5.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r3.texOffs(8, 7).addBox(-0.75F, -0.5F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setPos(0.0F, 5.25F, 4.25F);
        tail1.addChild(tail2);


        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.0F, 0.0F, -0.25F);
        tail2.addChild(cube_r4);
        setRotationAngle(cube_r4, -1.0036F, 0.0F, 0.0F);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -10.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -9.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -8.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -7.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -6.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -5.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -4.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -3.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -2.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r4.texOffs(8, 7).addBox(-0.75F, -1.2072F, -0.4609F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        this.tail1.xRot = -0.4F * MathHelper.cos(p_225597_2_) * p_225597_3_;
        this.tail2.xRot = 0.21286163F + 0.4F * MathHelper.cos(p_225597_2_) * p_225597_3_;
    }
}
