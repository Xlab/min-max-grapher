package minmax.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author xlab
 */
public class formulaeDisplay extends javax.swing.JPanel {

    /**
     * Creates new form formulaeDisplay
     */
    public formulaeDisplay() {
        initComponents();
        cache = new ArrayList<String>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    BufferedImage buffer;
    ArrayList<String> cache;

    public void clearCache() {
        cache.clear();
    }

    public void add(String s) {

        try {
            TeXFormula tmp = new TeXFormula("\\begin{array}{l}" + s + "&\\\\" + s + "\\end{array}");
            cache.add(s);
        } catch (Exception e) {
            //smth here
        }
    }

    public void render() {

        String document = "\\begin{array}{l}";
        for (String s : cache) {
            document += (s + "&\\\\");
        }
        document += "\\end{array}";

        TeXFormula formula = new TeXFormula(document);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
        icon.setInsets(new Insets(5, 5, 5, 5));
        buffer = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = buffer.createGraphics();
        JLabel ll = new JLabel();
        ll.setForeground(new Color(0, 0, 0));
        icon.paintIcon(ll, g2, 0, 0);

        this.setPreferredSize(new Dimension(buffer.getWidth(), buffer.getHeight()));
        this.getParent().doLayout();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (buffer != null) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage(buffer, 0, 0, this);
        }
    }
}
