package cn.ksmcbrigade.scb.config.neoForgeCongfgEx;

import net.neoforged.neoforge.common.ModConfigSpec;

public record NeoModConfigStringEx(ModConfigSpec.ConfigValue<String> value, ModConfigSpec self) {
    public String get(){
        if(self.isLoaded()){
            return value.get();
        }
        else{
            return value.getDefault();
        }
    }

    public void set(String value){
        this.value.set(value);
    }
}
