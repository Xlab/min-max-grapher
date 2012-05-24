package minmax.gui.utils;

/**
 * Создал: Максим Куприянов,
 * Факультет Бизнес-информатики
 * Отделение Программной инженерии
 * 2 курс, группа 272ПИ, НИУ-ВШЭ
 *
 * Проект: Курсовая работа 2011-2012гг
 *
 * Тема: "Программа выполнения операций в
 * идемпотентном полукольце конус-ограниченных
 * множеств."
 *
 * Программа: MinMaxGrapher
 *
 * Связь: me@kc.vc
 */

import java.awt.*;
import java.awt.image.*;

public class ImageHelpers {

    public static AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public static BufferedImage makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {

            public int markerRGB = color.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return imageToBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
    }

    private static BufferedImage imageToBufferedImage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return bufferedImage;

    }
}
