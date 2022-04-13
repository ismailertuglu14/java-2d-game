package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_SpeedBoots;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI
{
    GamePanel gp;
    Graphics2D g2;

    Font arial_40 = new Font("Arial",Font.PLAIN,40);
    BufferedImage heart_full,heart_half,heart_blank, keyImage, sound_on, sound_off, boots;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    public UI(GamePanel gp)
    {
        this.gp = gp;

        //CREATER HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity key = new OBJ_Key(gp);
        keyImage = key.image;
        sound_on = gp.music.image1;
        sound_off = gp.music.image2;
        Entity speedBoots = new OBJ_SpeedBoots(gp);
        boots = speedBoots.image;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState) {
            drawPlayerLife();
            drawKey();
            drawInfo();
            if (gp.player.speedy) {
                drawBoots();
            }
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
            drawKey();
            drawSoundImage();
            if (gp.player.speedy) {
                drawBoots();
            }
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        //DEAD STATE
        if (gp.gameState == gp.deadState) {
            drawDeadScreen();
        }
        //END STATE
        if (gp.gameState == gp.endState) {
            drawEndScreen();
        }

    }

    public void drawPlayerLife()
    {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //DRAW MAX HEART
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = gp.tileSize / 2;
        i = 0;

        //DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawKey()
    {
        int x = gp.tileSize / 2;
        int y = gp.tileSize * 2;

        g2.drawImage(keyImage,x,y,null);
        g2.setFont(arial_40);
        g2.drawString("x " + gp.player.hasKey,94,180);
    }

    public void drawBoots() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize * 4;

        g2.drawImage(boots,x,y,null);
    }

    public void drawInfo() {
        int x = gp.windowWidth - gp.tileSize;
        int y = 0;

        drawSubWindows(x,y, gp.tileSize, gp.tileSize);
        x = gp.windowWidth - 36;
        y = gp.tileSize * 3 / 4;
        g2.drawString("i",x,y);
    }

    public void drawTitleScreen()
    {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0,0,gp.windowWidth,gp.windowHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
            String text = "Maze Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text,x+3,y+3);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text,x,y);

            //CHARACTER IMAGE
            x = gp.windowWidth / 2 - (gp.tileSize*2) /2;
            y += gp.tileSize * 2;
            g2.fillRoundRect(x - gp.tileSize / 2,y - gp.tileSize / 2, gp.tileSize * 3, gp.tileSize * 3, 90, 90);
            g2.drawImage(gp.player.standDown,x,y,gp.tileSize * 2,gp.tileSize * 2,null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,37F));

            text = "New Game";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text,x+3,y+3);

            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            if (commandNum == 0) {
                g2.drawString(">",x - gp.tileSize,y);
            }

            text = "Quit";
            x = getXforCenteredText(text);
            y += gp.tileSize;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text,x+3,y+3);

            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            if (commandNum == 1) {
                g2.drawString(">",x - gp.tileSize,y);
            }
        }
    }

    public void drawPauseScreen()
    {
        int dialogueX = gp.tileSize * 3;
        int dialogueY = gp.windowHeight / 2 - gp.tileSize * 4;
        int width = gp.windowWidth / 2 + gp.tileSize * 2;
        int height = gp.tileSize * 8;
        drawSubWindows(dialogueX,dialogueY,width,height);

        Color c = new Color(255,255,255);
        g2.setColor(c);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.windowHeight / 2 - (int) (gp.tileSize * 1.5);
        //Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+3, y+3);
        //Text
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        text = "Resume";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text,x,y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Return to Main Menu";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawSoundImage()
    {
        int x = gp.windowWidth - (int)(gp.tileSize * 1.5);
        int y = gp.tileSize / 2;

        if (gp.music.soundOn) {
            g2.drawImage(sound_on,x,y,null);
        }
        if (!gp.music.soundOn){
            g2.drawImage(sound_off,x,y,null);
        }
    }

    public void drawDeadScreen()
    {
        int subX = gp.tileSize * 3;
        int subY = gp.windowHeight / 2 - gp.tileSize * 4;
        int width = gp.windowWidth / 2 + gp.tileSize * 2;
        int height = gp.tileSize * 8;
        drawSubWindows(subX,subY,width,height);

        Color color = new Color(255,255,255);
        g2.setColor(color);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "YOU DIED";
        int x = getXforCenteredText(text);
        int y = gp.windowHeight / 2 - (int) (gp.tileSize * 1.5);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        text = "Respawn";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text,x,y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        text = "Return to Main Menu";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawEndScreen()
    {
        int subX = gp.tileSize * 3;
        int subY = gp.windowHeight / 2 - gp.tileSize * 4;
        int width = gp.windowWidth / 2 + gp.tileSize * 2;
        int height = gp.tileSize * 8;
        drawSubWindows(subX,subY,width,height);

        Color color = new Color(255,255,255);
        g2.setColor(color);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        String text = "Congratulations!";
        String text2 = "You finished the map!";
        int x = getXforCenteredText(text);
        int y = gp.windowHeight / 2 - (int) (gp.tileSize * 1.5);
        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = getXforCenteredText(text2);
        y += gp.tileSize;
        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text2,x+3,y+3);
        g2.setColor(Color.white);
        g2.drawString(text2, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        text = "Return to Main Menu";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text,x,y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawDialogueScreen()
    {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.windowWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindows(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line: currentDialogue.split("\n"))
        {
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    public void drawSubWindows(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0,240);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public int getXforCenteredText(String text)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.windowWidth / 2 - length / 2;
    }

}
