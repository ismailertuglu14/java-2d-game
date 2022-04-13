package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    //DEBUG
    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        //TITLE STATE

        if (gp.gameState == gp.titleState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.player.setDefaultValues();
                    gp.aSetter.setObject();
                    gp.aSetter.setMonster();
                    gp.gameState = gp.playState;
                    if (gp.music.soundOn) {
                        gp.playMusic(0);
                    }
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }

        //PLAY STATE
        else if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_SPACE){
                enterPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.pauseState;
            }
        }
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.playState;
            }

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1)
                {
                    gp.gameState = gp.titleState;
                    if (gp.music.soundOn) {
                        gp.stopMusic();
                    }
                }
                if (gp.ui.commandNum == 2)
                {
                    System.exit(0);
                }
            }
        }

        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        //DEAD STATE
        else if (gp.gameState == gp.deadState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.player.setDefaultValues();
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1)
                {
                    gp.gameState = gp.titleState;
                }
                if (gp.ui.commandNum == 2)
                {
                    System.exit(0);
                }
            }
        }

        //END STATE
        else if (gp.gameState == gp.endState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 1;
                }
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1)
                {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    if (gp.music.soundOn) {
                        gp.stopMusic();
                    }
                    gp.gameState = gp.titleState;
                }
                if (gp.ui.commandNum == 1)
                {
                    System.exit(0);
                }
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

}
