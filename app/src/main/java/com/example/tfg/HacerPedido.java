package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tfg.db.DbExecute;

import java.util.ArrayList;

//para el pdf
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import org.w3c.dom.Document;

public class HacerPedido extends AppCompatActivity {

    //variables

    DbExecute dbExecute;
    Spinner spEmples, spClie, spFab, spProd;
    String material = "", aux, clie, nombre = "", texto = "", emple = "", fab = "";
    EditText cantidad, fecha, direccion;
    int precio = 0;

    //boton PDF.
    Button generatePDFbtn;

    //ancho y altura del fichero
    int pageHeight = 1120;
    int pagewidth = 792;

    //para guardar imagenes
    Bitmap bmp, scaledbmp;

    //constante para ceder permiso
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_pedido);

        cantidad = findViewById(R.id.editTextNumber);

        añadirEmples();//lanza metodos al ejecutarse
        añadirClie();
        añadirFab();
        añadirProd();

        //obtiene el item seleccionado en los spinner
        spProd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //se selecciona un item
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aux = (String) spProd.getSelectedItem();
                System.out.println("producto " + aux);
            }
            //no se selecciona nada
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        spEmples.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                emple = (String) spEmples.getSelectedItem();
                System.out.println("emple " + emple);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        spClie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clie = (String) spClie.getSelectedItem();
                System.out.println("cliente " + clie);
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });

        spFab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fab = (String) spFab.getSelectedItem();
                System.out.println("fab " + fab);
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nada
            }
        });



        System.out.println("texto: " + texto);

        //inicio variables
        fecha = findViewById(R.id.editTextDate);
        direccion = findViewById(R.id.direccion);
        generatePDFbtn = findViewById(R.id.generatePDFbtn);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 491, 138, false);

        //comprobar permisos
        if (checkPermission()) {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

    }


    //al pulsar boton se lanza
    public void generarPDF(View view) {
        //se crea objeto para el documento
        PdfDocument pdfDocument = new PdfDocument();

        //variables para decorar el pdf, paint para los bordes y title para el titulo
        Paint paint = new Paint();
        Paint title = new Paint();

        //informacion de la pagina del pdf, alto, ancho y num de pag
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        //estableces la pagina de inicio del pdf
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        //variable para el canvas del pdf
        Canvas canvas = myPage.getCanvas();

        //para poner la imagen en el pdf
        //left es la posicion a la derecha
        //top posicion al alto
        //pasas la variable paint para dibujarlo
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        //añadimos estilo al titulo del pdf
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        //tamaño del texto
        title.setTextSize(15);

        //color del texto
        title.setColor(ContextCompat.getColor(this, R.color.black));

        //para escribir texto
        //primero el texto, x posicion de inicio, y posicion de alto y por ultimo el titulo
        canvas.drawText("Presupuesto del pedido", 100, 220, title);
        canvas.drawText("Aplicación de TFG", 100, 260, title);

        //creamos otro texto y le damos tamaño, colo y forma
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        texto = "Empleado: " + emple;
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 300, title);

        texto = "Cliente: " + clie;
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 330, title);

        texto = "Fecha: " + fecha.getText().toString();
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 360, title);

        texto = "Fabricamte: " + fab;
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 390, title);

        texto = "Dirección: " + direccion.getText().toString();
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 420, title);

        texto = "Material: " + material;
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 450, title);

        texto = "Precio: " + precio;
        //ponemos el texto al centro del pdf
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(texto, 396, 480, title);




        //terminamos la página
        pdfDocument.finishPage(myPage);

        String[] arrayCliente = clie.split("-");
        nombre = arrayCliente[1]; //para obtener el nombre del cliente que hace el pedido

        //nombre del PDF
        File file = new File(Environment.getExternalStorageDirectory(), "Presupuesto" + nombre + ".pdf");

        try {
            //escribirmos sobre el pdf
            pdfDocument.writeTo(new FileOutputStream(file));

            //mensaje de confirmacion
            Toast.makeText(HacerPedido.this, "PDF generado", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //controlamos errores
            e.printStackTrace();
        }
        //cerramos la conexion con el pdf
        pdfDocument.close();
    }

    private boolean checkPermission() {
        //comprueba permisos
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        //da permisos cuando no los tiene
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                //se le muestra al usuario que los permisos estan concedidos o no
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    public void añadirEmples(){

        spEmples = findViewById(R.id.spinnerEmple);//inicias spinner
        dbExecute = new DbExecute(HacerPedido.this);//enlazas base de datos
        ArrayList<String> arr = new ArrayList<String>();//creas array con datos

        arr = dbExecute.selectEmples();//el array recibe los valores

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);//el array le pasa los valores al spinner

        spEmples.setAdapter(adapter);//estableces el array
    }

    public void añadirClie(){

        spClie = findViewById(R.id.spinnerClie);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectClie();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spClie.setAdapter(adapter);
    }

    public void añadirFab(){

        spFab = findViewById(R.id.spinnerFab);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectFab();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spFab.setAdapter(adapter);
    }

    public void añadirProd(){

        spProd = findViewById(R.id.spinnerProd);
        dbExecute = new DbExecute(HacerPedido.this);
        ArrayList<String> arr = new ArrayList<String>();

        arr = dbExecute.selectProd();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);

        spProd.setAdapter(adapter);
    }

    //cada material añadido se suma a la variable
    public void añadirMaterial(View view)
    {
        int precioUni = 0;

        String[] mat = aux.split("-");//separas los valores obtenidos para coger el que necesitamos

        material = material + mat[1] + " x " + cantidad.getText().toString() + "\n";//sumamos el material y la cantidad seleccionada

        precioUni = dbExecute.selectPrecio(Integer.parseInt(mat[0]));//obtenemos el precio de cada uno de los materiales

        precio = (precioUni * Integer.parseInt(cantidad.getText().toString())) + precio;//multiplicamos la cantidad por la unidad, para obtener el total

        System.out.println("precio " + precio);

        System.out.println("material " + material);

        Toast.makeText(this, "Material añdido", Toast.LENGTH_SHORT).show();
    }

}