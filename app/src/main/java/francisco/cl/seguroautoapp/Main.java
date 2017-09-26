package francisco.cl.seguroautoapp;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



import static android.icu.util.Calendar.YEAR;
@RequiresApi(api = Build.VERSION_CODES.N)

public class Main extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Spinner marca;
    String marca_seleccionada;
    //EditText marca;
    EditText modelo;
    EditText ano_v;
    EditText patente;
    EditText valor_UF;
    Button ingresar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marca = (Spinner) findViewById(R.id.spn_marca);
        //marca = (EditText) findViewById(R.id.edt_marca);
        modelo = (EditText) findViewById(R.id.edt_modelo);
        ano_v = (EditText) findViewById(R.id.edt_ano);
        patente = (EditText) findViewById(R.id.edt_patente);
        valor_UF = (EditText) findViewById(R.id.edt_valor_UF);
        ingresar = (Button) findViewById(R.id.btn_ingresar);
        //resultado_imagen = (ImageView) findViewById(R.id.imv_resultado);


        //Adaptador para spinner:
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca_array, android.R.layout.simple_spinner_item);
        //Especificación del layout usado cuando aparece la lista de opciones:
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //aplicación del adaptador al spinner:
        marca.setAdapter(adapter);
        //Habilita la ejecución del spinerr mediante el método "OnItemSelectedListener":
        marca.setOnItemSelectedListener(this);

        ingresar.setOnClickListener(this);


    }

    Calendar calendar = Calendar.getInstance();
    int anoActual = calendar.get(YEAR);

    @Override
    public void onClick(View v) {
        //String marca_ingresada = marca.getText().toString();
        //***
        String modelo_ingresado = modelo.getText().toString();
        int ano_ingresado = Integer.parseInt(ano_v.getText().toString());
        String patente_ingresada = patente.getText().toString();
        int valorUF_ingresado = Integer.parseInt(valor_UF.getText().toString());
        int antiguedad = anoActual - ano_ingresado;
        String estado_vehiculo = calculaValido(ano_ingresado);
        double valor_seg = calculaValorSeguro(antiguedad, valorUF_ingresado);

        Intent intento = new Intent(Main.this,activity_resultado.class);

        intento.putExtra("marca mostrar", marca_seleccionada);
        //intento.putExtra("marca mostrar", marca_ingresada);
        intento.putExtra("modelo mostrar", modelo_ingresado);
        intento.putExtra("año mostrar", ano_ingresado);
        intento.putExtra("patente mostrar", patente_ingresada);
        intento.putExtra("valor UF mostrar", valorUF_ingresado);
        intento.putExtra("antiguedad mostrar", antiguedad);
        intento.putExtra("estado mostrar", estado_vehiculo);
        intento.putExtra("valor seguro mostrar", valor_seg);


        startActivity(intento);
    }



    public String calculaValido (int ano_v){
        String estado_vehiculo;
        int antiguedad = anoActual - ano_v;

        if (antiguedad>10){
        estado_vehiculo="No asegurable 👎☹";
        }else{
        estado_vehiculo="Asegurable 👍🙂";
        }
        return estado_vehiculo;
    }


    public Double calculaValorSeguro (int antiguedad, int valorUF){
        valorUF = Integer.parseInt(valor_UF.getText().toString());
        double valor_seguro;
        if(antiguedad>10){
            valor_seguro = 0;
        }else {
            valor_seguro = (valorUF * 0.1) * antiguedad;
        }

        return valor_seguro;
    }

    @Override                                 //revisar!
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        marca_seleccionada = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
