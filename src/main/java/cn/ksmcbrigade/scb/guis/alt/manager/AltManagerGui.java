package cn.ksmcbrigade.scb.guis.alt.manager;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.alt.Alt;
import cn.ksmcbrigade.scb.alt.AltManager;
import cn.ksmcbrigade.scb.guis.alt.edit.EditAltScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class AltManagerGui extends OptionsSubScreen {

    public AltList altList;
    public final Screen last;

    public AltManagerGui(Screen p_345104_) {
        super(p_345104_, Minecraft.getInstance().options,Component.literal("AltManager"));
        this.minecraft = Minecraft.getInstance();
        this.last = p_345104_;
    }

    @Override
    protected void addFooter() {
        LinearLayout linearlayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearlayout.addChild(Button.builder(Component.literal("Add"), p_345997_ -> Minecraft.getInstance().setScreen(new EditAltScreen(new AltManagerGui(this.lastScreen),new Alt("", UUID.randomUUID(),"0"), AltManager.Action.ADD))).build());
        linearlayout.addChild(Button.builder(CommonComponents.GUI_DONE, p_345169_ -> this.onClose()).build());
        linearlayout.addChild(Button.builder(Component.literal("Add10RandomAlts"), p_345169_ -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                try {
                    SimpleClientBase.altManager.add(new Alt(RandomStringUtils.randomAlphanumeric(random.nextInt(4,16)),UUID.randomUUID(),"0"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Minecraft.getInstance().setScreen(new AltManagerGui(this.last));
        }).build());
    }

    @Override
    protected void addContents() {
        this.altList = this.layout.addToContents(new AltList(this,Minecraft.getInstance()));
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        this.altList.updateSize(this.width,this.layout);
    }

    @Override
    protected void addOptions() {
    }

    @Override
    protected void init() {
        super.init();
        for (AltList.AltEntry child : this.altList.children()) {
            child.refreshEntry();
        }
    }
}
