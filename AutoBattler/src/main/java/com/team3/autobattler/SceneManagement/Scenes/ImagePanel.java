/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.SceneManagement.Scenes;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author colli
 */
public class ImagePanel extends JPanel{
    ImageIcon image1 = new ImageIcon("Batman.png");
    ImageIcon image2 = new ImageIcon("Skeleton.png");
    ImageIcon image3 = new ImageIcon("Goblino.png");
    ImageIcon image4 = new ImageIcon("Mushroom.png");
    
    //final int IMG_Width1 = image1.getIconWidth();
    //final int IMG_HEIGHT1 = image1.getIconHeight();
    //final int IMG_Width2 = image2.getIconWidth();
   // final int IMG_HEIGHT2 = image2.getIconHeight();
    //final int IMG_Width3 = image3.getIconWidth();
    //final int IMG_HEIGHT3 = image3.getIconHeight();
   // final int IMG_Width4 = image4.getIconWidth();
   // final int IMG_HEIGHT4 = image4.getIconHeight();
    
    Point image_corner;
    Point previousPoint;
    
    public ImagePanel() {
        image_corner = new Point(0,0);
        
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
         
        
    }
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        image1.paintIcon(this, g, (int)image_corner.getX(), (int)image_corner.getY());
        image2.paintIcon(this, g, (int)image_corner.getX(), (int)image_corner.getY());
        image3.paintIcon(this, g, (int)image_corner.getX(), (int)image_corner.getY());
        image4.paintIcon(this, g, (int)image_corner.getX(), (int)image_corner.getY());
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {
            previousPoint = evt.getPoint();
        }
    }
    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent evt) {
            Point currentPoint = evt.getPoint();
            
            image_corner.translate((int)(currentPoint.getX()-previousPoint.getX()), (int)(currentPoint.getY()-previousPoint.getY()));
            previousPoint = currentPoint;
            repaint();
        }
    }
}
