/*
PURPOSE:    Object which represents each individual plot summary and all fields associated with the plot summary.
            There are only two fields, a wikipedia_movie_id and summary


DATA EXAMPLE
15765481	Jill Masters  has not seen or heard from her ex-lover, Roderick "Rick" Usher , or her best friend, Madeline "Maddy" Usher, for three years. One night, Rick contacts Jill and informs her of Maddy's sudden death. Her last wish was for Jill to attend the funeral. Conflicted, Jill returns to the family home of the Usher family. Her love affair with Rick is rekindled as she learns he suffers from the same malady that robbed his twin sister, Maddy, of her sharp mind before taking her life. His affliction is manifested in a rare nerve condition, which renders him hypersensitive. Under the watchful eye of the caretaker, Nurse Thatcher , Jill appears to be haunted by the ghost of Maddy. In the meantime, Jill becomes intimate with Rick and tells him she has missed her period. A pregnancy test confirms Jill is pregnant. In the meantime, Jill has discovered that the Usher family has practiced incest for the past five or six generations, right down to Maddy and Rick. All the prior generations had twins, who later became a couple and birthed twins of their own and so on down the line to Maddy and Rick, who were to continue the Usher curse of incest and inbreeding.
*/


package agilenlp;

public class PlotSummary {
    public int wikipedia_movie_id = 0;
    public String summary = "";
    
        
    public PlotSummary (String [] string_array)
    {
    //languages
    //countries
    //genres
        if(string_array.length == 2)
        {
            wikipedia_movie_id = Integer.parseInt(string_array[0]);
            summary = string_array[1];
        }
        
    }
}
