package helllo.app;

import HelloApp.*;
import org.omg.CosNaming.*; // HelloClient will use the naming service.
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;     // All CORBA applications need these classes.

public class HelloClient
{
    static Hello helloImpl;
    
    public static void main(String args[])
    {
	try {
	    ORB orb = ORB.init(args, null);
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
