/*
PURPOSE:    The Test Harness Objects purpose is to stub out a main method that can be simply run which 
            in turn can call other test classes to do unit testing as we are developing this application.

            This will also allow for the agilenlp.Main method to be left alone while others work on the core program.

            TestHarness is solely for each individual developer to leverage for their own testing purposes.  And should be 
            able to be tore down at any given time for another developer to do their own "needful" :-)


*/

package TestHarness;

public class TestHarness {
    public static void main(String[] args) {
        //feel free to write your own test harness and run it from here
        //TestDataLoader.run();
        TestDataAggregator.run();
    }    
}
