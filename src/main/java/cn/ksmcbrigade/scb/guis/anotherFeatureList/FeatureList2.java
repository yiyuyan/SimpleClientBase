package cn.ksmcbrigade.scb.guis.anotherFeatureList;

import cn.ksmcbrigade.scb.config.HUD02Config;
import cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets.SearchBox;
import cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets.TypeList;
import cn.ksmcbrigade.scb.module.Groups;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeatureList2 extends Screen {

    private final ArrayList<TypeList> lists = new ArrayList<>();
    private SearchBox searchBox;

    public FeatureList2() {
        super(Component.literal("FeatureList"));
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        if(this.minecraft==null){
            this.minecraft = Minecraft.getInstance();
        }
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP1_TITLE.get(),2,2,50,25,HUD02Config.GROUP1_BACKGROUND_COLOR.get(), HUD02Config.GROUP1_CUR_BACKGROUND_COLOR.get(),HUD02Config.MODULE_ENABLED_COLOR.get(), Groups.COMBAT)));
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP2_TITLE.get(),2+2+50,2,50,25, HUD02Config.GROUP2_BACKGROUND_COLOR.get(),HUD02Config.GROUP2_CUR_BACKGROUND_COLOR.get(), HUD02Config.MODULE_ENABLED_COLOR.get(),Groups.ITEM)));
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP3_TITLE.get(),2+2+50+2+50,2,50,25, HUD02Config.GROUP3_BACKGROUND_COLOR.get(),HUD02Config.GROUP3_CUR_BACKGROUND_COLOR.get(), HUD02Config.MODULE_ENABLED_COLOR.get(),Groups.BLOCK)));
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP4_TITLE.get(),2+2+50+2+50+2+50,2,50,25,HUD02Config.GROUP4_BACKGROUND_COLOR.get(), HUD02Config.GROUP4_CUR_BACKGROUND_COLOR.get(), HUD02Config.MODULE_ENABLED_COLOR.get(),Groups.RENDER)));
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP5_TITLE.get(),2+2+50+2+50+2+50+2+50,2,50,25,HUD02Config.GROUP5_BACKGROUND_COLOR.get(), HUD02Config.GROUP5_CUR_BACKGROUND_COLOR.get(), HUD02Config.MODULE_ENABLED_COLOR.get(),Groups.OVERLAY)));
        lists.add(this.addRenderableWidget(new TypeList(this,HUD02Config.GROUP6_TITLE.get(),2+2+50+2+50+2+50+2+50+2+50,2,50,25, HUD02Config.GROUP6_BACKGROUND_COLOR.get(),HUD02Config.GROUP6_CUR_BACKGROUND_COLOR.get(),HUD02Config.MODULE_ENABLED_COLOR.get(), Groups.MISC)));

        this.searchBox = this.addRenderableWidget(new SearchBox(2+2+50+2+50+2+50+2+50+2+50+75,10,50,12,Component.empty(),-15693574,5,5,this));
    }

    @Override
    public void render(@NotNull GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        super.render(p_281549_, p_281550_, p_282878_, p_282465_);
    }

    @Override
    public <T extends GuiEventListener & NarratableEntry> @NotNull T addWidget(@NotNull T p_96625_) {
        return super.addWidget(p_96625_);
    }

    @Override
    public void removeWidget(@NotNull GuiEventListener p_169412_) {
        super.removeWidget(p_169412_);
    }
}
