package com.nyaruru.client.model.itemstack;

// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nyaruru.utils.StringUtil;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class FishModel extends EntityModel<Entity> {
	public static final ResourceLocation TEXTURE = StringUtil.resPrefix("textures/item/fish.png");
	private final ModelRenderer bone;

	public FishModel() {
		texWidth = 64;
		texHeight = 64;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 11).addBox(-10.0F, -10.0F, -1.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 12).addBox(-9.0F, -9.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 12).addBox(-8.0F, -8.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 12).addBox(-7.0F, -7.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 11).addBox(-6.0F, -7.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone.texOffs(4, 11).addBox(-5.0F, -8.0F, -1.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(4, 12).addBox(-3.0F, -9.0F, -1.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		bone.texOffs(4, 11).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 9.0F, 1.0F, 0.0F, false);
		bone.texOffs(4, 12).addBox(1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 1.0F, 0.0F, false);
		bone.texOffs(4, 11).addBox(3.0F, -12.0F, -1.0F, 2.0F, 13.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 12).addBox(5.0F, -11.0F, -1.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone.texOffs(10, 12).addBox(6.0F, -10.0F, -1.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 13).addBox(7.0F, -9.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone.texOffs(15, 13).addBox(8.0F, -8.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 13).addBox(9.0F, -7.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 14).addBox(10.0F, -6.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}