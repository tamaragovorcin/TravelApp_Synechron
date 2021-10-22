
package com.example.TravelApp.Service.NBSservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthenticationHeader_QNAME = new QName("http://communicationoffice.nbs.rs", "AuthenticationHeader");

    public ObjectFactory() { }

    @XmlElementDecl(namespace = "http://communicationoffice.nbs.rs", name = "AuthenticationHeader")
    public JAXBElement<AuthenticationHeader> createAuthenticationHeader(AuthenticationHeader value) {
        return new JAXBElement<AuthenticationHeader>(_AuthenticationHeader_QNAME, AuthenticationHeader.class, null, value);
    }

}
