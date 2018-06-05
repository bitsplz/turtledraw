/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author MMM
 */
public class Draw {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame canvas =new Canvas();
        canvas.setTitle("Draw");
        canvas.show();
    }
    
}


class Canvas extends JFrame{
    private final JButton upButton,downButton,leftButton,rightButton;
    private final JPanel bGrid;
    int xPos=250,turtleX=244;
    int yPos=250,turtleY=250;
    int[] allX={xPos};
    int[] allY={yPos};
    public Canvas(){
        setSize(new Dimension(500,500));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container pane=getContentPane();
        bGrid=new JPanel();bGrid.setLayout(new GridLayout(1,1));
        ButtonListener listener =new ButtonListener();
        upButton=new JButton("UP");downButton=new JButton("DOWN");
        rightButton=new JButton("Right");leftButton=new JButton("Left");
        bGrid.add(upButton,"South");upButton.addActionListener(listener);
        bGrid.add(downButton,"South");downButton.addActionListener(listener);
        bGrid.add(rightButton,"South");rightButton.addActionListener(listener);
        bGrid.add(leftButton,"South");leftButton.addActionListener(listener);
        pane.add(bGrid,"South");
        pane.setBackground(Color.white);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        final Font font=new Font("Arial",Font.BOLD,20);
        g2.setFont(font);
        g2.drawString("X", turtleX,turtleY);
        g2.setColor(Color.BLUE);
        g2.drawPolyline(allX, allY, allX.length);
    }
    
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            if (e.getSource()==upButton) {
                yPos-=20;turtleY-=20;
            }
            else if (e.getSource()==downButton) {
                yPos+=20;turtleY+=20;
            }
            else if (e.getSource()==rightButton) {
                xPos+=20;turtleX+=20;
                turtleY=yPos+7;//not necessary
            }
            else if (e.getSource()==leftButton) {
                xPos-=20;turtleX-=20;
                turtleY=yPos+7;//not necessary
            }
            allX=Arrays.copyOf(allX, allX.length+1);
            allY=Arrays.copyOf(allY, allY.length+1);
            allX[allX.length-1]=xPos;
            allY[allY.length-1]=yPos;
            repaint();
        }
    }
}