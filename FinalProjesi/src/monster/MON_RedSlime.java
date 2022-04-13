package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_RedSlime extends Entity {
    GamePanel gp;

    public MON_RedSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Red_Slime";
        speed = 3;
        maxLife = 30;
        life = maxLife;

        solidArea.x = gp.tileSize / 16;
        solidArea.y = (gp.tileSize * 6) / 16;
        solidArea.width = (gp.tileSize * 7) / 8;
        solidArea.height = (gp.tileSize * 5) / 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        up1 = setup("/resources/monster/redslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/resources/monster/redslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/resources/monster/redslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/resources/monster/redslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/resources/monster/redslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/resources/monster/redslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/resources/monster/redslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/resources/monster/redslime_down_2",gp.tileSize,gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 80) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;  // pick up a number from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
