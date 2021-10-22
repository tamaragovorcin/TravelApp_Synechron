package com.example.TravelApp.Service.NBSservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(targetNamespace = "http://communicationoffice.nbs.rs", name = "ExchangeRateXmlServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface ExchangeRateXmlServiceSoap {

    @WebMethod(operationName = "GetExchangeRateByCurrency", action = "http://communicationoffice.nbs.rs/GetExchangeRateByCurrency")
    @RequestWrapper(localName = "GetExchangeRateByCurrency", targetNamespace = "http://communicationoffice.nbs.rs", className = "nbs.service.GetExchangeRateByCurrency")
    @ResponseWrapper(localName = "GetExchangeRateByCurrencyResponse", targetNamespace = "http://communicationoffice.nbs.rs", className = "nbs.service.GetExchangeRateByCurrencyResponse")
    @WebResult(name = "GetExchangeRateByCurrencyResult", targetNamespace = "http://communicationoffice.nbs.rs")
    java.lang.String getExchangeRateByCurrency(

            @WebParam(name = "currencyCode", targetNamespace = "http://communicationoffice.nbs.rs")
                    int currencyCode,
            @WebParam(name = "dateFrom", targetNamespace = "http://communicationoffice.nbs.rs")
                    java.lang.String dateFrom,
            @WebParam(name = "dateTo", targetNamespace = "http://communicationoffice.nbs.rs")
                    java.lang.String dateTo,
            @WebParam(name = "exchangeRateListTypeID", targetNamespace = "http://communicationoffice.nbs.rs")
                    int exchangeRateListTypeID
    );

    @WebMethod(operationName = "GetExchangeRateByDate", action = "http://communicationoffice.nbs.rs/GetExchangeRateByDate")
    @RequestWrapper(localName = "GetExchangeRateByDate", targetNamespace = "http://communicationoffice.nbs.rs", className = "nbs.service.GetExchangeRateByDate")
    @ResponseWrapper(localName = "GetExchangeRateByDateResponse", targetNamespace = "http://communicationoffice.nbs.rs", className = "nbs.service.GetExchangeRateByDateResponse")
    @WebResult(name = "GetExchangeRateByDateResult", targetNamespace = "http://communicationoffice.nbs.rs")
    java.lang.String getExchangeRateByDate(

        @WebParam(name = "date", targetNamespace = "http://communicationoffice.nbs.rs")
        java.lang.String date,
        @WebParam(name = "exchangeRateListTypeID", targetNamespace = "http://communicationoffice.nbs.rs")
        int exchangeRateListTypeID
    );

}
