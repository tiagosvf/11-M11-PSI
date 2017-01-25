package net.esen.aluno.okstudent;

import android.app.Activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    private FragmentManager supportFragmentManager;

    //VARIAVEIS

    boolean resultado;
    boolean erros;
    boolean erros_fulfill;
    boolean maior;
    String universidade;
    int idade;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.Universidades,android.R.layout.simple_spinner_item );

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //TORNAR AS LABELS DE ERRO INVISIVEIS
        TextView lbl_erro_idade= (TextView)findViewById(R.id.lbl_erro_idade);
        lbl_erro_idade.setVisibility(View.INVISIBLE);
        TextView lbl_erro_secundario= (TextView)findViewById(R.id.lbl_erro_secundario);
        lbl_erro_secundario.setVisibility(View.INVISIBLE);
        TextView lbl_erro_ingles= (TextView)findViewById(R.id.lbl_erro_ingles);
        lbl_erro_ingles.setVisibility(View.INVISIBLE);
        TextView lbl_erro_nome= (TextView)findViewById(R.id.lbl_erro_nome);
        lbl_erro_nome.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText= (TextView) view;
       // Toast.makeText(this, "you Selected"+myText.getText(), Toast.LENGTH_SHORT).show();
        universidade = myText.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void onClickUni(View view){


        //BUSCAR OS TEXTFIELDS
        EditText txt_secundario  = (EditText)findViewById(R.id.txt_secundario);
        EditText txt_ingles  = (EditText)findViewById(R.id.txt_ingles);
        EditText txt_nome = (EditText)findViewById(R.id.txt_nome);
        EditText txt_data = (EditText)findViewById(R.id.txt_data);


        //Tornar as labels de erro todas invisiveis
        TextView lbl_erro_idade= (TextView)findViewById(R.id.lbl_erro_idade);
        lbl_erro_idade.setVisibility(View.INVISIBLE);

        TextView lbl_erro_secundario= (TextView)findViewById(R.id.lbl_erro_secundario);
        lbl_erro_secundario.setVisibility(View.INVISIBLE);

        TextView lbl_erro_ingles= (TextView)findViewById(R.id.lbl_erro_ingles);
        lbl_erro_ingles.setVisibility(View.INVISIBLE);

        TextView lbl_erro_nome= (TextView)findViewById(R.id.lbl_erro_nome);
        lbl_erro_nome.setVisibility(View.INVISIBLE);




        //Declarar que por defeito não há erros e que ele passou
        resultado = true;
        erros = false;
        erros_fulfill = false;
        maior = true;

        //Testar se preencheu todos os campos
        if( TextUtils.isEmpty(txt_secundario.getText()))
        {
            erros_fulfill = true;
            lbl_erro_secundario.setText("*Introduza um valor");
            lbl_erro_secundario.setVisibility(View.VISIBLE);
        }
        else
        {
           lbl_erro_secundario.setText("*A nota deve ser entre 0 e 20");
        }

        if( (TextUtils.isEmpty(txt_ingles.getText())))
        {
            erros_fulfill = true;
            lbl_erro_ingles.setText("*Introduza um valor");
            lbl_erro_ingles.setVisibility(View.VISIBLE);
        }
        else
        {
            lbl_erro_ingles.setText("*A nota deve ser entre 0 e 10");
        }

        if((TextUtils.isEmpty(txt_nome.getText())))
        {
            erros_fulfill = true;

            lbl_erro_nome.setVisibility(View.VISIBLE);
        }
        if((TextUtils.isEmpty(txt_data.getText())))
        {
            erros_fulfill = true;
            lbl_erro_idade.setText("*Insira uma data de nascimento.");
            lbl_erro_idade.setVisibility(View.VISIBLE);
        }



        if(erros_fulfill == false) {
            //Obter as notas
            int nota_secundario;
            int nota_ingles;

            nota_secundario = Integer.parseInt(txt_secundario.getText().toString());
            nota_ingles = Integer.parseInt(txt_ingles.getText().toString());

            //Verificar se os dados foram introduzidos corretamente

            if (idade < 18) {
/*                lbl_erro_idade.setVisibility(View.VISIBLE);
                erros = true;*/
                maior = false;
            }

            if (nota_secundario < 0 || nota_secundario > 20) {
                lbl_erro_secundario.setVisibility(View.VISIBLE);
                erros = true;
            }

            if (nota_ingles < 0 || nota_ingles > 10) {
                lbl_erro_ingles.setVisibility(View.VISIBLE);
                erros = true;
            }




            if (erros == false) {

                //Verificar se foi aceite:

                if(nota_ingles<6)
                {
                    resultado = false;
                }
                else if(nota_secundario<14)
                {resultado = false;}
                else{resultado=true;}

                //Abrir a outra atividade

                Intent intecao = new Intent(this, MainAtivity2.class);

                TextView texto = (TextView) findViewById(R.id.textView5);


                intecao.putExtra("TextoPartilhado", texto.getText().toString());
                String resultadoS = String.valueOf(resultado);
                String maiorS = String.valueOf(maior);
                String nota_secundarioS = String.valueOf(nota_secundario);
             //   Toast.makeText(getApplicationContext(),resultadoS, Toast.LENGTH_SHORT).show();

                intecao.putExtra("nota_secundarioS", nota_secundarioS);
                intecao.putExtra("resultadoS", resultadoS);
                intecao.putExtra("maiorS", maiorS);
                intecao.putExtra("nome", txt_nome.getText().toString());
                intecao.putExtra("universidade", universidade);

                startActivity(intecao);
            }


        }
    }

    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }



        EditText txt_data  = (EditText)findViewById(R.id.txt_data);
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Calendar c = Calendar.getInstance();

            //Verificar se foi introduzida uma data válida
            if (year<c.get(Calendar.YEAR))
            {
                txt_data.setText( "" + day + "-" + (month + 1) + "-" + year );
            }
            else if (year == c.get(Calendar.YEAR))
            {
                if(month<c.get(Calendar.MONTH))
                {
                    txt_data.setText( "" + day + "-" + (month + 1) + "-" + year );
                }
                else if(month==c.get(Calendar.MONTH))
                {
                    if(day<=c.get(Calendar.DAY_OF_MONTH)) {
                        txt_data.setText("" + day + "-" + (month + 1) + "-" + year);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Insira uma data de nascimento válida.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Insira uma data de nascimento válida.", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Insira uma data de nascimento válida.", Toast.LENGTH_SHORT).show();
            }

            //Definir a idade

             idade = c.get(Calendar.YEAR) - year;

            if (c.get(Calendar.MONTH) < month){
                idade--;
            }
            else if(c.get(Calendar.MONTH)==month)
            {
                if(c.get(Calendar.DAY_OF_MONTH)<day)
                {
                    idade--;
                }
            }





        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


}
