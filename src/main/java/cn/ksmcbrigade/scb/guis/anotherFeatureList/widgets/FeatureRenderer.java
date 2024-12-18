package cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets;

import cn.ksmcbrigade.scb.guis.anotherFeatureList.FeatureList2;
import cn.ksmcbrigade.scb.guis.configuration.configurationScreen;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.uitls.JNAUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;

public class FeatureRenderer extends AbstractButton {

    public final int color;
    public final int cur_color;
    public final int enabled_color;
    public final Group group;
    public Module module;

    public FeatureList2 instance;
    public FeatureConfigRenderer configRenderer;

    public FeatureRenderer(FeatureList2 instance, int x, int y, int width, int height, int color, int cur_color, int enabled_color, Module module, @NotNull Group group) {
        super(x,y,width,height, Component.nullToEmpty(module.name));
        this.color = color;
        this.cur_color = cur_color;
        this.enabled_color = enabled_color;
        this.module = module;
        this.group = group;
        this.instance = instance;
        this.configRenderer = this.instance.addWidget(new FeatureConfigRenderer(this.instance,this.getX()+getWidth()-Minecraft.getInstance().font.width(">")*2,(this.getY()+this.getHeight()/2),module));
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics p_281670_, int p_282682_, int p_281714_, float p_282542_) {
        Minecraft minecraft = Minecraft.getInstance();
        p_281670_.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        p_281670_.fillGradient(this.getX(),this.getY(),this.getX()+this.getWidth(),this.getY()+this.getHeight(),this.module.enabled?this.cur_color:this.color,this.module.enabled?this.cur_color:this.color);
        int i = getFGColor();
        this.renderString(p_281670_, minecraft.font,module.enabled?this.enabled_color:i | Mth.ceil(this.alpha * 255.0F) << 24);
        this.configRenderer.render(p_281670_, p_282682_, p_281714_, p_282542_);
    }

    @Override
    public void onPress() {

        if(JNAUtils.isPressed(KeyEvent.VK_SHIFT) && this.module.getConfig()!=null){
            Minecraft.getInstance().setScreen(new configurationScreen(this.instance,this.module));
            return;
        }

        try {
            this.module.setEnabled(!this.module.enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput p_259858_) {

    }
}
