package com.example.TravelApp.Service.NBSservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.Service;

@WebServiceClient(name = "ExchangeRateXmlService",
                  wsdlLocation = "https://webservices.nbs.rs/CommunicationOfficeService1_0/ExchangeRateXmlService.asmx?WSDL",
                  targetNamespace = "http://communicationoffice.nbs.rs")
public class ExchangeRateXmlService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://communicationoffice.nbs.rs", "ExchangeRateXmlService");
    public final static QName ExchangeRateXmlServiceSoap = new QName("http://communicationoffice.nbs.rs", "ExchangeRateXmlServiceSoap");
    static {
        URL url = null;
        try {
            url = new URL("https://webservices.nbs.rs/CommunicationOfficeService1_0/ExchangeRateXmlService.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ExchangeRateXmlService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "https://webservices.nbs.rs/CommunicationOfficeService1_0/ExchangeRateXmlService.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public ExchangeRateXmlService() {
        super(WSDL_LOCATION, SERVICE);
    }

    @WebEndpoint(name = "ExchangeRateXmlServiceSoap")
    public ExchangeRateXmlServiceSoap getExchangeRateXmlServiceSoap() {
        return super.getPort(ExchangeRateXmlServiceSoap, ExchangeRateXmlServiceSoap.class);
    }

}
