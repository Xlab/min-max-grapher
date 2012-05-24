package minmax.gui.utils;

import hse.kcvc.jminmaxgd.Constants;
import hse.kcvc.jminmaxgd.Monomial;
import hse.kcvc.jminmaxgd.Polynomial;
import hse.kcvc.jminmaxgd.Series;
import minmax.model.Config;

/**
 *
 * @author Kouprianov Maxim <me@kc.vc> @ SE HSE
 */
public class Utils {

    public static String mLaTeX(Monomial m) {
        if (m.getGamma() == 0 && m.getDelta() == 0) {
            return "e";
        } else if (m.getGamma() == Constants.INFINITY
                && m.getDelta() == Constants._INFINITY) {
            return "\\varepsilon";
        } else if (m.getGamma() == Constants._INFINITY
                && m.getDelta() == Constants.INFINITY) {
            return "\\mathsf{T}";
        } else {
            String result = "";
            if (m.getGamma() == Constants.INFINITY) {
                result += "\\gamma^{\\infty}";
            } else if (m.getGamma() == Constants._INFINITY) {
                result += "\\gamma^{-\\infty}";
            } else if (m.getGamma() != 0) {
                result += "\\gamma^{" + m.getGamma() + "}";
            }

            if (m.getDelta() == Constants.INFINITY) {
                result += "\\delta^{\\infty}";
            } else if (m.getDelta() == Constants._INFINITY) {
                result += "\\delta^{-\\infty}";
            } else if (m.getDelta() != 0) {
                result += "\\delta^{" + m.getDelta() + "}";
            }

            return result;
        }
    }

    public static String pLaTeX(Polynomial p) {
        String result = "";
        for (int i = 0; i < p.getCount(); ++i) {
            result += mLaTeX(p.getElement(i)) + (i < p.getCount() - 1 ? " \\oplus " : "");
        }

        return result;
    }

    public static String sLaTeX(Series s) {
        String result = "";
        boolean pEmpty = s.getP().equals(new Polynomial());
        boolean qE = s.getQ().equals(new Polynomial(new Monomial(0, 0)));

        if (!pEmpty) {
            result += pLaTeX(s.getP());
        }

        if (s.getQ().equals(new Polynomial()) && pEmpty) {
            result += "\\varepsilon";
        } else {
            if (!qE) {
                result += (pEmpty ? "" : " \\oplus ") + "(" + pLaTeX(s.getQ()) + ")";
            }

            if (!s.getR().equals(new Monomial(0, 0)) || qE) {
                result += (pEmpty ? "" : (qE ? " \\oplus " : "")) + "(" + mLaTeX(s.getR()) + ")^\\star";
            }
        }
        return result;
    }
}
