package com.nyaruru.client.model.entity.misc;

// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.nyaruru.entities.misc.FishCrossSlashEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FishCrossSlashModel extends EntityModel<FishCrossSlashEntity> {
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer back;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;

	public FishCrossSlashModel() {
		texWidth = 64;
		texHeight = 64;

		left = new ModelRenderer(this);
		left.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(left, -0.6109F, 3.1416F, 0.0F);
		left.texOffs(0, 0).addBox(-10.0F, -33.032F, -18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		left.texOffs(2, 0).addBox(-9.0F, -34.532F, -18.928F, 1.0F, 15.0F, 0.0F, 0.0F, false);
		left.texOffs(4, 0).addBox(-8.0F, -36.032F, -18.928F, 1.0F, 18.0F, 0.0F, 0.0F, false);
		left.texOffs(6, 0).addBox(-7.0F, -37.032F, -18.928F, 1.0F, 20.0F, 0.0F, 0.0F, false);
		left.texOffs(0, 14).addBox(-6.0F, -38.032F, -18.928F, 1.0F, 22.0F, 0.0F, 0.0F, false);
		left.texOffs(8, 0).addBox(-4.0F, -39.032F, -18.928F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		left.texOffs(9, 0).addBox(-4.0F, -27.032F, -18.928F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		left.texOffs(9, 0).addBox(-3.0F, -25.332F, -18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		left.texOffs(9, 1).addBox(-3.0F, -39.732F, -18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		left.texOffs(9, 1).addBox(-2.0F, -40.332F, -18.928F, 1.0F, 10.0F, 0.0F, 0.0F, false);
		left.texOffs(9, 1).addBox(-2.0F, -23.732F, -18.928F, 1.0F, 10.0F, 0.0F, 0.0F, false);
		left.texOffs(12, 0).addBox(-1.0F, -22.632F, -18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		left.texOffs(12, 0).addBox(-1.0F, -40.632F, -18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		left.texOffs(12, 0).addBox(0.0F, -40.632F, -18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		left.texOffs(12, 0).addBox(0.0F, -22.632F, -18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		left.texOffs(2, 17).addBox(1.0F, -40.632F, -18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		left.texOffs(2, 17).addBox(1.0F, -21.632F, -18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		left.texOffs(14, 0).addBox(2.0F, -21.632F, -18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		left.texOffs(14, 0).addBox(2.0F, -40.632F, -18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		left.texOffs(16, 0).addBox(3.0F, -20.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(16, 0).addBox(3.0F, -20.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(16, 0).addBox(3.0F, -40.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(18, 0).addBox(4.0F, -20.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(18, 0).addBox(4.0F, -40.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(0, 36).addBox(-5.0F, -38.532F, -18.928F, 1.0F, 23.0F, 0.0F, 0.0F, false);
		left.texOffs(18, 0).addBox(4.0F, -40.632F, -18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(5.0F, -40.132F, -18.928F, 1.0F, 6.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(5.0F, -20.132F, -18.928F, 1.0F, 6.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(6.0F, -19.632F, -18.928F, 1.0F, 5.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(7.0F, -39.132F, -18.928F, 1.0F, 4.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(8.0F, -18.632F, -18.928F, 1.0F, 3.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(8.0F, -38.632F, -18.928F, 1.0F, 3.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(7.0F, -19.132F, -18.928F, 1.0F, 4.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(9.0F, -38.132F, -18.928F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(9.0F, -18.132F, -18.928F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		left.texOffs(20, 0).addBox(6.0F, -39.632F, -18.928F, 1.0F, 5.0F, 0.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(right, 0.6109F, 3.1416F, 0.0F);
		right.texOffs(0, 0).addBox(-10.0F, -33.032F, 18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		right.texOffs(2, 0).addBox(-9.0F, -34.532F, 18.928F, 1.0F, 15.0F, 0.0F, 0.0F, false);
		right.texOffs(4, 0).addBox(-8.0F, -36.032F, 18.928F, 1.0F, 18.0F, 0.0F, 0.0F, false);
		right.texOffs(6, 0).addBox(-7.0F, -37.032F, 18.928F, 1.0F, 20.0F, 0.0F, 0.0F, false);
		right.texOffs(0, 14).addBox(-6.0F, -38.032F, 18.928F, 1.0F, 22.0F, 0.0F, 0.0F, false);
		right.texOffs(8, 0).addBox(-4.0F, -39.032F, 18.928F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		right.texOffs(9, 0).addBox(-4.0F, -27.032F, 18.928F, 1.0F, 12.0F, 0.0F, 0.0F, false);
		right.texOffs(9, 0).addBox(-3.0F, -25.332F, 18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		right.texOffs(9, 1).addBox(-3.0F, -39.732F, 18.928F, 1.0F, 11.0F, 0.0F, 0.0F, false);
		right.texOffs(9, 1).addBox(-2.0F, -40.332F, 18.928F, 1.0F, 10.0F, 0.0F, 0.0F, false);
		right.texOffs(9, 1).addBox(-2.0F, -23.732F, 18.928F, 1.0F, 10.0F, 0.0F, 0.0F, false);
		right.texOffs(12, 0).addBox(-1.0F, -22.632F, 18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		right.texOffs(12, 0).addBox(-1.0F, -40.632F, 18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		right.texOffs(12, 0).addBox(0.0F, -40.632F, 18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		right.texOffs(12, 0).addBox(0.0F, -22.632F, 18.928F, 1.0F, 9.0F, 0.0F, 0.0F, false);
		right.texOffs(2, 17).addBox(1.0F, -40.632F, 18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		right.texOffs(2, 17).addBox(1.0F, -21.632F, 18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		right.texOffs(14, 0).addBox(2.0F, -21.632F, 18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		right.texOffs(14, 0).addBox(2.0F, -40.632F, 18.928F, 1.0F, 8.0F, 0.0F, 0.0F, false);
		right.texOffs(16, 0).addBox(3.0F, -20.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(16, 0).addBox(3.0F, -20.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(16, 0).addBox(3.0F, -40.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(18, 0).addBox(4.0F, -20.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(18, 0).addBox(4.0F, -40.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(0, 36).addBox(-5.0F, -38.532F, 18.928F, 1.0F, 23.0F, 0.0F, 0.0F, false);
		right.texOffs(18, 0).addBox(4.0F, -40.632F, 18.928F, 1.0F, 7.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(5.0F, -40.132F, 18.928F, 1.0F, 6.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(5.0F, -20.132F, 18.928F, 1.0F, 6.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(6.0F, -19.632F, 18.928F, 1.0F, 5.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(7.0F, -39.132F, 18.928F, 1.0F, 4.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(8.0F, -18.632F, 18.928F, 1.0F, 3.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(8.0F, -38.632F, 18.928F, 1.0F, 3.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(7.0F, -19.132F, 18.928F, 1.0F, 4.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(9.0F, -38.132F, 18.928F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(9.0F, -18.132F, 18.928F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		right.texOffs(20, 0).addBox(6.0F, -39.632F, 18.928F, 1.0F, 5.0F, 0.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(back, 0.0F, 3.1416F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		back.addChild(cube_r1);
		setRotationAngle(cube_r1, 2.1817F, 0.0F, 0.0F);
		cube_r1.texOffs(14, 0).addBox(-3.0F, 18.4263F, 14.6765F, 0.0F, 1.0F, 25.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		back.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.9599F, 0.0F, 0.0F);
		cube_r2.texOffs(14, 0).addBox(-3.0F, -19.6187F, 15.0213F, 0.0F, 1.0F, 25.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(FishCrossSlashEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		left.render(matrixStack, buffer, packedLight, packedOverlay);
		right.render(matrixStack, buffer, packedLight, packedOverlay);
		back.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}