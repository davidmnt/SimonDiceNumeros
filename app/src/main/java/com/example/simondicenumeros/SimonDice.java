package com.example.simondicenumeros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SimonDice extends AppCompatActivity {

    View popupView = null;
    Dialog dialog = null;

    TextView puntuacionTotales = null;
    Button btn_reset = null;

    String boton0 = "";
    String boton1 = "";
    int cont = 0;
    Random random = new Random();
    int aleatorio = 0;
    boolean gameover = false;
    MediaPlayer player_Uno = null;
    MediaPlayer player_cero = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_dice);
        Button btn_cero = findViewById(R.id.btn_cero);
        Button btn_uno = findViewById(R.id.btn_uno);
        TextView puntosPrincipales = findViewById(R.id.Puntos);

        popupView = getLayoutInflater().inflate(R.layout.activity_game_over, null);
        dialog = new Dialog(this);
        dialog.setContentView(popupView);

        puntuacionTotales = popupView.findViewById(R.id.Puntuacion);
        btn_reset = popupView.findViewById(R.id.reset);

        player_Uno = MediaPlayer.create(this, R.raw.uno);
        player_cero = MediaPlayer.create(this, R.raw.cero);

        boton0 = btn_cero.getText().toString();
        boton1 = btn_uno.getText().toString();

        int numReproducido = NumAleatorio();

        Log.e("Num random", String.valueOf(aleatorio));

        if(numReproducido == 1){

            player_Uno.start();

        }else {

            player_cero.start();
        }

        if(gameover == true){
            NumAleatorio();
        }


        btn_cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int resultBoton0 = Integer.parseInt(boton0);

                if(aleatorio == resultBoton0){
                    NumAleatorio();
                    Log.e("Num random", String.valueOf(aleatorio));
                    cont++;
                    puntosPrincipales.setText("Puntuacion: " + cont);
                    gameover = false;
                }else{
                    gameOver();

                }
            }
        });

        btn_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int resultBoton1 = Integer.parseInt(boton1);

                if(aleatorio == resultBoton1){

                    NumAleatorio();
                    Log.e("Num random", String.valueOf(aleatorio));
                    cont++;
                    puntosPrincipales.setText("Puntuacion: " + cont);
                    gameover = false;

                }else{
                    gameOver();
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                puntosPrincipales.setText("Puntuacion : 0");
            }
        });



    }

    private int NumAleatorio(){

        Random random = new Random();
        aleatorio = random.nextInt(2);
        return aleatorio;

    }

    private void gameOver(){

        dialog.show();
        puntuacionTotales.setText("Puntuacion: " + cont);

    }


}