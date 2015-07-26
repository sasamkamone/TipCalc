package com.self.smone.tipcalc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    int tipPercent = 15;
    int people = 1;

    //TextView tipPercent = (TextView)findViewById(R.id.text_tip_value);

    //TextView numberOfPeople = (TextView)findViewById(R.id.text_people_view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementTip(View view) {
        tipPercent += 1;
        display(tipPercent);
        displaySummary(view);
    }

    public void decrementTip(View view) {
        tipPercent = (tipPercent == 0) ? tipPercent : tipPercent - 1;
        display(tipPercent);
        displaySummary(view);
    }

    public void incrementPeople(View view) {
        people = people + 1;
        displayPeople(people);
        displaySummary(view);
    }

    public void decrementPeople(View view) {
        people = (people == 1) ? people : people - 1;
        displayPeople(people);
        displaySummary(view);
    }

    private void display(int number) {
        TextView tipPercentTextView = (TextView) findViewById(
                R.id.text_tip_value);
        tipPercentTextView.setText("" + number);
    }

    private void displayPeople(int number) {
        TextView peopleTextView = (TextView) findViewById(R.id.text_people_value);
        peopleTextView.setText("" + number);
    }

    private void displaySummary(View view) {
        final TextInputLayout floatingEdittext = (TextInputLayout) findViewById(R.id.edit_bill_text);
        TextView summaryView = (TextView) findViewById(R.id.summary_text);
        String bill = floatingEdittext.getEditText().getText().toString();
        Log.i(TAG, "bill is ***=" + bill + "***");
        if (bill.length() > 0 && bill.matches("^\\d*(\\.\\d{2})?$")) {
            BigDecimal tip = calculateTip(Double.parseDouble(bill), tipPercent, people);
            BigDecimal total = totalPerPerson(Double.parseDouble(bill), tip, people);
            String summary = "Tip Per Person: $" + tip + "\n\n";
            summary += "Total per person: $" + total;
            summaryView.setText(summary);
        } else {
            displayToast(view);
        }


    }


    private BigDecimal calculateTip(double bill, int tipPercent, int numberOfPeople) {
        BigDecimal b = new BigDecimal((bill * tipPercent) / (100 * numberOfPeople));
        return b.setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal totalPerPerson(double bill, BigDecimal tip, int numberOfPeople) {
        BigDecimal b = tip.add(new BigDecimal((bill / numberOfPeople)));
        return b.setScale(2, RoundingMode.CEILING);
    }

    private void displayToast(View view) {
        Snackbar.make(view, "Invalid Bill amount", Snackbar.LENGTH_LONG)
                //.setAction("Undo", mOnClickListener)
                .setActionTextColor(Color.RED)
                .show();

    }

}
