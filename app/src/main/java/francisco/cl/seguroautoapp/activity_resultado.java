package francisco.cl.seguroautoapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_resultado extends AppCompatActivity {

    TextView resultado;
    ImageView imagen_resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        resultado = (TextView) findViewById(R.id.txv_resultado);
        imagen_resultado = (ImageView) findViewById(R.id.imv_resultado);

        Intent intento = getIntent();

        Bundle datos_recibidos = intento.getExtras();
        String marca_mostrar = datos_recibidos.getString("marca mostrar");
        //String marca_mostrar = datos_recibidos.getString("marca mostrar");
        String modelo_mostrar = datos_recibidos.getString("modelo mostrar");
        int ano_mostrar = datos_recibidos.getInt("año mostrar");
        String patente_mostrar = datos_recibidos.getString("patente mostrar");
        int valor_UF_mostrar = datos_recibidos.getInt("valor UF mostrar");
        int antiguedad_mostrar = datos_recibidos.getInt("antiguedad mostrar");
        String estado_mostrar =  datos_recibidos.getString("estado mostrar");
        double valor_seguro_mostrar = datos_recibidos.getDouble("valor seguro mostrar");


        resultado.setText("Marca: "+marca_mostrar+"\n"+"Modelo: "+modelo_mostrar+"\n"+
                "Año: "+ano_mostrar+"\n"+"Patente: "+patente_mostrar+"\n"+"Valor UF: $"+
                valor_UF_mostrar+"\n"+"Antiguedad: "+antiguedad_mostrar+" años"+"\n"+"Estado: "+
                estado_mostrar+"\n"+"Valor Seguro: $"+valor_seguro_mostrar+".\n");

        if(antiguedad_mostrar>10){
            imagen_resultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nook));
        }else{
            imagen_resultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ok));
        }
    }


}
