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

import hse.kcvc.jminmaxgd.Monomial;
import java.awt.Color;
import minmax.gui.utils.Utils;

public class Piece {
    private final Monomial location;
    private final Type type;
    private final Color color;

    public Piece(int g, int d, Type type, Color color) {
        this.type = type;
        this.location = new Monomial(g, d);
        this.color = color;
    }

    public Monomial getLocation() {
        return location;
    }
    
    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
    
    static public enum Type {
        VERTEX,
        BOTTOM,
        LEFT,
        REGULAR
    };

    @Override
    public String toString() {
        return Utils.mLaTeX(location);
    }
}