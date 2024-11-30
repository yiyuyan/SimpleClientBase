package cn.ksmcbrigade.scb.guis.anotherFeatureList.widgets;

import cn.ksmcbrigade.scb.config.HUD02Config;
import cn.ksmcbrigade.scb.guis.anotherFeatureList.FeatureList2;
import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.uitls.ModuleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchBox extends EditBox {

    public final int backgroundColor;
    public final int widthSize;
    public final int heightSize;

    public final ArrayList<FeatureRenderer> last = new ArrayList<>();
    public final FeatureList2 instance;

    public SearchBox(int x, int y, int width, int height, Component context, int backgroundColor, int widthSize, int heightSize, FeatureList2 instance) {
        super(Minecraft.getInstance().font, x,y,width,height,context);
        this.backgroundColor = backgroundColor;
        this.widthSize = widthSize;
        this.heightSize = heightSize;
        this.instance = instance;

        this.setCanLoseFocus(true);
        this.setHint(Component.literal("Search"));

        this.setResponder((str)->{
            if(str.isEmpty()){
                for (FeatureRenderer renderer : last) {
                    this.instance.removeWidget(renderer);
                }
                last.clear();
                return;
            }
            for (FeatureRenderer renderer : last) {
                this.instance.removeWidget(renderer);
            }
            last.clear();
            for (Module module : ModuleUtils.getAll(false)) {
                if(module.getName().toLowerCase().contains(str.toLowerCase())){
                    FeatureRenderer renderer = new FeatureRenderer(instance,this.getX(),this.getY()+this.getHeight()+this.heightSize+this.last.size()*25,50,25,-15693574,-15667718, HUD02Config.MODULE_ENABLED_COLORd.get(),module,module.type.group);
                    this.last.add(renderer);
                }
            }

            for (FeatureRenderer renderer : this.last) {
                this.instance.addWidget(renderer);
            }
        });
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics p_283252_, int p_281594_, int p_282100_, float p_283101_) {
        p_283252_.fillGradient(this.getX()-widthSize,this.getY()-heightSize,this.getX()+this.getWidth()+this.widthSize,this.getY()+this.getHeight()+this.heightSize,this.backgroundColor,this.backgroundColor);
        super.renderWidget(p_283252_, p_281594_, p_282100_, p_283101_);

        try {
            for (FeatureRenderer renderer : this.last) {
                renderer.render(p_283252_, p_281594_, p_282100_, p_283101_);
            }
        }
        catch (Exception e){
            //nothing
        }
    }
}
