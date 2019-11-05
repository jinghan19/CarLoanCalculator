package com.example.carloancalculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import java.util.Currency.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        //two ways to put function first is put at here so that this code will run when whole things is pressed to run, and go down here to modify the function)
        buttonCalculate.setOnClickListener{
            calculateLoan()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    private fun calculateLoan(){

        if(editTextCarPrice.text.isEmpty()){ //if user no key in car price dun calculate

            editTextCarPrice.setError(getString(R.string.error_input))
            return //stop
        }

        val carPrice:Int = editTextCarPrice.text.toString().toInt()
        val downPayment:Int = editTextDownPayment.text.toString().toInt()
        val interestRate: Float = editTextInterestRate.text.toString().toFloat() /100
        val loanPeriod: Int = editTextLoanPeriod.text.toString().toInt()
        val loan:Int = carPrice - downPayment
        val interest:Float = loan * interestRate * loanPeriod
        val monthlyRe: Float = (loan + interest) / loanPeriod / 12
        val symbol= getInstance(Locale.getDefault()).getSymbol()
        //textViewCarLoan.setText(loan.toString())
        textViewCarLoan.setText(getString(R.string.loan) + "${symbol}" + "${loan}") //come out is Loan: Rm999, because original Loan: will replaced if key in this line
        textViewInterest.setText(getString(R.string.interest )+ "${symbol}" + "${String.format(" %.2f",interest)}") //%{} is to let sring.format function de code
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment) + "${symbol}" + "${String.format(" %.2f",monthlyRe)}")

        Toast.makeText(this, "Calculate Loan",Toast.LENGTH_SHORT).show()
    }

    //two ways to declare function second is put like fun reser(View) and go to UI thr to (common attributes,onClick and select this reset function)
    fun reset(view: View){
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
        textViewCarLoan.setText(getString(R.string.loan))
        textViewInterest.setText(getString(R.string.interest ))
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment))
        Toast.makeText(this, "Reset",Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
