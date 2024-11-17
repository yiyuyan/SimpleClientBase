package cn.ksmcbrigade.scb.guis.configuration;

import cn.ksmcbrigade.scb.guis.KeyboardSetting;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.uitls.JsonTypeChecker;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class ConfigList extends ContainerObjectSelectionList<ConfigList.ConfigEntry>{

    public final configurationScreen instance;
    public final Module module;

    public ConfigList(configurationScreen instance,Minecraft MC,Module module) {
        super(MC, instance.width,instance.layout.getContentHeight(),instance.layout.getHeaderHeight(),20);

        this.module = module;
        this.instance = instance;

        this.addEntry(new BooleanEntry(this.module,this,true,""));

        if(this.module.getConfig()==null){
            this.addEntry(new ButtonEntry(this.module,this,false,"bind",()-> Minecraft.getInstance().setScreen(new KeyboardSetting(this.module,this.instance))));
            return;
        }

        for (String s : this.module.getConfig().data.keySet()) {
            JsonElement element = this.module.getConfig().get(s);
            if(element instanceof JsonPrimitive primitive){
                if(primitive.isBoolean()){
                    this.addEntry(new BooleanEntry(this.module,this,false,s));
                    continue;
                }
                if(JsonTypeChecker.isInt(element) && !JsonTypeChecker.isFloat(element) && !JsonTypeChecker.isDouble(element)){
                    this.addEntry(new IntEntry(this.module,this,false,s));
                    continue;
                }
                if(JsonTypeChecker.isDouble(element)){
                    this.addEntry(new DoubleEntry(this.module,this,false,s));
                    continue;
                }
                if(primitive.isString()){
                    this.addEntry(new StringEntry(this.module,this,false,s));
                }
            }
        }

        this.addEntry(new ButtonEntry(this.module,this,false,"bind",()-> Minecraft.getInstance().setScreen(new KeyboardSetting(this.module,this.instance))));
    }

    @OnlyIn(Dist.CLIENT)
    public abstract static class ConfigEntry extends ContainerObjectSelectionList.Entry<ConfigEntry>{

        public final ConfigList instance;
        public final Module module;

        public ConfigEntry(Module module, ConfigList list){
            this.instance = list;
            this.module = module;
        }

        @Override
        public @NotNull List<? extends NarratableEntry> narratables() {
            return List.of();
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            //int i = this.instance.getScrollbarPosition() - this.remove.getWidth() - 10;
            //int j = p_93525_ - 2;

            //p_283112_.drawString(Minecraft.getInstance().font,this.module.getName(),p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class BooleanEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public Checkbox checkbox;

        public BooleanEntry(Module module, ConfigList list,boolean setModule,String key) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.checkbox = Checkbox.builder(Component.nullToEmpty(""),Minecraft.getInstance().font).selected(this.setModule?module.enabled:module.getConfig().get(ConfigKey).getAsBoolean()).onValueChange((var,var02)->{
                if(this.setModule){
                    try {
                        this.module.setEnabled(var02);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    this.module.getConfig().data.addProperty(this.ConfigKey,var02);
                    try {
                        this.module.getConfig().save(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).build();
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.checkbox.getWidth() - 10;
            int j = p_93525_ - 2;

            this.checkbox.setPosition(i,j);
            this.checkbox.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,setModule?this.module.getName():this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.checkbox);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class IntEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public EditBox editBox;

        public IntEntry(Module module, ConfigList list,boolean setModule,String key) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.editBox = new EditBox(Minecraft.getInstance().font,50,20,Component.literal("Value"));
            this.editBox.setCanLoseFocus(true);
            this.editBox.setMaxLength(22);
            this.editBox.setValue(String.valueOf(module.getConfig().get(this.ConfigKey).getAsInt()));
            this.editBox.setFilter(string -> {
                try {
                    Integer.parseInt(string);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
            this.editBox.setResponder(box -> {
                int value;
                try {
                    value = Integer.parseInt(box);
                } catch (NumberFormatException e) {
                   return;
                }
                this.module.getConfig().data.addProperty(ConfigKey,value);
                try {
                    this.module.getConfig().save(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.editBox.getWidth() - 10;
            int j = p_93525_ - 2;

            this.editBox.setPosition(i,j);
            this.editBox.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.editBox);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FloatEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public EditBox editBox;

        public FloatEntry(Module module, ConfigList list,boolean setModule,String key) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.editBox = new EditBox(Minecraft.getInstance().font,50,20,Component.literal("Value"));
            this.editBox.setCanLoseFocus(true);
            this.editBox.setMaxLength(22);
            this.editBox.setValue(String.valueOf(module.getConfig().get(this.ConfigKey).getAsFloat()));
            this.editBox.setFilter(string -> {
                try {
                    Float.parseFloat(string);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
            this.editBox.setResponder(box -> {
                float value;
                try {
                    value = Float.parseFloat(box);
                } catch (NumberFormatException e) {
                    return;
                }
                this.module.getConfig().data.addProperty(ConfigKey,value);
                try {
                    this.module.getConfig().save(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.editBox.getWidth() - 10;
            int j = p_93525_ - 2;

            this.editBox.setPosition(i,j);
            this.editBox.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.editBox);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class DoubleEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public EditBox editBox;

        public DoubleEntry(Module module, ConfigList list,boolean setModule,String key) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.editBox = new EditBox(Minecraft.getInstance().font,50,20,Component.literal("Value"));
            this.editBox.setCanLoseFocus(true);
            this.editBox.setMaxLength(22);
            this.editBox.setValue(String.valueOf(module.getConfig().get(this.ConfigKey).getAsDouble()));
            this.editBox.setFilter(string -> {
                try {
                    Double.parseDouble(string);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
            this.editBox.setResponder(box -> {
                double value;
                try {
                    value = Double.parseDouble(box);
                } catch (NumberFormatException e) {
                    return;
                }
                this.module.getConfig().data.addProperty(ConfigKey,value);
                try {
                    this.module.getConfig().save(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.editBox.getWidth() - 10;
            int j = p_93525_ - 2;

            this.editBox.setPosition(i,j);
            this.editBox.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.editBox);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class StringEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public EditBox editBox;

        public StringEntry(Module module, ConfigList list,boolean setModule,String key) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.editBox = new EditBox(Minecraft.getInstance().font,50,20,Component.literal("Value"));
            this.editBox.setCanLoseFocus(true);
            this.editBox.setValue(module.getConfig().get(this.ConfigKey).getAsString());
            this.editBox.setResponder(box -> {
                this.module.getConfig().data.addProperty(ConfigKey,box);
                try {
                    this.module.getConfig().save(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.editBox.getWidth() - 10;
            int j = p_93525_ - 2;

            this.editBox.setPosition(i,j);
            this.editBox.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.editBox);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class ButtonEntry extends ConfigEntry{

        public boolean setModule;
        public String ConfigKey;

        public Button button;

        public ButtonEntry(Module module, ConfigList list,boolean setModule,String key,Runnable runnable) {
            super(module, list);
            this.setModule = setModule;
            this.ConfigKey = key;

            this.button = Button.builder(Component.nullToEmpty(key),(button)-> runnable.run()).size(50,20).build();
        }

        @Override
        public void render(@NotNull GuiGraphics p_283112_, int p_93524_, int p_93525_, int p_93526_, int p_93527_, int p_93528_, int p_93529_, int p_93530_, boolean p_93531_, float p_93532_) {

            int i = this.instance.getScrollbarPosition() - this.button.getWidth() - 10;
            int j = p_93525_ - 2;

            this.button.setPosition(i,j);
            this.button.render(p_283112_,p_93529_,p_93530_,p_93532_);

            p_283112_.drawString(Minecraft.getInstance().font,this.ConfigKey,p_93526_,p_93525_+p_93528_/2-9/2,-1);
        }

        @Override
        public @NotNull List<? extends GuiEventListener> children() {
            return List.of(this.button);
        }
    }
}
