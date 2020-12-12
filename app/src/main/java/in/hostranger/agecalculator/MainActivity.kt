package `in`.hostranger.agecalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDateBtn.setOnClickListener { view ->
            ageToDays(view)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun ageToDays(view: View) {

        val myCalender = Calendar.getInstance()

        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, month, dateOfMonth ->


            val userSelectedDate = "$dateOfMonth/${month+1}/$year"
            selectedDate.text = userSelectedDate

            val sdf = SimpleDateFormat("dd/mm/yy", Locale.ENGLISH)
            val userDob = sdf.parse(userSelectedDate)
            val usrSelectedDateToMinuite = userDob!!.time/60000

            val curentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val curentDateToMinutes = curentDate!!.time/60000

            val diferenceTime = curentDateToMinutes - usrSelectedDateToMinuite

            val days =  (diferenceTime / (60*24));

            showResult.text = days.toString()

        }
            ,year
            ,month
            ,day).show()



    }
}