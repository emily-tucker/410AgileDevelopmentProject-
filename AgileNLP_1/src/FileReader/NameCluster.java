/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* CLASS NameCluster
* -----------------------------------
* Description
PURPOSE:    Object which represents each individual name cluster and the two fields associated with the name cluster.  
            Character name and the freebase_character_id
DATA EXAMPLE
Elizabeth Swann	/m/0k1xvz
Elizabeth Swann	/m/0k1x_d
Elizabeth Swann	/m/0k1xxy
* **************************************************************************
*/  


package FileReader;

public class NameCluster {
    public String character_name = "";
    public String freebase_character_id = "";
    
        
    public NameCluster (String [] string_array)
    {

        if(string_array.length == 2)
        {
            character_name = string_array[0];
            freebase_character_id = string_array[1];
        }
        
    }
}
