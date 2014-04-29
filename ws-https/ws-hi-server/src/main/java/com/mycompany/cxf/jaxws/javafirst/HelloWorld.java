package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}
