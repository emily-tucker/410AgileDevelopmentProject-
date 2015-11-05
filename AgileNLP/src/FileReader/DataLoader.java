/*
 PURPOSE:    DataLoader is a simple class that exposes static methods that return array lists of entity objects
 which represent a single line read in from each entities TSV file.
 All this class does is loop over a given entity file line by line, pass the line into the constuctor
 for that entity (i.e. a movie, character, etc.) which returns the entity as an object, and then loads it into
 an array list.  The array list is then populated with that object.  Once the file is completely read and loadeded
 into an array list of entities, the array list is returned.

 */
package FileReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class DataLoader {

    protected enum DataType {

        hash_table, array_list
    };

    protected enum ObjectType {

        character, movie, name_cluster, plot_summary
    };

    private static BufferedReader GetBufferedReader(Boolean resources, String path_to_file) {
        try {
            InputStream input_stream;
            BufferedReader reader;

            if (resources) {
                input_stream = DataLoader.class.getResourceAsStream(path_to_file);
            } else {
                input_stream = new FileInputStream(path_to_file);
            }

            //load buffered reader
            reader = new BufferedReader(new InputStreamReader(input_stream));

            return reader;
        } catch (Exception ex) {
            return null;
        }
    }

    private static Object GetData(Boolean resources, String path_to_file, String splitter, DataType dt, ObjectType t) {
        try {

            Hashtable ht;
            ArrayList a = new ArrayList();
            Object o = null;

            String read_line = "";
            String key = "";
            int key_int = 0;
            String temp[];
            int i = 0;

            BufferedReader reader;
            reader = GetBufferedReader(resources, path_to_file);

            //declare the hash table explicitly
            switch (t)
            {
                case character:
                    ht = new Hashtable<String, MovieCharacter>();
                    break;
                case movie:
                    ht = new Hashtable<Integer, Movie>();
                    break;
                case plot_summary:
                    ht = new Hashtable<Integer, PlotSummary>();
                    break;
                case name_cluster:
                    ht = new Hashtable<String, NameCluster>();
                    break;
                default:
                    ht = new Hashtable();
                    break;
                
            }
            
            while ((read_line = reader.readLine()) != null) {
                temp = read_line.split(splitter);

                switch (t) {
                    case character:
                        MovieCharacter c = new MovieCharacter(temp);
                        key = c.freebase_character_id;
                        o = c;
                        break;
                    case movie:
                        Movie m = new Movie(temp);
                        key_int = m.wikipedia_movie_id;
                        o = m;
                        break;
                    case plot_summary:
                        PlotSummary ps = new PlotSummary(temp);
                        key_int = ps.wikipedia_movie_id;
                        o = ps;
                        break;
                    case name_cluster:
                        NameCluster nc = new NameCluster(temp);
                        key = java.util.UUID.randomUUID().toString();
                        o = nc;
                        break;
                    default:
                        o = null;
                        break;

                }

                //decide which data type to load
                if (o != null) {
                    switch (dt) {
                        case hash_table:
                            if (key_int > 0) {
                                ht.put(key_int, o);
                            } else {
                                ht.put(key, o);
                            }
                            break;
                        case array_list:
                            i = i + 1;
                            a.add(o);
                            break;
                        default:
                            break;
                    }
                } else {

                }

            }

            //return statements
            switch (dt) {
                case hash_table:
                    return ht;
                case array_list:
                    return a;
                default:
                    return null;
            }

        } catch (Exception ex) {
            //TODO: NOT DEALING WITH EXCEPTIONS RIGHT NOW
            return null;
        }
    }

    protected static Object GetObjectData(DataType dt, ObjectType ot) {
        String s_plot_summary_path = "/resources/plot_summaries.txt";
        String s_movie_path = "/resources/movie.metadata.tsv";
        String s_character_path = "/resources/character.metadata.tsv";
        String s_name_cluster_path = "/resources/name.clusters.txt";
        String s_path = "";

        switch (ot) {
            case character:
                s_path = s_character_path;
                break;
            case movie:
                s_path = s_movie_path;
                break;
            case name_cluster:
                s_path = s_name_cluster_path;
                break;
            case plot_summary:
                s_path = s_plot_summary_path;
                break;
            default:
                s_path = "";
                break;

        }

        Object o = GetData(true, s_path, "\t", dt, ot);
        if (o == null) {
            return null;
        } else {
            if (dt == DataType.hash_table) {
                return (Hashtable) o;
            } else if (dt == DataType.array_list) {
                ArrayList x = (ArrayList) o;
                return x;
            } else {
                return null;
            }
        }
    }

    /**
     * *************************************************************************
     * public static ArrayList GetMovies() -----------------------------------
     * GetMovies loads all Movies from movie.tsv and returns an array list of
     * movie objects
     * **************************************************************************
     */
    protected static ArrayList GetMovies() {
        return (ArrayList) GetObjectData(DataType.array_list, ObjectType.movie);
    }

    /**
     * *************************************************************************
     * public static ArrayList GetPlotSummaries()
     * ----------------------------------- GetPlotSummaries loads all
     * PlotSummaries from the plot summary.tsv and returns an array list of plot
     * summary objects
     *     
* **************************************************************************
     */
    protected static ArrayList GetPlotSummaries() {
        return (ArrayList) GetObjectData(DataType.array_list, ObjectType.plot_summary);
    }

    /*
     * *************************************************************************
     * public static ArrayList GetCharacters()
     * -----------------------------------
     * GetCharacters loads all Characters from character.tsv and returns an array list of character objects
     * 
     * **************************************************************************
     */
    protected static ArrayList GetCharacters() {
        return (ArrayList) GetObjectData(DataType.array_list, ObjectType.character);
    }

    protected static CharacterLoader GetCharacterLoader() {
        try {
            CharacterLoader cl = new CharacterLoader();
            String read_line = "";
            String key = "";
            String temp[];

            BufferedReader reader;
            reader = GetBufferedReader(true, "/resources/character.metadata.tsv");

            while ((read_line = reader.readLine()) != null) {
                if (read_line != null) {
                    temp = read_line.split("\t");

                    MovieCharacter c = new MovieCharacter(temp);

                    //decide which data type to load
                    if (c != null) {
                        cl.Add(c);
                    }
                }

            }

            return cl;
        } catch (Exception ex) {
            //TODO: NOT DEALING WITH EXCEPTIONS RIGHT NOW
            return null;
        }

    }

    /**
     * *************************************************************************
     * public static ArrayList GetNameClusters()
     * ----------------------------------- GetNameClusters loads all
     * NameClusters from the name cluster text file and returns an array list of
     * name cluster objects
     *     
* **************************************************************************
     */
    protected static ArrayList GetNameClusters() {
        return (ArrayList) GetObjectData(DataType.array_list, ObjectType.name_cluster);
    }
}
