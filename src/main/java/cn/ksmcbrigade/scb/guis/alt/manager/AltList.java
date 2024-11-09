package cn.ksmcbrigade.scb.guis.alt.manager;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.alt.Alt;
import cn.ksmcbrigade.scb.alt.AltManager;
import cn.ksmcbrigade.scb.guis.alt.edit.EditAltScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class AltList extends ContainerObjectSelectionList<AltList.AltEntry> {

    public final AltManagerGui instance;

    public AltList(AltManagerGui altManagerGui, Minecraft MC) {
        super(MC, altManagerGui.width,altManagerGui.layout.getContentHeight(),altManagerGui.layout.getHeaderHeight(),20);

        this.instance = altManagerGui;

        for (Alt alt : SimpleClientBase.altManager.alts) {
            this.addEntry(new AltEntry(alt,this));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class AltEntry extends ContainerObjectSelectionList.Entry<AltList.AltEntry>{

        public final AltList instance;
        public final Alt alt;

        public final Button change;
        public final Button edit;
        public final Button remove;

        public AltEntry(Alt alt,AltList list){
            this.instance = list;
            this.alt = alt;

            this.change = Button.builder(Component.literal("Change"),(on)->{
                Alt.change(Minecraft.getInstance(),this.alt);
                for (AltEntry child : this.instance.children()) {
                    child.refreshEntry();
                }
            }).size(30,20).pos(0,0).build();

            this.remove = Button.builder(Component.literal("Remove"),(on)-> {
                SimpleClientBase.altManager.alts.remove(this.alt);
                try {
                    SimpleClientBase.altManager.save(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.instance.removeEntry(this);
            }).size(30,20).pos(0,0).build();

            this.edit = Button.builder(Component.literal("Edit"),(on)-> Minecraft.getInstance().setScreen(new EditAltScreen(new AltManagerGui(this.instance.instance.last),this.alt, AltManager.Action.EDIT))).size(30,20).pos(0,0).build();
        }

        void refreshEntry() {
            this.change.active = !Minecraft.getInstance().getUser().getProfileId().equals(this.alt.uuid());
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of();
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.remove.getWidth() - 10;
            int j = p_93525_ - 2;

            this.remove.setPosition(i,j);
            this.edit.setPosition(i-5-this.edit.getWidth(),j);
            this.change.setPosition(i-5-this.edit.getWidth()-5-this.change.getWidth(),j);

            this.change.render(p_283112_,p_93529_,p_93530_,p_93532_);
            this.edit.render(p_283112_,p_93529_,p_93530_,p_93532_);
            this.remove.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.alt.name(),p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.change,this.edit,this.remove);
        }
    }
}
