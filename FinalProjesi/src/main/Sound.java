package main;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Sound {

    GamePanel gp;
    Clip clip;
    public boolean soundOn = true;
    public BufferedImage image1, image2;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/resources/sound/Wavecont.wav");
        soundURL[1] = getClass().getResource("/resources/sound/coin.wav");
        soundURL[2] = getClass().getResource("/resources/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/resources/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/resources/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/resources/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/resources/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/resources/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/resources/sound/extra_bonus.wav");
        soundURL[9] = getClass().getResource("/resources/sound/losing_game.wav");
        soundURL[10] = getClass().getResource("/resources/sound/Amogus.wav");

        image1 = setup("/resources/ui/volume", 48, 48);
        image2 = setup("/resources/ui/no-sound", 48, 48);
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image,width,height);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
