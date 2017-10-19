/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ramy
 */
public class Store 
{
    private static final int MAX_GAMES=10;
    private static final String FILENAME= "Store.bin";
    private static Store instance;
    private ArrayList<Game> games;
    private Store()
    {
        try(ObjectInputStream stream= new ObjectInputStream(new FileInputStream(FILENAME)))
        {
            games= (ArrayList<Game>) stream.readObject();   
        }
        catch(Exception e)
        {
            System.out.println("Cannot read file " + FILENAME + " : " + e);
        }
    }
    public void synchronize()
    {
        try(ObjectOutputStream stream= new ObjectOutputStream(new FileOutputStream(FILENAME)))
        {
            stream.writeObject(games);
        }
        catch(Exception e)
        {
            System.out.println("Cannot write file " + FILENAME + " : " + e);
        }
    }
    public static Store getInstance()
    {
        if(instance== null)
            instance= new Store();
        return instance;
    }
    public ArrayList<Game> getGames()
    {
        return games;
    }
}
