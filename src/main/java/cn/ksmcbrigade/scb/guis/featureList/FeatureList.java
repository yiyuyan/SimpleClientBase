package cn.ksmcbrigade.scb.guis.featureList;

import cn.ksmcbrigade.scb.config.HUDConfig;
import cn.ksmcbrigade.scb.guis.group.Group;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class FeatureList {

    public final Group group;
    public FeatureListRenderer renderer;

    public FeatureList(Group group){
        this.group = group;
        this.renderer = new FeatureListRenderer(group.renderer.x+group.renderer.width,group.renderer.y,group.renderer.height,group.renderer.backgroundColor,group.renderer.curBackgroundColor, HUDConfig.MODULE_DISABLED_COLOR.get(),HUDConfig.MODULE_ENABLED_COLOR.get(),group);
    }

    public void render(GuiGraphics context, Font textRenderer){
        this.renderer.render(context, textRenderer);
    }
}
