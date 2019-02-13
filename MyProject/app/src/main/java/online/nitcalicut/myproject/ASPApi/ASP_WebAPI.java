package online.nitcalicut.myproject.ASPApi;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ASP_WebAPI {
    private static String NAMESPACE = "http://tempuri.org/";
    private static String URL = "http://ladduwalaz.com//APICategory.asmx";
    private static String SOAP_ACTION = "http://tempuri.org/";

    public static String ApiLogin(String RegMobile, String RegPassword, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo PropRegMobile = new PropertyInfo();
        PropRegMobile.setName("RegMobile");
        PropRegMobile.setValue(RegMobile);
        PropRegMobile.setType(String.class);
        request.addProperty(PropRegMobile);

        PropertyInfo PropRegPassword = new PropertyInfo();
        PropRegPassword.setName("RegPassword");
        PropRegPassword.setValue(RegPassword);
        PropRegPassword.setType(String.class);
        request.addProperty(PropRegPassword);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "Error occured";
        }
        return resTxt;
    }

    public static String ApiRegister(String RegName, String RegMobile, String RegEmail, String RegPassword, String Regstatus, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo PropRegName = new PropertyInfo();
        PropRegName.setName("RegName");
        PropRegName.setValue(RegName);
        PropRegName.setType(String.class);
        request.addProperty(PropRegName);

        PropertyInfo PropRegMobile = new PropertyInfo();
        PropRegMobile.setName("RegMobile");
        PropRegMobile.setValue(RegMobile);
        PropRegMobile.setType(String.class);
        request.addProperty(PropRegMobile);

        PropertyInfo PropRegEmail = new PropertyInfo();
        PropRegEmail.setName("RegEmail");
        PropRegEmail.setValue(RegEmail);
        PropRegEmail.setType(String.class);
        request.addProperty(PropRegEmail);

        PropertyInfo PropRegPassword = new PropertyInfo();
        PropRegPassword.setName("RegPassword");
        PropRegPassword.setValue(RegPassword);
        PropRegPassword.setType(String.class);
        request.addProperty(PropRegPassword);

        PropertyInfo PropRegstatus = new PropertyInfo();
        PropRegstatus.setName("Regstatus");
        PropRegstatus.setValue(Regstatus);
        PropRegstatus.setType(String.class);
        request.addProperty(PropRegstatus);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "Error occured";
        }
        return resTxt;
    }

    public static String ApiInsertData(String CategoryName, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo PropCategory = new PropertyInfo();
        PropCategory.setName("CategoryName");
        PropCategory.setValue(CategoryName);
        PropCategory.setType(String.class);
        request.addProperty(PropCategory);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "Error occured";
        }
        return resTxt;
    }

    public static String ApigetCategory(String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "Error occured";
        }
        return resTxt;
    }
}
