
package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebService;

@WebService(endpointInterface = "com.mycompany.cxf.jaxws.javafirst.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}
