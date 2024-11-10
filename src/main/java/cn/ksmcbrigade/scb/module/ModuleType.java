package cn.ksmcbrigade.scb.module;

import cn.ksmcbrigade.scb.config.HUDConfig;
import cn.ksmcbrigade.scb.guis.group.Group;

public class ModuleType {

    public static boolean init = false;

    public static ModuleType COMBAT;
    public static ModuleType BLOCK;
    public static ModuleType MOVEMENT;
    public static ModuleType RENDER;
    public static ModuleType OVERLAY;
    public static ModuleType MISC;

    public Group group;
    public String name;

    public ModuleType(Group group,String name){
        this.group = group;
        this.name = name;
    }

    public static void init(){
        if(!init){

            COMBAT = new ModuleType(Groups.COMBAT,"COMBAT");
            BLOCK = new ModuleType(Groups.BLOCK,"BLOCK");
            MOVEMENT = new ModuleType(Groups.ITEM,"MOVEMENT");
            RENDER = new ModuleType(Groups.RENDER,"RENDER");
            OVERLAY = new ModuleType(Groups.OVERLAY,"OVERLAY");
            MISC = new ModuleType(Groups.MISC,"MISC");

            init = true;
        }
    }

    public static ModuleType[] values(){
        return new ModuleType[]{COMBAT,BLOCK,MOVEMENT,RENDER,MISC};
    }

    public static boolean check(){
        try {
            boolean set = false;
            for (ModuleType value : ModuleType.values()) {
                if(value.group==null){
                    set = true;
                    break;
                }
            }
            if(set){
                HUDConfig.init = false;
                HUDConfig.init();

                COMBAT.group = Groups.COMBAT;
                MOVEMENT.group = Groups.ITEM;
                BLOCK.group = Groups.BLOCK;
                RENDER.group = Groups.RENDER;
                MISC.group = Groups.MISC;
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
