package graficos.curso.ejercicios.a09_servicio_soap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrar(View v){
        AccesoContactos ac=new AccesoContactos();
        ac.execute();
    }

    private class AccesoContactos extends AsyncTask<Void,Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            String URL = "http://169.254.13.49:8080/01_ejemplo_soap/services/ServicioSaludo?wsdl";
            String NAMESPACE = "http://servicios";
            String SOAP_ACTION = "";
            String METHOD_NAME = "saludar";
            String PARAMETER_NAME = "name";
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName(PARAMETER_NAME);
            propertyInfo.setValue("yo");
            propertyInfo.setType(String.class);

            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                httpTransportSE.call(SOAP_ACTION, envelope);
                SoapPrimitive soapPrimitive = (SoapPrimitive)envelope.getResponse();
                System.out.println("respuestaaaaaaaaaaaaaa "+ soapPrimitive.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }

}
