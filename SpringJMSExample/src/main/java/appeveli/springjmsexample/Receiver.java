/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appeveli.springjmsexample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;
import org.springframework.jms.annotation.JmsListener;

import java.io.File;
/**
 *
 * @author Dterryable
 */
public class Receiver {
    
    /*
    
    Get a copy of the application context
    
    */
    @Autowired
    ConfigurableApplicationContext context;
    /*When you receive a message, print it out, then shut dowm the application
    Finally, clean up any ActiveMQ server stuff
    */
    
    @JmsListener(destination="mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message){
        System.out.println("Received <"+message+">");
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    
    }
    
}
