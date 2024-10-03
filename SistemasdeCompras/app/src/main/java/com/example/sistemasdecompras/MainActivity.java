package com.example.sistemasdecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import android.view.*;
import android.app.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CheckBox chkMacarrao, chkExtrato, chkcarne, chkQueijo, chkCalabresa;
    Button bttotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkMacarrao = (CheckBox) findViewById(R.id.chKMacarrao);
        chkExtrato = (CheckBox) findViewById(R.id.chkExtrato);
        chkcarne = (CheckBox) findViewById(R.id.chkcarne);
        chkQueijo = (CheckBox) findViewById(R.id.chKQueijo);
        chkCalabresa = (CheckBox) findViewById(R.id.chKCalabresa);
        Button bttotal = (Button) findViewById(R.id.bttotal);

        bttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = calcularTotal();
                exibirAlerta("AVISO", "Total da sua Compra: " + String.valueOf(total));
                exibirFormaDePagamento(total);
            }
        });
    }


    private double calcularTotal() {
        double total = 0;
        if (chkMacarrao.isChecked())
            total += 8.79;
        if (chkExtrato.isChecked())
            total += 5.00;
        if (chkcarne.isChecked())
            total += 34.00;
        if (chkQueijo.isChecked())
            total += 12.00;
        if (chkCalabresa.isChecked())
            total += 35.59;

        return total;
    }

    // Método para exibir um diálogo de alerta
    private void exibirAlerta(String titulo, String mensagem) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(titulo);
        dialogo.setMessage(mensagem);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    // Método para exibir a forma de pagamento
    private void exibirFormaDePagamento(final double total) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha a forma de pagamento");
        String[] formasDePagamento = {"Cartão de Crédito", "Cartão de Debito", "Dinheiro", "Pix"};
        builder.setItems(formasDePagamento, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Aqui você pode implementar a lógica para lidar com a forma de pagamento selecionada
                String formaSelecionada = formasDePagamento[i];
                exibirAlerta("Forma de Pagamento Selecionada", "Você escolheu pagar " + total + " usando " + formaSelecionada);
            }
        });
        builder.show();
    }
}
