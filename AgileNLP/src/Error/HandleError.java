/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Error;

import java.util.Date;
import org.apache.commons.lang3.exception.ExceptionUtils;
/**
 *
 * @author bdoyle
 */
public class HandleError {
    public static void HandleError(Exception ex)
    {
        //do something with the error in a general way for the rest of the 
        String stack_trace = ExceptionUtils.getStackTrace(ex);
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        System.out.println("ERROR HAS OCCURED!");
        System.out.println("DATE TIME: " + new Date());
        System.out.println("*******************************************");
        System.out.println(stack_trace);
        System.out.println("*******************************************");
    }
}
