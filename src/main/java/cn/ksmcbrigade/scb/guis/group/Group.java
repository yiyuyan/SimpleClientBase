package cn.ksmcbrigade.scb.guis.group;

import cn.ksmcbrigade.scb.guis.featureList.FeatureList;
import cn.ksmcbrigade.scb.guis.featureList.FeatureRenderer;
import cn.ksmcbrigade.scb.module.Module;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;

public class Group {

    public final GroupRenderer renderer;
    public ArrayList<Module> features;
    public FeatureList featureList;

    public boolean cur = false;

    public Group(GroupRenderer renderer, ArrayList<Module> features){
        this.renderer = renderer;
        this.features = features;
        this.featureList = new FeatureList(this);
    }

    public void render(GuiGraphics context, Font textRenderer){
        this.renderer.render(context, textRenderer);
        //if(this.cur) System.out.println(this.renderer.title);
    }

    public void cur(boolean cur){
        this.cur = cur;
        this.renderer.cur = this.cur;
    }

    public void add(Module module){
        this.features.add(module);
        this.featureList = new FeatureList(this);
    }
}
