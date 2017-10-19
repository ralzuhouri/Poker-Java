package poker;

import java.io.InputStream;

/**
 *
 * @author ramy
 */
public class Card implements Comparable<Card>
{
    private int seed;
    private int value;
    private boolean keep;
    private static int discardedCards=0;
    public Card(int seed,int value)
    {
        this.value=value;
        this.seed=seed;
        keep=true;
    }
    public static int getDiscardedCards()
    {
        return discardedCards;
    }
    public String getFilename()
    {
        String result= "" + seed + "-" + value + ".png";
        return result;
    }
    public static InputStream getInputStream(String filename)
    {
        InputStream result;
        result= Card.class.getClassLoader().getResourceAsStream(filename);
        return result;
    }
    public int getSeed()
    {
        return seed;
    }
    public int getValue()
    {
        return value;
    }
    public boolean getKeep()
    {
        return keep;
    }
    public void setKeep(boolean keep)
    {
        if(!this.keep && keep)
        {
            Card.discardedCards--;
        }
        else if(this.keep && !keep)
        {
            Card.discardedCards++;
        }
        this.keep=keep;
    }
    @Override
    public int compareTo(Card c) 
    {
        int result= valueOf() - c.valueOf();
        return result;
    }
    @Override
    public String toString()
    {
        String result;
        switch(value)
        {
            case 11:
                result= "J of ";
                break;
            case 12:
                result="Q of ";
                break;
            case 13:
                result="K of ";
                break;
            case 1:
                result="A of ";
                break;
            default:
                result= "" + value + " of ";
        }
        switch(seed)
        {
            case 1:
                result+= "Clubs";
                break;
            case 2:
                result+= "Spades";
                break;
            case 3:
                result+= "Diamonds";
                break;
            case 4:
                result+="Hearts";
        }
        return result;
    }
    public int valueOf()
    {
        int theValue= value;
        if(value==1)
        {
            theValue=13;
        }
        return value*4+seed;
    }
}

