package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    public int hasKey = 0;
    public final int screenX;
    public final int screenY;
    private BufferedImage image;
    public BufferedImage standUp, standDown, standLeft,standRight;
    private int tempSpeed;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.windowWidth / 2 - (gp.tileSize) / 2 ;
        screenY = gp.windowHeight / 2 - (gp.tileSize) / 2 ;

        solidArea = new Rectangle(gp.tileSize / 6,(gp.tileSize * 2) / 6,(gp.tileSize * 2) / 3,(gp.tileSize * 2) / 3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = (gp.tileSize * 6) / 8;
        attackArea.height = (gp.tileSize * 6) / 8;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 43;
        hasKey = 0;
        speed = 4;
        tempSpeed = speed;
        direction = "right";
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("/resources/player/up/player_01", gp.tileSize, gp.tileSize);
        up2 = setup("/resources/player/up/player_02", gp.tileSize, gp.tileSize);
        standUp = setup("/resources/player/up/player_00", gp.tileSize, gp.tileSize);

        down1 = setup("/resources/player/down/player_04", gp.tileSize, gp.tileSize);
        down2 = setup("/resources/player/down/player_05", gp.tileSize, gp.tileSize);
        standDown = setup("/resources/player/down/player_03", gp.tileSize, gp.tileSize);

        left1 = setup("/resources/player/left/player_10", gp.tileSize, gp.tileSize);
        left2 = setup("/resources/player/left/player_11", gp.tileSize, gp.tileSize);
        standLeft = setup("/resources/player/left/player_09", gp.tileSize, gp.tileSize);

        right1 = setup("/resources/player/right/player_07", gp.tileSize, gp.tileSize);
        right2 = setup("/resources/player/right/player_08", gp.tileSize, gp.tileSize);
        standRight = setup("/resources/player/right/player_06", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/resources/player/attack_up/attack0",gp.tileSize,gp.tileSize * 2);
        attackUp2 = setup("/resources/player/attack_up/attack1",gp.tileSize,gp.tileSize * 2);
        attackDown1 = setup("/resources/player/attack_down/attack2",gp.tileSize,gp.tileSize * 2);
        attackDown2 = setup("/resources/player/attack_down/attack3",gp.tileSize,gp.tileSize * 2);
        attackLeft1 = setup("/resources/player/attack_left/attack4",gp.tileSize,gp.tileSize);
        attackLeft2 = setup("/resources/player/attack_left/attack5",gp.tileSize,gp.tileSize);
        attackRight1 = setup("/resources/player/attack_right/attack6",gp.tileSize,gp.tileSize);
        attackRight2 = setup("/resources/player/attack_right/attack7",gp.tileSize,gp.tileSize);
    }

    public void update() {
        if (attacking) {
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed  || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if(keyH.upPressed) {
                direction = "up";

            }
            else if(keyH.downPressed) {
                direction = "down";

            }
            else if(keyH.leftPressed) {
                direction = "left";

            }
            else if(keyH.rightPressed) {
                direction = "right";

            }

            //Check Tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check Object Collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Check NPC Collision
            int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);

            //Check Monster Collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Event
            gp.eHandler.checkEvent();

            //if collision is false, player can move
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else if (spriteNum == 3) {
                    spriteNum = 4;
                }
                else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        //This needs to be outside of key if statement!
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (speedy) {
            if (speedCounter > 600) {
                speedy = false;
                speedCounter = 0;
                speed = tempSpeed;
            }
        }

        if (life <= 0) {
            gp.gameState = gp.deadState;
            if (gp.music.soundOn) {
                gp.stopMusic();
                gp.playSE(9);
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //Save the current worldX worldY solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collision with the updated worldX, worldY, and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            switch (gp.obj[i].name) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    if (gp.music.soundOn) {
                        gp.playSE(1);
                    }
                    break;
                case "Door":
                    if (hasKey > 0) {
                        hasKey--;
                        gp.obj[i] = null;
                        if (gp.music.soundOn) {
                            gp.playSE(3);
                        }
                    }
                    break;
                case "Boots":
                    speedy = true;
                    speed += 6;
                    gp.obj[i] = null;
                    if (gp.music.soundOn) {
                        gp.playSE(8);
                    }
                    break;
                case "Heart":
                    life += 2;
                    if (life > 6) { life = maxLife; }
                    gp.obj[i] = null;
                    if (gp.music.soundOn) {
                        gp.playSE(1);
                    }
                    break;
            }
        }
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed)
        {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else
            {
                if (gp.music.soundOn) {
                    gp.playSE(7);
                }
                attacking = true;
            }
        }

    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                if (gp.music.soundOn) {
                    gp.playSE(6);
                }
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                if (gp.music.soundOn) {
                    gp.playSE(5);
                }
                gp.monster[i].life -= 2;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    public void boosterAnimation(Graphics2D g2) {
        speedCounter++;

        if (speedCounter % 8 == 0) {
            changeAlpha(g2, 0.9f);
        }
        if (speedCounter % 8 == 1) {
            changeAlpha(g2, 0.7f);
        }
        if (speedCounter % 8 == 2) {
            changeAlpha(g2, 0.5f);
        }
        if (speedCounter % 8 == 3) {
            changeAlpha(g2, 0.3f);
        }
        if (speedCounter % 8 == 4) {
            changeAlpha(g2, 0.1f);
        }
        if (speedCounter % 8 == 5) {
            changeAlpha(g2, 0.3f);
        }
        if (speedCounter % 8 == 6) {
            changeAlpha(g2, 0.5f);
        }
        if (speedCounter % 8 == 7) {
            changeAlpha(g2, 0.7f);
        }
    }

    public void draw(Graphics2D g2)
    {
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                    if (spriteNum == 3) { image = up1; }
                    if (spriteNum == 4) { image = up2; }
                }
                else {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) { image = attackUp1; }
                    if (spriteNum == 2) { image = attackUp2; }
                    if (spriteNum == 3) { image = attackUp2; }
                    if (spriteNum == 4) { image = attackUp2; }
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                    if (spriteNum == 3) { image = down1; }
                    if (spriteNum == 4) { image = down2; }
                }
                else {
                    if (spriteNum == 1) { image = attackDown1; }
                    if (spriteNum == 2) { image = attackDown2; }
                    if (spriteNum == 3) { image = attackDown2; }
                    if (spriteNum == 4) { image = attackDown2; }
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                    if (spriteNum == 3) { image = left1; }
                    if (spriteNum == 4) { image = left2; }
                }
                else {
                    tempScreenX = screenX;
                    if (spriteNum == 1) { image = attackLeft2; }
                    if (spriteNum == 2) { image = attackLeft1; }
                    if (spriteNum == 3) { image = attackLeft1; }
                    if (spriteNum == 4) { image = attackLeft1; }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) { image = right1; }
                    if (spriteNum == 2) { image = right2; }
                    if (spriteNum == 3) { image = right1; }
                    if (spriteNum == 4) { image = right2; }
                }
                else {
                    if (spriteNum == 1) { image = attackRight2; }
                    if (spriteNum == 2) { image = attackRight1; }
                    if (spriteNum == 3) { image = attackRight1; }
                    if (spriteNum == 4) { image = attackRight1; }
                }
                break;
        }

        if (invincible) {
            changeAlpha(g2,0.4f);
        }
        if (speedy) {
            boosterAnimation(g2);
        }


        if (attacking) {
            g2.drawImage(image, tempScreenX, tempScreenY,null);
        }
        else {
            if (!keyH.upPressed) {
                if (direction.equals("up")) {
                    g2.drawImage(standUp,tempScreenX,tempScreenY,null);
                }
            }
            else {
                g2.drawImage(image, tempScreenX, tempScreenY,null);
            }

            if (!keyH.downPressed) {
                if (direction.equals("down")) {
                    g2.drawImage(standDown,tempScreenX,tempScreenY,null);
                }
            }
            else {
                g2.drawImage(image, tempScreenX, tempScreenY,null);
            }

            if (!keyH.leftPressed) {
                if (direction.equals("left")) {
                    g2.drawImage(standLeft,tempScreenX,tempScreenY,null);
                }
            }
            else {
                g2.drawImage(image, tempScreenX, tempScreenY,null);
            }

            if (!keyH.rightPressed) {
                if (direction.equals("right")) {
                    g2.drawImage(standRight,tempScreenX,tempScreenY,null);
                }
            }
            else {
                g2.drawImage(image, tempScreenX, tempScreenY,null);
            }
        }

        //Reset Alpha
        changeAlpha(g2,1f);
    }
}