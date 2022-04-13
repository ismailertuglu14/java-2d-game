package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SpeedBoots extends Entity {

    public OBJ_SpeedBoots(GamePanel gp) {
        super(gp);

        name = "Boots";
        down1 = setup("/resources/objects/speedBoots",gp.tileSize,gp.tileSize);
        image = down1;
    }
}
