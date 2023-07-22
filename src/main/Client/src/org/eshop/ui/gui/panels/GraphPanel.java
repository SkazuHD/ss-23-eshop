package org.eshop.ui.gui.panels;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    int[] data;
    int max;
    int[] dt;
    public GraphPanel(int[] dt,int[] data, int max) {
        super();
        this.data = data;
        this.max = max;
        this.dt = dt;

        this.setPreferredSize(new Dimension(1280, 900));


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;

        // Calculate the scaling factors
        double xScale = (double) chartWidth / (data.length - 1);
        double yScale = (double) chartHeight / (this.max + this.max * 0.1);

        // Draw x and y axes
        g2.drawLine(padding, height - padding, padding, padding);
        g2.drawLine(padding, height - padding, width - padding, height - padding);

        for (int i = 0; i < data.length; i++) {
            int x = (int) (i * xScale) + padding;
            int y = height - padding + 15;

            g2.drawString(String.valueOf(i + 1), x, y);
        }

        // Draw y-axis labels
        for (int i = 0; i <= this.max + 25; i += 25) {
            int x = padding - 30;
            int y = height - padding - (int) (i * yScale);

            g2.drawString(String.valueOf(i), x, y);
        }
        // Draw data points and lines
        for (int i = 0; i < data.length - 1; i++) {
            int x1 = (int) (i * xScale) + padding;
            int y1 = height - (int) (data[i] * yScale) - padding;
            int x2 = (int) ((i + 1) * xScale) + padding;
            int y2 = height - (int) (data[i + 1] * yScale) - padding;

            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.blue);
            g2.drawLine(x1, y1, x2, y2);

        }
    }
}
