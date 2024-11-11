package cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets;

import cn.ksmcbrigade.scb.guis.configuration.configurationScreen;
import cn.ksmcbrigade.scb.module.Module;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class FeatureConfigRenderer extends AbstractButton {

    public Screen last;
    public Module module;

    public FeatureConfigRenderer(Screen screen,int x, int y, Module module) {
        super(x,y,Minecraft.getInstance().font.width(">")*2,9, Component.nullToEmpty(">"));
        this.last = screen;
        this.module = module;
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics p_281670_, int p_282682_, int p_281714_, float p_282542_) {
        Minecraft minecraft = Minecraft.getInstance();
        p_281670_.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        int i = getFGColor();
        this.renderString(p_281670_, minecraft.font,i | Mth.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    public void onPress() {
        Minecraft.getInstance().setScreen(new configurationScreen(this.last,module));
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput p_259858_) {

    }
}
