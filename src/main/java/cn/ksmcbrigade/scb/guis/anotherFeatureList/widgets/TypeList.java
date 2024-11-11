package cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.guis.anotherFeatureList.FeatureList2;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.module.Module;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TypeList extends AbstractButton {

    public final int color;
    public final int cur_color;
    public final int enabled_color;
    public final Group group;

    public final ArrayList<FeatureRenderer> renderers = new ArrayList<>();
    public boolean show = true;

    public TypeList(FeatureList2 instance,String title, int x, int y, int width, int height, int color, int cur_color,int enabled_color, @NotNull Group group) {
        super(x,y,width,height, Component.nullToEmpty(title));
        this.color = color;
        this.cur_color = cur_color;
        this.enabled_color = enabled_color;
        this.group = group;

        SimpleClientBase.checkGroupEmpty(this.group);
        for (int i1 = 0; i1 < this.group.featureList.renderer.done; i1++) {
            Module module = this.group.featureList.renderer.featureRenderers.get(i1).feature;
            FeatureRenderer renderer = instance.addWidget(new FeatureRenderer(instance,this.getX(),this.getY()+this.getHeight()+(this.getWidth()/2)*i1,this.getWidth(),(this.getWidth()/2),this.color,this.cur_color,this.enabled_color,module,this.group));
            this.renderers.add(renderer);
        }
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics p_281670_, int p_282682_, int p_281714_, float p_282542_) {
        Minecraft minecraft = Minecraft.getInstance();
        p_281670_.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        p_281670_.fillGradient(this.getX(),this.getY(),this.getX()+this.getWidth(),this.getY()+this.getHeight(),this.color,this.color);
        int i = getFGColor();
        this.renderString(p_281670_, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);

        if(this.show){
            for (FeatureRenderer renderer : this.renderers) {
                renderer.render(p_281670_, p_282682_, p_281714_, p_282542_);
            }
        }
    }

    @Override
    public void onPress() {
        this.show = !show;
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput p_259858_) {

    }
}
