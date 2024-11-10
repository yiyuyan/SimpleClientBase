package cn.ksmcbrigade.scb.config;

import cn.ksmcbrigade.scb.config.neoForgeCongfgEx.NeoModConfigEx;
import cn.ksmcbrigade.scb.config.neoForgeCongfgEx.NeoModConfigStringEx;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.guis.group.GroupRenderer;
import cn.ksmcbrigade.scb.module.Groups;
import cn.ksmcbrigade.scb.module.ModuleType;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.awt.*;
import java.util.ArrayList;

public class HUDConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    //Group1
    public static final ModConfigSpec.ConfigValue<String> GROUP1_TITLEd = BUILDER.comment("For group1").define("group1_title","COMBAT");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP1_BACKGROUND_COLORd = BUILDER.define("group1_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP1_CUR_BACKGROUND_COLORd = BUILDER.define("group1_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP1_TEXT_COLORd = BUILDER.define("group1_text_color", Color.WHITE.getRGB());

    //Group2
    public static final ModConfigSpec.ConfigValue<String> GROUP2_TITLEd = BUILDER.comment("For group2").define("group2_title","MOVEMENT");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP2_BACKGROUND_COLORd = BUILDER.define("group2_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP2_CUR_BACKGROUND_COLORd = BUILDER.define("group2_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP2_TEXT_COLORd = BUILDER.define("group2_text_color", Color.WHITE.getRGB());

    //Group3
    public static final ModConfigSpec.ConfigValue<String> GROUP3_TITLEd = BUILDER.comment("For group3").define("group3_title","BLOCK");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP3_BACKGROUND_COLORd = BUILDER.define("group3_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP3_CUR_BACKGROUND_COLORd = BUILDER.define("group3_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP3_TEXT_COLORd = BUILDER.define("group3_text_color", Color.WHITE.getRGB());

    //Group4
    public static final ModConfigSpec.ConfigValue<String> GROUP4_TITLEd = BUILDER.comment("For group4").define("group4_title","RENDER");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP4_BACKGROUND_COLORd = BUILDER.define("group4_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP4_CUR_BACKGROUND_COLORd = BUILDER.define("group4_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP4_TEXT_COLORd = BUILDER.define("group4_text_color", Color.WHITE.getRGB());

    //Group5
    public static final ModConfigSpec.ConfigValue<String> GROUP5_TITLEd = BUILDER.comment("For group5").define("group5_title","MISC");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP5_BACKGROUND_COLORd = BUILDER.define("group5_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP5_CUR_BACKGROUND_COLORd = BUILDER.define("group5_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP5_TEXT_COLORd = BUILDER.define("group5_text_color", Color.WHITE.getRGB());

    //Group6
    public static final ModConfigSpec.ConfigValue<String> GROUP6_TITLEd = BUILDER.comment("For group6").define("group6_title","OVERLAY");
    public static final ModConfigSpec.ConfigValue<Integer> GROUP6_BACKGROUND_COLORd = BUILDER.define("group6_background_color", Color.BLUE.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP6_CUR_BACKGROUND_COLORd = BUILDER.define("group6_cur_background_color", Color.RED.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> GROUP6_TEXT_COLORd = BUILDER.define("group6_text_color", Color.WHITE.getRGB());

    public static final ModConfigSpec.ConfigValue<Integer> MODULE_ENABLED_COLORd = BUILDER.comment("For features").define("enabled_text_color",Color.YELLOW.getRGB());
    public static final ModConfigSpec.ConfigValue<Integer> MODULE_DISABLED_COLORd = BUILDER.define("disabled_text_color",Color.WHITE.getRGB());

    public static final ModConfigSpec CONFIG_SPEC = BUILDER.build();

    //Group1
    public static final NeoModConfigStringEx GROUP1_TITLE = new NeoModConfigStringEx(GROUP1_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP1_BACKGROUND_COLOR = new NeoModConfigEx(GROUP1_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP1_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP1_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP1_TEXT_COLOR = new NeoModConfigEx(GROUP1_TEXT_COLORd,CONFIG_SPEC);

    //Group2
    public static final NeoModConfigStringEx GROUP2_TITLE = new NeoModConfigStringEx(GROUP2_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP2_BACKGROUND_COLOR = new NeoModConfigEx(GROUP2_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP2_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP2_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP2_TEXT_COLOR = new NeoModConfigEx(GROUP2_TEXT_COLORd,CONFIG_SPEC);

    //Group3
    public static final NeoModConfigStringEx GROUP3_TITLE =new NeoModConfigStringEx(GROUP3_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP3_BACKGROUND_COLOR = new NeoModConfigEx(GROUP3_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP3_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP3_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP3_TEXT_COLOR = new NeoModConfigEx(GROUP3_TEXT_COLORd,CONFIG_SPEC);

    //Group4
    public static final NeoModConfigStringEx GROUP4_TITLE = new NeoModConfigStringEx(GROUP4_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP4_BACKGROUND_COLOR = new NeoModConfigEx(GROUP4_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP4_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP4_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP4_TEXT_COLOR = new NeoModConfigEx(GROUP4_TEXT_COLORd,CONFIG_SPEC);

    //Group5
    public static final NeoModConfigStringEx GROUP5_TITLE = new NeoModConfigStringEx(GROUP5_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP5_BACKGROUND_COLOR = new NeoModConfigEx(GROUP5_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP5_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP5_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP5_TEXT_COLOR = new NeoModConfigEx(GROUP5_TEXT_COLORd,CONFIG_SPEC);


    //Group6
    public static final NeoModConfigStringEx GROUP6_TITLE = new NeoModConfigStringEx(GROUP6_TITLEd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP6_BACKGROUND_COLOR = new NeoModConfigEx(GROUP6_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP6_CUR_BACKGROUND_COLOR = new NeoModConfigEx(GROUP6_CUR_BACKGROUND_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx GROUP6_TEXT_COLOR = new NeoModConfigEx(GROUP6_TEXT_COLORd,CONFIG_SPEC);

    public static final NeoModConfigEx MODULE_ENABLED_COLOR = new NeoModConfigEx(MODULE_ENABLED_COLORd,CONFIG_SPEC);
    public static final NeoModConfigEx MODULE_DISABLED_COLOR = new NeoModConfigEx(MODULE_DISABLED_COLORd,CONFIG_SPEC);

    public static final KeyMapping UP = new KeyMapping("UpTurnTheFeaturesList", InputConstants.KEY_UP,KeyMapping.CATEGORY_MISC);
    public static final KeyMapping DOWN = new KeyMapping("DownTurnTheFeaturesList", InputConstants.KEY_DOWN,KeyMapping.CATEGORY_MISC);

    public static final KeyMapping LEFT = new KeyMapping("LeftTurnTheFeaturesList", InputConstants.KEY_LEFT,KeyMapping.CATEGORY_MISC);
    public static final KeyMapping RIGHT = new KeyMapping("RightTurnTheFeaturesList", InputConstants.KEY_RIGHT,KeyMapping.CATEGORY_MISC);

    public static final KeyMapping SET = new KeyMapping("SetTurnTheFeaturesList", InputConstants.KEY_NUMPADENTER,KeyMapping.CATEGORY_MISC);

    public static boolean init = false;

    public static void init(){
        if(init) return;
        Groups.COMBAT = new Group(new GroupRenderer(0,0,50,12, HUDConfig.GROUP1_BACKGROUND_COLOR.get(),HUDConfig.GROUP1_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP1_TEXT_COLOR.get(),HUDConfig.GROUP1_TITLE.get()), new ArrayList<>());
        Groups.ITEM = new Group(new GroupRenderer(0,24,50,12,HUDConfig.GROUP2_BACKGROUND_COLOR.get(),HUDConfig.GROUP2_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP2_TEXT_COLOR.get(),HUDConfig.GROUP2_TITLE.get()), new ArrayList<>());
        Groups.BLOCK = new Group(new GroupRenderer(0,12,50,12,HUDConfig.GROUP3_BACKGROUND_COLOR.get(),HUDConfig.GROUP3_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP3_TEXT_COLOR.get(),HUDConfig.GROUP3_TITLE.get()), new ArrayList<>());
        Groups.RENDER = new Group(new GroupRenderer(0,36,50,12,HUDConfig.GROUP4_BACKGROUND_COLOR.get(),HUDConfig.GROUP4_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP4_TEXT_COLOR.get(),HUDConfig.GROUP4_TITLE.get()), new ArrayList<>());
        Groups.OVERLAY = new Group(new GroupRenderer(0,48,50,12,HUDConfig.GROUP6_BACKGROUND_COLOR.get(),HUDConfig.GROUP6_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP6_TEXT_COLOR.get(),HUDConfig.GROUP6_TITLE.get()), new ArrayList<>());
        Groups.MISC = new Group(new GroupRenderer(0,60,50,12,HUDConfig.GROUP5_BACKGROUND_COLOR.get(),HUDConfig.GROUP5_CUR_BACKGROUND_COLOR.get(),HUDConfig.GROUP5_TEXT_COLOR.get(),HUDConfig.GROUP5_TITLE.get()), new ArrayList<>());

        ModuleType.init = false;
        ModuleType.init();

        init = true;
    }
}
