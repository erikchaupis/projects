package helllo.app;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import HelloApp.Hello;
import HelloApp.HelloHelper;

public class HelloClient
{
    static Hello helloImpl;
    
    public static void main(String args[])
    {
	try {
		Properties orbProperties = new Properties();
		orbProperties.setProperty("org.omg.CORBA.ORBInitialPort", "5000");
//		orbProperties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost")

	    ORB orb = ORB.init(args, orbProperties);
	    org.omg.CORBA.Object objRef = 
                     orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	    String name = "Hello";
	    helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

	    System.out.println("Got a handle on the server object: " + helloImpl);
	    System.out.println(helloImpl.sayHello());
	    helloImpl.shutdown();

	} catch(Exception e){
	    System.out.println("ERROR : " + e);
	    e.printStackTrace(System.out);
	}
    }
}
