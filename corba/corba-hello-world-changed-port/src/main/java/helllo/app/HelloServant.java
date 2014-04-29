package helllo.app;

import HelloApp.*;
import org.omg.CORBA.*;     // All CORBA applications need these classes.
import org.omg.PortableServer.*; 
import org.omg.PortableServer.POA;  

class HelloServant extends HelloPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
	orb = orb_val;
    }

    public String sayHello() {
	return "\nHello Edinburgh!!\n";
    }

    public void shutdown() {
	orb.shutdown(false);
    }
}
