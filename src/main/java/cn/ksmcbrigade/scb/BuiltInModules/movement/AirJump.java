package cn.ksmcbrigade.scb.BuiltInModules.movement;

import cn.ksmcbrigade.scb.module.Module;
import cn.ksmcbrigade.scb.module.ModuleType;
import net.minecraft.client.Minecraft;

public class AirJump extends Module {
    public AirJump() {
        super(AirJump.class.getSimpleName(),ModuleType.MOVEMENT);
    }

    @Override
    public void keyInput(int key, boolean screen) {
        if(screen) return;
        if(Minecraft.getInstance().options.keyJump.isDown()){
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.jumpFromGround();
            }
        }
    }
}
