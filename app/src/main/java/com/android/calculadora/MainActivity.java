package com.android.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_valor;
    private TextView tv_porcentagem, tv_gorjeta, tv_total;
    private SeekBar sk_gorjeta;
    private double porcentagem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_valor = findViewById(R.id.et_valor);
        tv_porcentagem = findViewById(R.id.tv_porcentagem);
        tv_gorjeta = findViewById(R.id.tv_gorjeta);
        tv_total = findViewById(R.id.tv_total);
        sk_gorjeta = findViewById(R.id.sb_gorjeta);

        //Adicionar Listener Seekbar
        sk_gorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i; //i = progresso
                tv_porcentagem.setText(Math.round(porcentagem) + " % ");
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    public void calcular(){
        String valor_recuperado = et_valor.getText().toString();
        if (valor_recuperado == null || valor_recuperado.equals(" ")){
            Toast.makeText(
                    getApplicationContext(),
                    "Favor digitar um valor.",
                    Toast.LENGTH_LONG
            ).show();
        }else{
            // Converter string para double
            double valor_digitado = Double.parseDouble(valor_recuperado);

            // Cálculo da gorjeta total
            double gorjeta = valor_digitado * (porcentagem/100);
            double total = gorjeta + valor_digitado;

            // Exibir a gorjeta e o total
            tv_gorjeta.setText("R$: " + Math.round(gorjeta));
            tv_total.setText("R$: " + total );
        }
    }
}