/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ramy
 */
public class Game implements Serializable
{
    private static final long serialVersionUID = 0x1L;
    private String name;
    private int money;
    private int tax;
    private Date date;
    private static Game emptyGame;
    private boolean empty= false;


    private Game() {
        empty= true;
    }
    
    public static Game getEmptyGame()
    {
        if(emptyGame== null)
            emptyGame= new Game();
        return emptyGame;
    }
    
    public Game(String name, int money, int tax, Date date) {
        this.name = name;
        this.money = money;
        this.tax = tax;
        this.date = date;
    }
    
    /**
     * Get the value of empty
     *
     * @return the value of empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the value of tax
     *
     * @return the value of tax
     */
    public int getTax() {
        return tax;
    }

    /**
     * Get the value of money
     *
     * @return the value of money
     */
    public int getMoney() {
        return money;
    }
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }
    
    public String toString()
    {
        if(date== null)
        {
            return "Empty";
        }
        else
        {
            return name + " {money: " + money + " , tax: " + tax + " } " + date;
        }
    }

}
