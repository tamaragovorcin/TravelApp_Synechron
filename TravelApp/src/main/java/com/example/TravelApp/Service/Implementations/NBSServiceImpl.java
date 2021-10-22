package com.example.TravelApp.Service.Implementations;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.example.TravelApp.Service.Interfaces.INBSService;
import com.example.TravelApp.Service.NBSservice.AuthenticationHeader;
import com.example.TravelApp.Service.NBSservice.ExchangeRateXmlService;
import com.example.TravelApp.Service.NBSservice.ExchangeRateXmlServiceSoap;
import com.example.TravelApp.Service.NBSservice.ObjectFactory;
import org.springframework.stereotype.Service;


@Service
public class NBSServiceImpl implements INBSService {

    @Override
    public double getMiddleCourseForDate(LocalDateTime date, String currencyCode) {

        ExchangeRateXmlService proxy = new ExchangeRateXmlService();
        ExchangeRateXmlServiceSoap port = proxy.getExchangeRateXmlServiceSoap();

        final Binding binding = ((BindingProvider) port).getBinding();
        List<Handler> handlersList = new ArrayList<Handler>();

        AuthenticationHeader authHeader = new AuthenticationHeader();
        authHeader.setUserName("SynSrb");
        authHeader.setPassword("L552233syn");
        authHeader.setLicenceID("aaf394ed-07f2-4b5a-bb7b-7875934bece8");

        final ObjectFactory objectFactory = new ObjectFactory();
        final JAXBElement<AuthenticationHeader> requesterCredentials = objectFactory
                .createAuthenticationHeader(authHeader);

        handlersList.add(new SOAPHandler<SOAPMessageContext>() {
            @Override
            public boolean handleMessage(final SOAPMessageContext context) {
                try {
                    // checking whether handled message is outbound one as per Martin Strauss answer
                    final Boolean outbound = (Boolean) context.get("javax.xml.ws.handler.message.outbound");
                    if (outbound != null && outbound) {
                        // obtaining marshaller which should marshal instance to xml
                        final Marshaller marshaller = JAXBContext.newInstance(AuthenticationHeader.class)
                                .createMarshaller();
                        // adding header because otherwise it's null
                        final SOAPHeader soapHeader = context.getMessage().getSOAPPart().getEnvelope().addHeader();
                        // marshalling instance (appending) to SOAP header's xml node
                        marshaller.marshal(requesterCredentials, soapHeader);
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                return true;
            }

            @Override
            public boolean handleFault(SOAPMessageContext context) {
                return false;
            }

            @Override
            public void close(MessageContext context) {
            }

            @Override
            public Set<QName> getHeaders() {
                return null;
            }

            // ... default implementations of other methods go here

        });

        binding.setHandlerChain(handlersList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        ZoneId defaultZoneId = ZoneId.systemDefault();

        String datum = sdf.format(Date.from(date.atZone(defaultZoneId).toInstant()));

        String response = port.getExchangeRateByDate(datum, 1);
        String[] splitedResponse = response.split("\n");

        double buyingRate = 0.0;
        double sellingRate = 0.0;
        String currency = "";
        boolean goodCurrency = false;
        double unit = 1.0;
        for (int i = 0; i < splitedResponse.length; i++) {

            if (splitedResponse[i].contains("CurrencyCodeAlfaChar")) {
                if (splitedResponse[i].contains(currencyCode)) {
                    goodCurrency = true;
                    currency = splitedResponse[i].substring("    <CurrencyCodeAlfaChar>".length(),
                            "    <CurrencyCodeAlfaChar>".length() + 3);
                }
            }
            if (goodCurrency) {
                if (splitedResponse[i].contains("<Unit>") && splitedResponse[i].contains("</Unit>")) {

                    String[] splitedUnit = splitedResponse[i].split("    <Unit>");
                    String[] units = splitedUnit[1].split("</Unit>");
                    unit = Double.parseDouble(units[0]);// Double.parseDouble(splitedResponse[i].substring("
                    // <BuyingRate>".length(), " <BuyingRate>".length()+7));
                }

            }
            if (splitedResponse[i].contains("BuyingRate")) {
                if (goodCurrency) {
                    String[] splitedUnit = splitedResponse[i].split("    <BuyingRate>");
                    String[] units = splitedUnit[1].split("</BuyingRate>");
                    buyingRate = Double.parseDouble(units[0]);// Double.parseDouble(splitedResponse[i].substring("
                    // <BuyingRate>".length(), " <BuyingRate>".length()+7));
                }
            }
            if (splitedResponse[i].contains("SellingRate")) {
                if (goodCurrency) {

                    String[] splitedUnit = splitedResponse[i].split("    <SellingRate>");
                    String[] units = splitedUnit[1].split("</SellingRate>");
                    sellingRate = Double.parseDouble(units[0]);// Double.parseDouble(splitedResponse[i].substring("
                    // <BuyingRate>".length(), " <BuyingRate>".length()+7));
                    double midlleCourse = (sellingRate + buyingRate) / 2.0;

                    return roundAvoid(midlleCourse / unit, 4);
                }
            }

        }
        if (!goodCurrency) {
            String r2 = port.getExchangeRateByCurrency(784, datum, datum, 3);

        }
        return 0;

    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}
