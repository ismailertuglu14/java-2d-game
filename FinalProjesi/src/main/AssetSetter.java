package main;

import monster.MON_BlueSlime;
import monster.MON_GreenSlime;
import monster.MON_RedSlime;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) { this.gp = gp; }

    public void setObject() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = gp.tileSize * 34;
        gp.obj[0].worldY = gp.tileSize * 14;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = gp.tileSize * 17;
        gp.obj[1].worldY = gp.tileSize * 18;

        gp.obj[2] = new OBJ_SpeedBoots(gp);
        gp.obj[2].worldX = gp.tileSize * 22;
        gp.obj[2].worldY = gp.tileSize * 35;

        gp.obj[3] = new OBJ_Key(gp);
        gp.obj[3].worldX = gp.tileSize * 38;
        gp.obj[3].worldY = gp.tileSize * 15;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = gp.tileSize * 27;
        gp.obj[4].worldY = gp.tileSize * 29;

        gp.obj[5] = new OBJ_Heart(gp);
        gp.obj[5].worldX = gp.tileSize * 34;
        gp.obj[5].worldY = gp.tileSize * 23;

        gp.obj[6] = new OBJ_SpeedBoots(gp);
        gp.obj[6].worldX = gp.tileSize * 139;
        gp.obj[6].worldY = gp.tileSize * 7;

        gp.obj[7] = new OBJ_SpeedBoots(gp);
        gp.obj[7].worldX = gp.tileSize * 183;
        gp.obj[7].worldY = gp.tileSize * 7;

        gp.obj[8] = new OBJ_SpeedBoots(gp);
        gp.obj[8].worldX = gp.tileSize * 134;
        gp.obj[8].worldY = gp.tileSize * 27;

        gp.obj[8] = new OBJ_SpeedBoots(gp);
        gp.obj[8].worldX = gp.tileSize * 171;
        gp.obj[8].worldY = gp.tileSize * 83;

        gp.obj[9] = new OBJ_Door(gp);
        gp.obj[9].worldX = gp.tileSize * 36;
        gp.obj[9].worldY = gp.tileSize * 30;

        gp.obj[10] = new OBJ_Key(gp);
        gp.obj[10].worldX = gp.tileSize * 10;
        gp.obj[10].worldY = gp.tileSize * 43;
    }

    public void setNPC() {
    }

    public void setMonster() {
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 35;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 23;
        gp.monster[1].worldY = gp.tileSize * 38;

        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = gp.tileSize * 43;
        gp.monster[2].worldY = gp.tileSize * 15;

        gp.monster[3] = new MON_GreenSlime(gp);
        gp.monster[3].worldX = gp.tileSize * 43;
        gp.monster[3].worldY = gp.tileSize * 39;

        gp.monster[4] = new MON_GreenSlime(gp);
        gp.monster[4].worldX = gp.tileSize * 33;
        gp.monster[4].worldY = gp.tileSize * 11;

        gp.monster[5] = new MON_BlueSlime(gp);
        gp.monster[5].worldX = gp.tileSize * 138;
        gp.monster[5].worldY = gp.tileSize * 23;

        gp.monster[6] = new MON_BlueSlime(gp);
        gp.monster[6].worldX = gp.tileSize * 150;
        gp.monster[6].worldY = gp.tileSize * 23;

        gp.monster[7] = new MON_BlueSlime(gp);
        gp.monster[7].worldX = gp.tileSize * 146;
        gp.monster[7].worldY = gp.tileSize * 76;

        gp.monster[8] = new MON_BlueSlime(gp);
        gp.monster[8].worldX = gp.tileSize * 151;
        gp.monster[8].worldY = gp.tileSize * 32;

        gp.monster[9] = new MON_RedSlime(gp);
        gp.monster[9].worldX = gp.tileSize * 150;
        gp.monster[9].worldY = gp.tileSize * 83;

        gp.monster[10] = new MON_RedSlime(gp);
        gp.monster[10].worldX = gp.tileSize * 106;
        gp.monster[10].worldY = gp.tileSize * 83;
    }
}
