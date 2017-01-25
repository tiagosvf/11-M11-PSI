package net.esen.aluno.okstudent;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

/**
 * Created by Tiago on 03/03/2016.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener{

    Context context;

    public DateSettings(Context context)
    {
this.context = context;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



    }
}
