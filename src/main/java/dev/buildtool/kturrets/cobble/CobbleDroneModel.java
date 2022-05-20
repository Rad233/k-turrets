package dev.buildtool.kturrets.cobble;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.buildtool.kturrets.KTurrets;
import dev.buildtool.satako.Functions;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CobbleDroneModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(KTurrets.ID, "cobble_drone"), "main");
    private final ModelPart rotor1;
    private final ModelPart rotor2;
    private final ModelPart rotor3;
    private final ModelPart rotor4;
    private final ModelPart rotating;
    private final ModelPart bb_main;
    private final ModelPart muzzle;

    public CobbleDroneModel(ModelPart root) {
        this.rotor1 = root.getChild("rotor1");
        this.rotor2 = root.getChild("rotor2");
        this.rotor3 = root.getChild("rotor3");
        this.rotor4 = root.getChild("rotor4");
        this.rotating = root.getChild("rotating");
        this.bb_main = root.getChild("bb_main");
        muzzle = rotating.getChild("muzzle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rotor1 = partdefinition.addOrReplaceChild("rotor1", CubeListBuilder.create().texOffs(27, 29).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 10.5F, 5.5F));

        PartDefinition rotor2 = partdefinition.addOrReplaceChild("rotor2", CubeListBuilder.create().texOffs(18, 29).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 10.5F, -5.5F));

        PartDefinition rotor3 = partdefinition.addOrReplaceChild("rotor3", CubeListBuilder.create().texOffs(9, 29).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 10.5F, -5.5F));

        PartDefinition rotor4 = partdefinition.addOrReplaceChild("rotor4", CubeListBuilder.create().texOffs(0, 29).addBox(-0.5F, 2.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 10.5F, 5.5F));

        PartDefinition rotating = partdefinition.addOrReplaceChild("rotating", CubeListBuilder.create().texOffs(28, 10).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition muzzle = rotating.addOrReplaceChild("muzzle", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.25F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 12).addBox(5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -10.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(23, 26).addBox(-5.0F, -10.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-5.0F, -10.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(5.0F, -9.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(21, 22).addBox(-5.0F, -8.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -9.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 0).addBox(5.0F, -2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(14, 0).addBox(-6.0F, -2.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        rotating.yRot = Functions.getDefaultHeadYaw(netHeadYaw);
        muzzle.xRot = Functions.getDefaultHeadPitch(headPitch);
        rotor1.yRot = ageInTicks * 1.5f;
        rotor2.yRot = -ageInTicks * 1.5f;
        rotor3.yRot = ageInTicks * 1.5f;
        rotor4.yRot = -ageInTicks * 1.5f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rotor1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotating.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}