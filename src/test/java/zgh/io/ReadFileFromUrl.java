package zgh.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

/**
 * 从url读图片写到本地
 */
public class ReadFileFromUrl {
    
    @Test
    public void a() {
        String url = "http://store.is.autonavi.com/showpic/ab3ab73653b1a16602717fa4101011be";
        try {
            BufferedImage bi = ImageIO.read(new URL(url));
            boolean ok = ImageIO.write(bi, "jpg", new File("c:/t/a.jpg"));
            System.out.println(ok);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
