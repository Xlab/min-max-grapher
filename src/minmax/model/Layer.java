package minmax.model;

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

import java.awt.Color;

public class Layer {

    private final Config config;
    private final Color color;
    private final boolean shadow;

    public Layer(Config config, Color color, boolean shadow) {
        this.config = config;
        this.color = color;
        this.shadow = shadow;
    }
    
    public Layer(Config config, Color color) {
        this(config, color, true);
    }

    public Color getColor() {
        return color;
    }

    public Config getConfig() {
        return config;
    }

    public boolean needShadow() {
        return shadow;
    } 
}
