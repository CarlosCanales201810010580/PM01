package com.uth.pm01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uth.pm01.Procesos.SQLiteConexion;
import com.uth.pm01.Procesos.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id,nombres,apellidos,edad,correo;
    Button btnconsulta,btneliminar,btnactualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        init();
        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarEmpleado();
            }
        });

    }
    private void Eliminar()
    {

    }
    private void BuscarEmpleado()
    {
        SQLiteDatabase db=conexion.getWritableDatabase();
        /*Parametros de BUSQUEDA de la sentencia SELECT*/
        String [] params = {id.getText().toString()};

        /*Campos a retornar de la sentencia SELECT*/

        String [] fields = {Transacciones.nombres,
                            Transacciones.apellidos,
                            Transacciones.correo,
                            Transacciones.edad};

        String WhereCondition = Transacciones.id + "?";

        Cursor cdata = db.query(Transacciones.tablaEmpleados, fields,
                                WhereCondition, params,null,null,null);
        cdata.moveToFirst();
        nombres.setText(cdata.getString(0));
        apellidos.setText(cdata.getString(1));
        correo.setText(cdata.getString(2));
        edad.setText(cdata.getString(3));

        Toast.makeText(getApplicationContext(),"Consultado con exito",Toast.LENGTH_LONG).show();

    }

    private void init()
    {
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1 );

        btnconsulta=(Button)findViewById(R.id.btnbuscar);
        btneliminar=(Button)findViewById(R.id.btneliminar);
        btnactualizar=(Button)findViewById(R.id.btnactualizar);

        id=(EditText)findViewById(R.id.txtcid);
        nombres=(EditText)findViewById(R.id.txtcnombres);
        apellidos=(EditText)findViewById(R.id.txtcapellidos);
        edad=(EditText)findViewById(R.id.txtcedad);
        correo=(EditText)findViewById(R.id.txtccorreo);
    }
}