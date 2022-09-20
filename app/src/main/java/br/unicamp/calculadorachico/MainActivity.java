package br.unicamp.calculadorachico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.google.common.primitives.Chars;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.primitives.Chars;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    PilhaLista<Character> umaPilha = new PilhaLista<Character>(); //new PilhaVetor<char>(200);

    EditText edtValores,edtResultado;
    TextView tvSequencias;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
            btnElev, btnDiv, btnMult, btnSub, btnAdc, btnPoint, btnAbre,
            btnFecha, btnLimparTudo, btnIgual,btnLimpaUm;

    String valor;
    String[] valorDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValores = findViewById(R.id.edtValores);
        edtResultado = findViewById(R.id.edtResultado);
        tvSequencias = findViewById(R.id.tvSequencias);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);

        btnElev = findViewById(R.id.btnElev);
        btnDiv = findViewById(R.id.btnDiv);
        btnMult = findViewById(R.id.btnMult);
        btnSub = findViewById(R.id.btnSub);
        btnAdc = findViewById(R.id.btnAdc);
        btnPoint = findViewById(R.id.btnPoint);
        btnAbre = findViewById(R.id.btnAbre);
        btnFecha = findViewById(R.id.btnFecha);
        btnLimparTudo = findViewById(R.id.btnLimparTudo);
        btnIgual = findViewById(R.id.btnIgual);
        btnLimpaUm = findViewById(R.id.btnLimpaUm);

        btn1.setOnClickListener(this::onClick);
        btn2.setOnClickListener(this::onClick);
        btn3.setOnClickListener(this::onClick);
        btn4.setOnClickListener(this::onClick);
        btn5.setOnClickListener(this::onClick);
        btn6.setOnClickListener(this::onClick);
        btn7.setOnClickListener(this::onClick);
        btn8.setOnClickListener(this::onClick);
        btn9.setOnClickListener(this::onClick);
        btn0.setOnClickListener(this::onClick);

        btnElev.setOnClickListener(this::onClick);
        btnDiv.setOnClickListener(this::onClick);
        btnMult.setOnClickListener(this::onClick);
        btnSub.setOnClickListener(this::onClick);
        btnAdc.setOnClickListener(this::onClick);
        btnPoint.setOnClickListener(this::onClick);
        btnAbre.setOnClickListener(this::onClick);
        btnFecha.setOnClickListener(this::onClick);
        btnLimparTudo.setOnClickListener(this::onClick);
        btnIgual.setOnClickListener(this::onClick);
        btnLimpaUm.setOnClickListener(this::onClick);

        valor = "";
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                valor += "1";
                break;
            case R.id.btn2:
                valor += "2";
                break;
            case R.id.btn3:
                valor += "3";
                break;
            case R.id.btn4:
                valor += "4";
                break;
            case R.id.btn5:
                valor += "5";
                break;
            case R.id.btn6:
                valor += "6";
                break;
            case R.id.btn7:
                valor += "7";
                break;
            case R.id.btn8:
                valor += "8";
                break;
            case R.id.btn9:
                valor += "9";
                break;
            case R.id.btn0:
                valor += "0";
                break;
            case R.id.btnElev:
                valor += " ^ ";
                break;
            case R.id.btnDiv:
                valor += " / ";
                break;
            case R.id.btnMult:
                valor += " * ";
                break;
            case R.id.btnSub:
                valor += " - ";
                break;
            case R.id.btnAdc:
                valor += " + ";
                break;
            case R.id.btnPoint:
                valor += ".";
                break;
            case R.id.btnAbre:
                valor += "( ";
                break;
            case R.id.btnFecha:
                valor += " )";
                break;
            case R.id.btnLimparTudo:
                valor = "";
                edtResultado.setText("");
                tvSequencias.setText("Infixa e Posfixa");
                break;
            case R.id.btnIgual:

                if(Balanceamento(edtValores.getText().toString()))
                    Igual();
                break;
            case R.id.btnLimpaUm:
                valor = valor.substring(0, valor.length() - 2);
                break;
        }
        edtValores.setText(valor);
    }

    public void Igual(){
        //Verificamos se txtVisor está vazio e se a expressão está balanceada
        tvSequencias.setText("");
        if (edtValores.getText().length() > 0)
        {
            String expressao = edtValores.getText().toString();
            String[] values = expressao.split(" "); //separamos os valores por espaço

            char letra = 'A';
            //Novo vetor com os valores pois iria se perder o primeiro vetor quando colocassemos as letras
            edtResultado.setText(ConverterInfixaParaPosfixa(expressao));
            String replace = edtResultado.getText().toString().replace("   ", "  ");
            String replaceAll = replace.replace("  ", " ");
            String[] valuesNaPosfixa = replaceAll.split(" ");

            //Variável global  recebe o conteudo desse vetor
            valorDe = valuesNaPosfixa;

            String finall = " ";

            for (int i = 0; i < valorDe.length; i++) {
                finall += valorDe[i];
            }

            //Foreach que atribui letras as posições do primeiro vetor
            int j = 0;

            for (String caracter:values) {
                if (!"+-*/^()".contains(values[j]))
                {
                    tvSequencias.setText(tvSequencias.getText() + String.valueOf(letra));
                    String letra2 = String.valueOf(letra++);
                    values[j] = letra2;
                }
                else
                {
                    tvSequencias.setText(tvSequencias.getText() + values[j].toString());
//                    tvSequencias.setText(new StringBuilder().append("").append(values[j].toString()).toString());
                }
                j++;
            }
            //Exibe expressão posfixa no lbSequencias e resultado no txtResultado
            ConverterInfixaParaPosfixa(tvSequencias.getText().toString());
            edtResultado.setText("" + ValorDaExpressaoPosfixa(valorDe));
            tvSequencias.setText(tvSequencias.getText() + "------------" + ConverterInfixaParaPosfixa(tvSequencias.getText().toString()));
        }
        else
        {
            tvSequencias.setText("Digite a expressão!");
        }
    }

    public String ConverterInfixaParaPosfixa(String cadeiaLida)
    {
        String resultado = "";
        for (int indice = 0; indice < cadeiaLida.length(); indice++)
        {
            char simboloLido = cadeiaLida.charAt(indice);
            if (!EhOperador(simboloLido)) // not In [‘(‘,’)’,’+’,’-‘,’*’,’/’,’’]
                resultado += simboloLido; // escreve Operando na saída
            else // operador
            {
                boolean parar = false;
                try {
                    while (!parar && !umaPilha.EstaVazia() && TerPrecedencia(umaPilha.OTopo()) <= TerPrecedencia(simboloLido))
                    {
                        try{
                            char operadorComMaiorPrecedencia = umaPilha.Desempilhar();
                            if (operadorComMaiorPrecedencia != '(')
                                resultado += " " + operadorComMaiorPrecedencia;
                            else
                            {
                                umaPilha.Empilhar(operadorComMaiorPrecedencia);
                                parar = true;
                            }
                        }catch (Exception err){}
                    }
                }catch (Exception err){}

                if (simboloLido != ')')
                    umaPilha.Empilhar(simboloLido);
                else // fará isso QUANDO o Pilha[TOPO] = ‘(‘
                {
                    try{
                        char operadorComMaiorPrecedencia;
                        operadorComMaiorPrecedencia = umaPilha.Desempilhar();
                    }catch (Exception err){}
                }
            }
        } // for
        while (!umaPilha.EstaVazia()) // Descarrega a Pilha Para a Saída
        {
            try{
                char operadorComMaiorPrecedencia;
                operadorComMaiorPrecedencia = umaPilha.Desempilhar();
                if (operadorComMaiorPrecedencia != '(')
                    resultado += " " + operadorComMaiorPrecedencia;
            }catch (Exception err){}
        }
        return resultado;
    }
    double ValorDaExpressaoPosfixa(String[] cadeiaPosfixa)
    {
        PilhaLista<Double> umaPilha = new PilhaLista<Double>();
        for (int atual = 0; atual < cadeiaPosfixa.length; atual++)
        {
            String simbolo = cadeiaPosfixa[atual];
            if (!"+-^/*()".contains(simbolo)) // É Operando
                umaPilha.Empilhar(Double.parseDouble(valorDe[atual]));
            else
            {
                try {
                    double operando2 = umaPilha.Desempilhar();
                    double operando1 = umaPilha.Desempilhar();
                    double valor = ValorDaSubExpressao(operando1, simbolo, operando2);
                    umaPilha.Empilhar(valor);
                }catch (Exception err){}
            }
        }

        return umaPilha.Desempilhar();
    }
    boolean EhOperador(char caracter)
    {
        char[] operadores = {'+', '-', '^', '/', '*', '(', ')'};
        return Chars.contains(operadores, caracter);
    }

    public static int TerPrecedencia(char op)
    {
        int p = 0;
        switch (op)
        {
            case '(': p = 1; break;
            case '^': p = 2; break;
            case '*':
            case '/': p = 3; break;
            case '+':
            case '-': p = 4; break;
            case ')': p = 5; break;
        }
        return (p);
    }

    double ValorDaSubExpressao(double op, String simbolo, double op2)
    {
        double resultado = 0;
        switch (simbolo)
        {
            case "^":
                resultado = Math.pow(op, op2);
                break;

            case "*":
                resultado = op * op2;
                break;

            case "/":
                resultado = op / op2;
                break;

            case "+":
                resultado = op + op2;
                break;

            case "-":
                resultado = op - op2;
                break;
        }
        return resultado;
    }

    boolean EhAbertura(char caracter)
    {
        char[] aberturas = {'{', '[', '('};
        return Chars.contains(aberturas, caracter);
    }

    boolean EhFechamento(char caracter)
    {
        char[] fechamentos = {'}', ']', ')'};
        return Chars.contains(fechamentos, caracter);
    }

    boolean Combinam(char anterior, char atual)
    {
        return anterior == '{' && atual == '}' ||
                anterior == '(' && atual == ')' ||
                anterior == '[' && atual == ']';
    }

    boolean Balanceamento(String expressao)
    {
        if (expressao != "")
        {
            PilhaLista<Character> pilha = new PilhaLista<Character>(); //new PilhaVetor<char>(200);
            String cadeia = expressao;
            boolean erro = false;
            int abertos = 0;
            int fechados = 0;
            for (int i = 0; i < cadeia.length() && !erro; i++)
            {
                char caracterLido = cadeia.charAt(i);
                if (EhAbertura(caracterLido))
                {
                    abertos++;
                    pilha.Empilhar(caracterLido);
                }
                else
                {
                    if (EhFechamento(caracterLido))
                    {
                        fechados++;
                        char aberturaAnterior = ' ';
                        try
                        {
                            aberturaAnterior = pilha.Desempilhar();
                        }
                        catch (Exception ex)
                        {
                            tvSequencias.setText("expressao nao balanceada!");
                            erro = true;
                        }
                        if (!Combinam(aberturaAnterior, caracterLido))
                        {
                            tvSequencias.setText("expressao nao balanceada!");
                            erro = true;
                        }
                    }
                }
            }
            if (abertos != fechados)
            {
                tvSequencias.setText("expressao nao balanceada!");
                erro = true;
            }
            else
            {
                return true;
            }
        }
        return false;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                edtValores.setText(edtValores.getText() + "1");
                return true;
            case KeyEvent.KEYCODE_2:
                edtValores.setText(edtValores.getText() + "2");
                return true;
            case KeyEvent.KEYCODE_3:
                edtValores.setText(edtValores.getText() + "3");
                return true;
            case KeyEvent.KEYCODE_4:
                edtValores.setText(edtValores.getText() + "4");
                return true;
            case KeyEvent.KEYCODE_5:
                edtValores.setText(edtValores.getText() + "5");
                return true;
            case KeyEvent.KEYCODE_6:
                edtValores.setText(edtValores.getText() + "6");
                return true;
            case KeyEvent.KEYCODE_7:
                edtValores.setText(edtValores.getText() + "7");
                return true;
            case KeyEvent.KEYCODE_8:
                if(event.isShiftPressed()){
                    edtValores.setText(edtValores.getText() + " * ");
                }else{
                    edtValores.setText(edtValores.getText() + "8");
                }
                return true;
            case KeyEvent.KEYCODE_9:
                if(event.isShiftPressed()){
                    edtValores.setText(edtValores.getText() + "( ");
                }else{
                    edtValores.setText(edtValores.getText() + "9");
                }
                return true;
            case KeyEvent.KEYCODE_0:
                if(event.isShiftPressed()){
                    edtValores.setText(edtValores.getText() + " )");
                }else{
                    edtValores.setText(edtValores.getText() + "0");
                }
                return true;
            case KeyEvent.KEYCODE_MINUS:
                if(event.isShiftPressed()){
                    edtValores.setText(edtValores.getText() + " - ");
                }
                return true;
            case KeyEvent.KEYCODE_EQUALS:
                if(event.isShiftPressed()){
                    edtValores.setText(edtValores.getText() + " + ");
                }
                return true;
            case KeyEvent.KEYCODE_PERIOD:
                edtValores.setText(edtValores.getText() + ".");
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}