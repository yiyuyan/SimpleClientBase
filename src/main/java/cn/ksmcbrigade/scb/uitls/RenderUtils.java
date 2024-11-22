package cn.ksmcbrigade.scb.uitls;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    
    public static void renderBlock(PoseStack poseStack, Matrix4f modelViewMatrix, Matrix4f projectionMatrix, BlockPos pos, float red, float green, float blue, float opacity, float size) {

        //System.out.println(pos);

        RenderSystem.depthMask(false);
        //RenderSystem.enableDepthTest();
        //RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        poseStack.pushPose();

        Vec3 playerPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.depthFunc(GL11.GL_ALWAYS);

        poseStack.mulPose(modelViewMatrix);
        poseStack.translate(-playerPos.x, -playerPos.y, -playerPos.z);

        final float x = (float) (pos.getX() - playerPos.x), y = (float) (pos.getY() - playerPos.y), z = (float) (pos.getZ() - playerPos.z);
        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.DEBUG_LINES,DefaultVertexFormat.POSITION_COLOR);

        buffer.addVertex(x, y + size, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + size, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + size, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + size, z).setColor(red, green, blue, opacity);

        // BOTTOM
        buffer.addVertex(x + size, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y, z).setColor(red, green, blue, opacity);

        // Edge 1
        buffer.addVertex(x + size, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z + size).setColor(red, green, blue, opacity);

        // Edge 2
        buffer.addVertex(x + size, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + size, y + size, z).setColor(red, green, blue, opacity);

        // Edge 3
        buffer.addVertex(x, y, z + size).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + size, z + size).setColor(red, green, blue, opacity);

        // Edge 4
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + size, z).setColor(red, green, blue, opacity);

        BufferUploader.drawWithShader(buffer.buildOrThrow());

        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    public static void renderPlayer(PoseStack poseStack, Matrix4f modelViewMatrix, Matrix4f projectionMatrix, Vec3 pos, Entity player, float red, float green, float blue, float opacity) {

        //System.out.println(pos);

        RenderSystem.depthMask(false);
        //RenderSystem.enableDepthTest();
        //RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        poseStack.pushPose();

        Vec3 playerPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.depthFunc(GL11.GL_ALWAYS);

        poseStack.mulPose(modelViewMatrix);
        poseStack.translate(-playerPos.x, -playerPos.y, -playerPos.z);

        final float x = (float) (pos.x - playerPos.x), y = (float) (pos.y - playerPos.y), z = (float) (pos.z - playerPos.z);
        final float width = player.getBbWidth();
        final float height = player.getBbHeight();
        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.DEBUG_LINES,DefaultVertexFormat.POSITION_COLOR);

        buffer.addVertex(x, y + height, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + height, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + height, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + height, z).setColor(red, green, blue, opacity);

        // BOTTOM
        buffer.addVertex(x + width, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y, z).setColor(red, green, blue, opacity);

        // Edge 1
        buffer.addVertex(x + width, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z + width).setColor(red, green, blue, opacity);

        // Edge 2
        buffer.addVertex(x + width, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x + width, y + height, z).setColor(red, green, blue, opacity);

        // Edge 3
        buffer.addVertex(x, y, z + width).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + height, z + width).setColor(red, green, blue, opacity);

        // Edge 4
        buffer.addVertex(x, y, z).setColor(red, green, blue, opacity);
        buffer.addVertex(x, y + height, z).setColor(red, green, blue, opacity);

        BufferUploader.drawWithShader(buffer.buildOrThrow());

        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
    }
}
