/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author ramy
 */
public class Deck 
{
    private Vector<Card> cards;
    private final static int moves=250;
    public Deck()
    {
        cards= new Vector<Card> ();
        for(int i=0; i<52; i++)
        {
            cards.add(new Card(i/13+1, i%13+1));
        }
    }
    public void discard()
    {
        int next=5;
        for(int i=0; i<5; i++)
        {
            Card c=cards.get(i);
            if(!c.getKeep())
            {
                swap(next,i);
                next++;
            }
        }
        sortFirstFive();
    }
    public void setAllToKeep()
    {
        for(int i=0; i<52; i++)
        {
            Card c=cards.elementAt(i);
            c.setKeep(true);
        }
    }
    public Card getCard(int index)
    {
        return cards.get(index);
    }
    public void mix()
    {
        Random random= new Random();
        for(int i=0; i<moves; i++)
        {
            int i1= random.nextInt(52);
            int i2= random.nextInt(52);
            swap(i1,i2);
        }
        sortFirstFive();
    }
    public void swap(int i, int j)
    {
        Card c1=cards.get(i);
        Card c2=cards.get(j);
        cards.set(i,c2);
        cards.set(j,c1);
    }
    public void sortFirstFive()
    {
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4-i; j++)
            {
                Card c1= cards.get(j);
                Card c2= cards.get(j+1);
                if(c1.compareTo(c2) > 0)
                {
                    swap(j,j+1);
                }
            }
        }
    }
    public Score getScore()
    {
        Score result= Score.None;
        ArrayList<Integer> doubles= getDoubles();
        if(doubles.isEmpty())
        {
            result= getFlush();
            result= result.sum(getStraight());
            result=result.sum(getHighCard());
        }
        else
        {
            for(int i=0; i<doubles.size(); i++)
            {
                result= result.sum(getPartialScore(doubles.get(i)));
            }
        }
        return result;
    }
    public Score getPartialScore (Integer doubles)
    {
        if(doubles==2)
        {
            return Score.OnePair;
        }
        if(doubles==3)
        {
            return Score.ThreeOfAKind;
        }
        if(doubles==4)
        {
            return Score.FourOfAKind;
        }
        return Score.None;
    }
    public Score getHighCard()
    {
        Score result= Score.None;
        for(int i=0; i<5; i++)
        {
            Card c=cards.get(i);
            if(c.getValue() > 10 || c.getValue()==1)
            {
                result= Score.HighCard;
                break;
            }
        }
        return result;
    }
    public Score getFlush()
    {
        Score flush= Score.Flush;
        int seed= cards.get(0).getSeed();
        for(int i=1; i<5; i++)
        {
            if(seed != cards.get(i).getSeed())
            {
                flush= Score.None;
                break;
            }
        }
        return flush;
    }
    public Score getStraight()
    {
        Score result=Score.Straight;
        for(int i=0; i<4; i++)
        {
            int value= cards.get(i+1).getValue();
            if(i==0 && cards.get(i+1).getValue()==10 && cards.get(i).getValue()==1)
            {
                value=2;
            }
            if(cards.get(i).getValue()+1 != value)
            {
                result=Score.None;
                break;
            }
        }
        return result;
    }
    public ArrayList<Integer> getDoubles()
    {
        ArrayList<Integer> result= new ArrayList<Integer> ();
        int[] occurrences= new int[13];
        for(int i=0; i<13; i++)
        {
            occurrences[i]=0;
        }
        for(int i=0; i<5; i++)
        {
            Card c= cards.get(i);
            occurrences[c.getValue()-1]++;
        }
        for(int i=0; i<13; i++)
        {
            if(occurrences[i]>=2)
            {
                result.add(new Integer(occurrences[i]));
            }
        }
        return result;
    }
}


