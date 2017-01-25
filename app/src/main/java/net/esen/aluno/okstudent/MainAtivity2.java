package net.esen.aluno.okstudent;

import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainAtivity2 extends FragmentActivity {

    EditText texto;
    int n1;
    int total;
    String s1;
    int propinas_base;
    int propinas_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_ativity2);

      /*  TextView lbl_resultado  = (TextView)findViewById(R.id.lbl_resultado);

        Bundle extras = getIntent().getExtras();

        if(extras.getString("resultado")=="false")
        {


            lbl_resultado.setTextColor();
        }
        */

        Bundle extras = getIntent().getExtras();

        TextView lbl_pon = (TextView)findViewById(R.id.lbl_pon);

     //   Toast.makeText(getApplicationContext(), "É " + extras.getString("resultadoS"), Toast.LENGTH_SHORT).show();

        String maS = extras.getString("maiorS");
        String reS = extras.getString("resultadoS");
        String falso = "false";
        if(maS.equals(falso) && reS.equals(falso))
        {
            lbl_pon.setText("Infelizmente, " + extras.getString("nome")+ " não se poderá candidatar à " + extras.getString("universidade") + " por ter menos de 18 anos e notas baixas." );



        }
        else if(maS.equals(falso))
        {
            lbl_pon.setText("Infelizmente, " + extras.getString("nome")+ " não se poderá candidatar à " + extras.getString("universidade") + " por ter menos de 18 anos." );

        }
        else if(reS.equals(falso))
        {
            lbl_pon.setText("Infelizmente, " +extras.getString("nome")+ " não se poderá candidatar à " + extras.getString("universidade") + " devido a notas baixas." );

        }
        else
        {
            lbl_pon.setText(extras.getString("nome")+ " poderá candidatar-se à " + extras.getString("universidade") + "! Parabéns!" );

        }


    }



    public void showAlert(View view)
    {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);


        Bundle extras = getIntent().getExtras();


        if(extras.getString("universidade").equals(("Universidade do Porto")))
        {
            propinas_base = 1001;
        }
        else if(extras.getString("universidade").equals(("Universidade de Lisboa")))
        {
           propinas_base = 1063;
        }
        else
        {
            propinas_base = 1063;
        }

        s1 = extras.getString("nota_secundarioS");



        n1 = Integer.parseInt(s1);

        total = n1;


        if (total >= 18)
        {
            propinas_final = propinas_base - ((propinas_base*20)/100);
            myAlert.setMessage("As suas propinas são de "+propinas_final+ " euros")
                    .create();
            myAlert.show();
        }
        else if ( total >=16 && total <= 17){

            propinas_final = propinas_base - ((propinas_base*10)/100);
            myAlert.setMessage("As suas propinas são de "+propinas_final+ " euros").create();
            myAlert.show();
        }
        else{

            myAlert.setMessage("As suas propinas são de " + propinas_base +" euros").create();
            myAlert.show();
        }
    }

    public void showAlert2(View view)
    {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);


            myAlert.setMessage("Este trabalho foi realizado por Tiago Ferreira (nº24) e João Aires (nº15) da turma 11ºH da ESEN Viseu. \n\nCuriosidade: No ano letivo de 2015/16 a Universidade de Lisboa e a de Coimbra tiveram exatamente o mesmo preço de propinas.")
                    .create();
            myAlert.show();


    }
    public void onClickVoltar(View view)
    {
        super.finish();
    }
}
