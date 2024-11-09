package cn.ksmcbrigade.scb.guis.alt.edit;

import cn.ksmcbrigade.scb.SimpleClientBase;
import cn.ksmcbrigade.scb.alt.Alt;
import cn.ksmcbrigade.scb.alt.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class EditAltScreen extends Screen {

    public final Screen lastScreen;
    public final Alt alt;
    public final AltManager.Action action;

    private Button selectButton;
    private EditBox Edit;

    public EditAltScreen(Screen screen, Alt alt, AltManager.Action action) {
        super(Component.literal("Edit Alt"));
        this.lastScreen = screen;
        this.alt = alt;
        this.action = action;
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    public boolean keyPressed(int p_95964_, int p_95965_, int p_95966_) {
        if (!this.selectButton.active || this.getFocused() != this.Edit || p_95964_ != 257 && p_95964_ != 335) {
            return super.keyPressed(p_95964_, p_95965_, p_95966_);
        } else {
            try {
                this.onSelect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @Override
    protected void init() {
        if(this.minecraft==null){
            this.minecraft = Minecraft.getInstance();
        }
        this.Edit = new EditBox(this.font, this.width / 2 - 100, 116, 200, 20, Component.literal("User Name"));
        this.Edit.setMaxLength(128);
        this.Edit.setValue(this.alt.name());
        this.addWidget(this.Edit);
        this.selectButton = this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_DONE, p_95981_ -> {
                            try {
                                this.onSelect();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
                        .bounds(this.width / 2 - 100, this.height / 4 + 96 + 12, 200, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_CANCEL, p_95977_ -> this.onClose())
                        .bounds(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20)
                        .build()
        );
    }

    @Override
    protected void setInitialFocus() {
        this.setInitialFocus(this.Edit);
    }

    @Override
    public void resize(@NotNull Minecraft p_95973_, int p_95974_, int p_95975_) {
        String s = this.Edit.getValue();
        this.init(p_95973_, p_95974_, p_95975_);
        this.Edit.setValue(s);
    }

    private void onSelect() throws IOException {
        if(this.action.equals(AltManager.Action.ADD)){
            SimpleClientBase.altManager.add(new Alt(this.Edit.getValue(),this.alt.uuid(),this.alt.token()));
        }
        else{
            SimpleClientBase.altManager.replace(this.alt.uuid(),new Alt(this.Edit.getValue(),this.alt.uuid(),this.alt.token()));
        }
        this.onClose();
    }

    @Override
    public void onClose() {
        if(this.minecraft==null){
            this.minecraft = Minecraft.getInstance();
        }
        this.minecraft.setScreen(this.lastScreen);
    }


    @Override
    public void render(@NotNull GuiGraphics p_282464_, int p_95969_, int p_95970_, float p_95971_) {
        super.render(p_282464_, p_95969_, p_95970_, p_95971_);
        p_282464_.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
        p_282464_.drawString(this.font, Component.literal("User Name"), this.width / 2 - 100 + 1, 100, 10526880);
        this.Edit.render(p_282464_, p_95969_, p_95970_, p_95971_);
    }
}
