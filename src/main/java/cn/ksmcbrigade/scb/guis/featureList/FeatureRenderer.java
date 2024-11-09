package cn.ksmcbrigade.scb.guis.featureList;

import cn.ksmcbrigade.scb.module.Module;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class FeatureRenderer {

    public final int x;
    public final int y;
    public final String width;
    public final int height;
    public int backgroundColor;
    public int curBackgroundColor;
    public int textColor;
    public int enabledColor;
    public final String title;

    public final Module feature;

    public boolean cur = false;

    public FeatureRenderer(int x, int y, String width, int height, int backgroundColor, int curBackgroundColor, int textColor, int enabledTextColor, String title, Module feature){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.curBackgroundColor = curBackgroundColor;
        this.textColor = textColor;
        this.enabledColor = enabledTextColor;
        this.title = title;

        this.feature = feature;
    }

    public void render(GuiGraphics context, Font renderer){
        context.fillGradient(x,y,x+renderer.width(width),y+height,cur?curBackgroundColor:backgroundColor,cur?curBackgroundColor:backgroundColor);
        context.drawString(renderer,title,x,y+2, feature.enabled?enabledColor:textColor);
    }
}
