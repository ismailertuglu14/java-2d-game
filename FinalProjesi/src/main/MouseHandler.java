package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener
{
    GamePanel gp;

    public MouseHandler(GamePanel gp) { this.gp = gp; }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        if (gp.gameState == gp.pauseState) {
            if (x >= gp.windowWidth - (int)(gp.tileSize * 1.5) && x <= gp.windowWidth - gp.tileSize / 2 && y >= gp.tileSize / 2 && y <= gp.tileSize * 1.5)
            {
                gp.music.soundOn = !gp.music.soundOn;
                if (gp.music.soundOn) {
                    gp.playMusic(0);
                }
                else {
                    gp.stopMusic();
                }
            }
        }

        if (gp.gameState == gp.playState) {
            if (x >= gp.windowWidth - gp.tileSize && x <= gp.windowWidth && y >= 0 && y <= gp.tileSize)
            {
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Move keys: A,W,S,D\nAttack key: Space\nCollect keys to open doors and find the exit.\nAnd look out the monsters\n" +
                        "                                                    Press enter to close dialogue";
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
