package cn.ksmcbrigade.scb.guis.group.info;

import cn.ksmcbrigade.scb.config.HUDConfig;
import cn.ksmcbrigade.scb.guis.group.Group;
import cn.ksmcbrigade.scb.module.Groups;

import java.util.Objects;

public class CurGroupInfo {

    public Group cur;

    public CurGroupInfo(Group cur){
       if(cur==null){
           HUDConfig.init = false;
           HUDConfig.init();
           return;
       }
        cur(cur);
    }

    public void cur(Group group){
        group.cur(true);
        if(!HUDConfig.init){
            HUDConfig.init();
        }
        if(!Objects.equals(Groups.COMBAT.renderer.title, group.renderer.title)){
            Groups.COMBAT.cur(false);
        }
        if(!Objects.equals(Groups.ITEM.renderer.title, group.renderer.title)){
            Groups.ITEM.cur(false);
        }
        if(!Objects.equals(Groups.BLOCK.renderer.title, group.renderer.title)){
            Groups.BLOCK.cur(false);
        }
        if(!Objects.equals(Groups.RENDER.renderer.title, group.renderer.title)){
            Groups.RENDER.cur(false);
        }
        if(!Objects.equals(Groups.OVERLAY.renderer.title, group.renderer.title)){
            Groups.OVERLAY.cur(false);
        }
        if(!Objects.equals(Groups.MISC.renderer.title, group.renderer.title)){
            Groups.MISC.cur(false);
        }

        this.cur = group;
    }

    public void next(){
        if(cur.equals(Groups.COMBAT)){
            cur(Groups.BLOCK);
            return;
        }
        if(cur.equals(Groups.BLOCK)){
            cur(Groups.ITEM);
            return;
        }
        if(cur.equals(Groups.ITEM)){
            cur(Groups.RENDER);
            return;
        }
        if(cur.equals(Groups.RENDER)){
            cur(Groups.OVERLAY);
            return;
        }
        if(cur.equals(Groups.OVERLAY)){
            cur(Groups.MISC);
            return;
        }
        if(cur.equals(Groups.MISC)){
            cur(Groups.COMBAT);
            return;
        }
        cur(Groups.COMBAT);
    }

    public void last(){
        if(cur.equals(Groups.COMBAT)){
            cur(Groups.MISC);
            return;
        }
        if(cur.equals(Groups.BLOCK)){
            cur(Groups.COMBAT);
            return;
        }
        if(cur.equals(Groups.ITEM)){
            cur(Groups.BLOCK);
            return;
        }
        if(cur.equals(Groups.RENDER)){
            cur(Groups.ITEM);
            return;
        }
        if(cur.equals(Groups.OVERLAY)){
            cur(Groups.RENDER);
            return;
        }
        if(cur.equals(Groups.MISC)){
            cur(Groups.OVERLAY);
            return;
        }
        cur(Groups.COMBAT);
    }
}
