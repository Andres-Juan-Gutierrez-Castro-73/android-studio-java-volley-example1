package com.example.javavolleylibrary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String IMAGE_URL = "https://source.unsplash.com/random"; // URL para obtener una imagen aleatoria

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView); // Referencia al ImageView en el archivo de diseño

        // Crear una instancia de RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Crear una solicitud GET con ImageRequest para obtener una imagen
        ImageRequest request = new ImageRequest(IMAGE_URL, new Response.Listener<android.graphics.Bitmap>() {
            @Override
            public void onResponse(android.graphics.Bitmap response) {
                // Manejar la respuesta exitosa
                imageView.setImageBitmap(response); // Establecer la imagen en el ImageView
            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar el error de la solicitud
                Log.e(TAG, "Error en la solicitud: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Agregar la solicitud a la cola de solicitudes
        requestQueue.add(request);
    }
}