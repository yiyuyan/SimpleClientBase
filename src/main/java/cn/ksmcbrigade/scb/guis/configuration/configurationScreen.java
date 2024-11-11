package cn.ksmcbrigade.scb.guis.configuration;

import cn.ksmcbrigade.scb.guis.anotherFeatureList.FeatureList2;
import cn.ksmcbrigade.scb.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class configurationScreen extends OptionsSubScreen {

    public Module module;
    public ConfigList configList;

    @Override
    public boolean isPauseScreen() {
        return !(this.lastScreen instanceof FeatureList2);
    }

    public configurationScreen(Screen screen, Module module) {
        super(screen, Minecraft.getInstance().options,Component.literal(module.getName()+" Config"));
        this.module = module;
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    protected void addOptions() {

    }

    @Override
    protected void addContents() {
        this.configList = this.layout.addToContents(new ConfigList(this,Minecraft.getInstance(),this.module));
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        this.configList.updateSize(this.width,this.layout);
    }
}
