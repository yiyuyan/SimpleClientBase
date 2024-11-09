package cn.ksmcbrigade.scb.config.neoForgeCongfgEx;

import net.neoforged.neoforge.common.ModConfigSpec;

public record NeoModConfigEx(ModConfigSpec.ConfigValue<Integer> value,ModConfigSpec self) {
    public int get(){
        if(self.isLoaded()){
            return value.get();
        }
        else{
            return value.getDefault();
        }
    }

    public void set(int value){
        this.value.set(value);
    }
}
