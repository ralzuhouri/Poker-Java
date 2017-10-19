/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author ramy
 */
public enum Score 
{
    None,HighCard, OnePair, TwoPair, ThreeOfAKind, 
    Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    public Score sum(Score s)
    {
        if( (s==OnePair && this==ThreeOfAKind) || (this==OnePair && s==ThreeOfAKind))
        {
            return FullHouse;
        }
        if(s==OnePair && this==OnePair)
        {
            return TwoPair;
        }
        if( (s==Flush && this==Straight ) || (s==Straight && this==Flush) )
        {
            return StraightFlush;
        }
        if(compareTo(s)<0)
        {
            return s;
        }
        return this;
    }
    @Override
    public String toString()
    {
        switch(this)
        {
            case HighCard:
                return "High Card";
            case OnePair:
                return "One Pair";
            case TwoPair:
                return "Two Pair";
            case ThreeOfAKind:
                return "Three Of A Kind";
            case Straight:
                return "Straight";
            case Flush:
                return "Flush";
            case FullHouse:
                return "Full House";
            case FourOfAKind:
                return "Four Of A Kind";
            case StraightFlush:
                return "Straight Flush";
            default:
                return "None";
        }
    }
    public int getValue()
    {
        switch(this)
        {
            case HighCard:
                return 3;
            case OnePair:
                return 7;
            case TwoPair:
                return 12;
            case ThreeOfAKind:
                return 25;
            case Straight:
                return 35;
            case Flush:
                return 50;
            case FullHouse:
                return 70;
            case FourOfAKind:
                return 90;
            case StraightFlush:
                return 100;
            default:
                return 0;
        }
    }
}

