package cn.ksmcbrigade.scb.guis.featureList;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;

public class FeatureListRenderer {

    public final int x;
    public final int y;
    public final String widthS;
    public boolean doneWidth = false;
    public int width = -1;
    public final int height;
    public int backgroundColor;
    public int curBackgroundColor;
    public int textColor;
    public int enabledColor;

    public final Group group;

    public ArrayList<FeatureRenderer> featureRenderers = new ArrayList<>();
    public FeatureRenderer cur;

    public final int min = 0;
    public int max;

    public int done = 0;

    public FeatureListRenderer(int x, int y, int height, int backgroundColor, int curBackgroundColor, int textColor, int enabledTextColor, Group group){
        this.group = group;

        this.x = x;
        this.y = y;
        this.height = height;
        this.widthS = this.getWidth();
        this.backgroundColor = backgroundColor;
        this.curBackgroundColor = curBackgroundColor;
        this.textColor = textColor;
        this.enabledColor = enabledTextColor;

        done = 0;
        for (int i = 0; i < this.group.features.size(); i++) {
            Module feature;
            try {
                feature = this.group.features.get(i);
            } catch (Exception e) {
                SimpleClientBase.LOGGER.error("Error in load a module {}",i);
                continue;
            }
            int renderY = this.y+done*12;
            FeatureRenderer featureRenderer = new FeatureRenderer(this.x,renderY,this.widthS,height,this.group.renderer.backgroundColor,this.group.renderer.curBackgroundColor, this.textColor,this.enabledColor,feature.getName(), feature);
            featureRenderers.add(featureRenderer);
            if(done==0){
                cur(featureRenderer);
            }
            done++;
        }

        this.max = featureRenderers.size()-1;
    }

    public void render(GuiGraphics context, Font renderer){
        for (FeatureRenderer featureRenderer : featureRenderers) {
            featureRenderer.render(context, renderer);
            //System.out.println("???"+featureRenderer.feature.getName()+featureRenderer.x);
        }
    }

    public void cur(FeatureRenderer renderer){
        if(featureRenderers.contains(renderer)){
            FeatureRenderer featureRenderer = featureRenderers.get(featureRenderers.indexOf(renderer));
            featureRenderer.cur = !featureRenderer.cur;
            cur = featureRenderer;
        }
    }

    public void next(){
        int next = featureRenderers.indexOf(cur)+1;
        if(next>max){
            next = min;
        }
        if(next<0) next = 0;
        FeatureRenderer renderer = featureRenderers.get(next);
        this.cur = renderer;
        renderer.cur = true;

        for (int i = 0; i < featureRenderers.size(); i++) {
            if(i!=next){
                featureRenderers.get(i).cur = false;
            }
        }
    }

    public void last(){
        int next = featureRenderers.indexOf(cur)-1;
        if(next<min){
            next = max;
        }
        if(next<0) next = 0;
        //System.out.println(this.cur+":"+featureRenderers.size()+":"+this.group.renderer.title);
        FeatureRenderer renderer = featureRenderers.get(next);
        this.cur = renderer;
        renderer.cur = true;

        for (int i = 0; i < featureRenderers.size(); i++) {
            if(i!=next){
                featureRenderers.get(i).cur = false;
            }
        }
    }

    public String getWidth(){
        String ret = "";
        for (Module feature : this.group.features) {
            if(feature.getName().length()>ret.length()){
                ret = feature.getName();
            }
        }
        return ret;
    }
}
