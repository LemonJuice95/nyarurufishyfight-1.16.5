package io.lemonjuice.nyaruru.client.model.entity.monster;

// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.lemonjuice.nyaruru.entities.monster.ForestMonster01Entity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ForestMst01Model extends EntityModel<ForestMonster01Entity> {
	private final ModelRenderer body;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer t1;
	private final ModelRenderer t2;
	private final ModelRenderer t3;
	private final ModelRenderer t4;
	private final ModelRenderer t5;
	private final ModelRenderer t6;
	private final ModelRenderer t7;
	private final ModelRenderer t8;

	public ForestMst01Model() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 0).addBox(-3.5F, -8.0F, -5.0F, 7.0F, 5.0F, 9.0F, 0.0F, false);
		body.texOffs(33, 0).addBox(-4.5F, -8.0F, -4.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
		body.texOffs(0, 24).addBox(3.5F, -8.0F, -4.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
		body.texOffs(0, 24).addBox(-3.5F, -9.0F, -4.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);
		body.texOffs(0, 24).addBox(-3.5F, -3.0F, -4.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);

		leftEar = new ModelRenderer(this);
		leftEar.setPos(-2.75F, -8.5F, -3.25F);
		body.addChild(leftEar);
		setRotationAngle(leftEar, -0.0436F, 0.3491F, 0.0F);
		leftEar.texOffs(0, 24).addBox(-1.4698F, -1.4925F, -1.1708F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		leftEar.texOffs(0, 39).addBox(-0.4698F, -1.4925F, -1.1708F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		leftEar.texOffs(0, 24).addBox(0.5302F, -1.4925F, -1.1708F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		leftEar.texOffs(0, 24).addBox(-0.4698F, -2.4925F, -1.1708F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		rightEar = new ModelRenderer(this);
		rightEar.setPos(3.0F, -8.5F, -3.75F);
		body.addChild(rightEar);
		setRotationAngle(rightEar, -0.05F, -0.61F, 0.0436F);
		rightEar.texOffs(0, 24).addBox(-1.4094F, -1.4925F, -0.7131F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rightEar.texOffs(0, 39).addBox(-0.4094F, -1.4925F, -0.7131F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rightEar.texOffs(0, 24).addBox(0.5906F, -1.4925F, -0.7131F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		rightEar.texOffs(0, 24).addBox(-0.4094F, -2.4925F, -0.7131F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		t1 = new ModelRenderer(this);
		t1.setPos(-1.5F, 22.0F, -3.5F);
		t1.texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t2 = new ModelRenderer(this);
		t2.setPos(-3.0F, 22.0F, -2.0F);
		t2.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t3 = new ModelRenderer(this);
		t3.setPos(-3.0F, 22.0F, 1.0F);
		t3.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t4 = new ModelRenderer(this);
		t4.setPos(-1.5F, 22.0F, 2.5F);
		t4.texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t5 = new ModelRenderer(this);
		t5.setPos(1.5F, 22.0F, 2.5F);
		t5.texOffs(0, 0).addBox(1.5F, 0.0F, -5.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t6 = new ModelRenderer(this);
		t6.setPos(1.5F, 22.0F, -3.5F);
		t6.texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t7 = new ModelRenderer(this);
		t7.setPos(1.5F, 22.0F, 2.5F);
		t7.texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t8 = new ModelRenderer(this);
		t8.setPos(-1.5F, 22.0F, 2.5F);
		t8.texOffs(0, 0).addBox(4.5F, 0.0F, -2.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ForestMonster01Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.t1.xRot = this.t3.xRot = this.t5.xRot = this.t7.xRot = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.t2.xRot = this.t4.xRot = this.t6.xRot = this.t8.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		t1.render(matrixStack, buffer, packedLight, packedOverlay);
		t2.render(matrixStack, buffer, packedLight, packedOverlay);
		t3.render(matrixStack, buffer, packedLight, packedOverlay);
		t4.render(matrixStack, buffer, packedLight, packedOverlay);
		t5.render(matrixStack, buffer, packedLight, packedOverlay);
		t6.render(matrixStack, buffer, packedLight, packedOverlay);
		t7.render(matrixStack, buffer, packedLight, packedOverlay);
		t8.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}