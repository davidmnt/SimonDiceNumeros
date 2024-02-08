package com.example.simondicenumeros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SimonDice extends AppCompatActivity {

    View popupView = null;
    Dialog dialog = null;

    TextView puntuacionTotales = null;
    Button btn_reset = null;
    String boton0 = "";
    String boton1 = "";
    Button btn_cero = null;
    Button btn_uno = null;
    int cont = 0;
    Random random = new Random();
    boolean gameover = false;
    boolean looping = false;
    MediaPlayer media = null;

    ArrayList<Integer> arrayNumeros = new ArrayList<Integer>();
    int numReproducido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_dice);
        btn_cero = findViewById(R.id.btn_cero);
        btn_uno = findViewById(R.id.btn_uno);
        TextView puntosPrincipales = findViewById(R.id.Puntos);

        popupView = getLayoutInflater().inflate(R.layout.activity_game_over, null);
        dialog = new Dialog(this);
        dialog.setContentView(popupView);

        puntuacionTotales = popupView.findViewById(R.id.Puntuacion);
        btn_reset = popupView.findViewById(R.id.reset);


        boton0 = btn_cero.getText().toString();
        boton1 = btn_uno.getText().toString();

        reproducirAudio();

            btn_cero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int resultBoton0 = Integer.parseInt(boton0);

                    if (numReproducido == resultBoton0) {

                        Log.e("Acertado", "yes");
                        cont++;
                        puntosPrincipales.setText("Puntuacion: " + cont);
                        gameover = false;
                        reproducirAudio();
                    } else {

                        gameOver();

                    }
                }
            });


        btn_uno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int resultBoton1 = Integer.parseInt(boton1);

                    if (numReproducido == resultBoton1) {

                        Log.e("Acertado", "yes");
                        cont++;
                        puntosPrincipales.setText("Puntuacion: " + cont);
                        gameover = false;
                        reproducirAudio();

                    } else {
                        gameOver();
                    }
                }
            });


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                puntosPrincipales.setText("Puntuacion : 0");
                arrayNumeros.clear();
                cont = 0;
                reproducirAudio();

            }
        });

    }


    private int NumAleatorio(){
        int aleatorio = 0;
        Random random = new Random();
        aleatorio = random.nextInt(2);

        Log.e("Num random", String.valueOf(aleatorio));
        return aleatorio;

    }

    private void gameOver(){
        gameover = true;
        dialog.show();
        puntuacionTotales.setText("Puntuacion: " + cont);

    }

    private int numAleatorio(){
        numReproducido = NumAleatorio();

        return numReproducido;
    }

    private void reproducirAudio(){

        int num = numAleatorio();

        arrayNumeros.add(num);

        for(int numeros : arrayNumeros){

            if(numeros == 1){
                media = MediaPlayer.create(this,R.raw.uno);

            }else{
                media = MediaPlayer.create(this,R.raw.cero);

            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    media.start();
                }
            }, 100);

            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });

        }


    }

    private void reproducirSecuencia() {
        // Reproduce la secuencia de números una vez
        reproducirAudio();

        // Repite la secuencia indefinidamente
        while (true) {
            // Espera un tiempo antes de volver a reproducir la secuencia
            try {
                Thread.sleep(1000); // Espera 1 segundo antes de reproducir la secuencia nuevamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Reproduce la secuencia de números nuevamente
            reproducirAudio();
        }
    }


}