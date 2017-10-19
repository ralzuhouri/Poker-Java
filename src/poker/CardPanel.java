/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ramy
 */
public class CardPanel extends Panel implements MouseListener
{
    private static final int width=120;
    private static final int height=160;
    private int offset;
    private Image image;
    private Card card;
    private boolean allowDiscard;
    public CardPanel()
    {
        this.setPreferredSize(new Dimension(width,height));
        allowDiscard=true;
    }
    public boolean getAllowDiscard()
    {
        return allowDiscard;
    }
    public void setAllowDiscard(boolean allowDiscard)
    {
        this.allowDiscard= allowDiscard;
    }
    public Card getCard()
    {
        return card;
    }
    public void setCard(Card c)
    {
        card=c;
        setImage(c.getFilename());
    }
    public void setImage(String filename) 
    {
        InputStream stream= null;
        try 
        {
            stream= Card.getInputStream(filename);
            image= ImageIO.read(stream);
            Image scaledImage=image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            image= scaledImage;
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
        finally
        {
            if(stream!= null)
            {
                try 
                {
                    stream.close();
                } 
                catch (IOException ex) 
                {
                    System.out.println("Cannot close input stream: " + ex); 
                }
            }
        }
    }
    public void setImage (Card card)
    {
        setImage(card.getFilename());
    }
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(image,0,0,null);
    }
    @Override
    public void mouseClicked(MouseEvent e) 
    {
    }
    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(allowDiscard)
        {
            card.setKeep(!card.getKeep());
            if(!card.getKeep())
            {
                if(Card.getDiscardedCards()==5)
                {
                    card.setKeep(!card.getKeep());
                    Toolkit.getDefaultToolkit().beep();
                }
                else
                {
                    setImage("black.png");
                    this.repaint();
                }
            }
            else
            {
                setImage(card.getFilename());
                this.repaint();
            }
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    @Override
    public void mouseReleased(MouseEvent me) 
    {
    }
    @Override
    public void mouseEntered(MouseEvent me) 
    {
    }
    @Override
    public void mouseExited(MouseEvent me) 
    {
    }
}


