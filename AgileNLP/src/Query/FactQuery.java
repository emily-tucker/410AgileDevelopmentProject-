/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import Fact.Fact;
import java.util.ArrayList;

/**
 *
 * @author bdoyle
 */

public class FactQuery {
    public Boolean FactEqual(Fact fact1, Fact fact2)
    {
        return fact1.equals(fact2);
    }

    public Boolean FactContainedWithinArrayList(Fact fact1, ArrayList<Fact> arraylist)
    {
        return arraylist.contains(fact1);
    }

    public Fact FactArrayListContainedWithinFactArrayList(ArrayList<Fact> arraylist1, ArrayList<Fact> arraylist2)
    {
        Boolean b = false;
        Fact return_fact = null;

        for(Fact f: arraylist1)
        {
            b = FactContainedWithinArrayList(f, arraylist2);
            {
                if(b)
                {
                    return_fact = f;
                    break;
                }
            }
        }
        
        return return_fact;
    }
}
