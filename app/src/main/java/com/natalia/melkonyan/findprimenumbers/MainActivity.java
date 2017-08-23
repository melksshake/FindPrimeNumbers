package com.natalia.melkonyan.findprimenumbers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.natalia.melkonyan.findprimenumbers.EratosphenBolterFuncs.calculatePrimeNumbers;
import static com.natalia.melkonyan.findprimenumbers.EratosphenBolterFuncs.getAllPrimesFromInput;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start_computation)
    Button computationBtn;

    @BindView(R.id.et_prime_numbers)
    EditText userPrimeNumbers;

    @BindView(R.id.computation_results)
    LinearLayout resultsView;

    @BindView(R.id.tv_prime_sum)
    TextView primeSum;

    @BindView(R.id.tv_prime_list)
    TextView primeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        computationBtn.setOnClickListener(v ->
                checkInputString(userPrimeNumbers.getText().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_info_ic) {
            AboutActivity.start(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkInputString(String input) {
        List<Integer> checkedUserInput = new ArrayList<>();

        if (input.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_empty_string), Toast.LENGTH_SHORT).show();
        } else {
            String[] separated = input.split(" ");
            for (int index = 0; index < separated.length; index++) {
                separated[index] = separated[index].replaceAll(",", "");

                if (android.text.TextUtils.isDigitsOnly(separated[index])) {
                    checkedUserInput.add(Integer.parseInt(separated[index]));
                }
            }

            if (checkedUserInput.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_no_numbers), Toast.LENGTH_SHORT).show();
            } else {
                startComputation(checkedUserInput);
            }
        }
    }

    private void startComputation(List<Integer> checkedInput) {
        Collections.sort(checkedInput);
        int N = checkedInput.get(checkedInput.size() - 1);

        List<Integer> calculatedPrimeNumbers = calculatePrimeNumbers(N);

        checkedInput = getAllPrimesFromInput(checkedInput, calculatedPrimeNumbers);

        int sum = 0;
        for (Integer output : checkedInput) {
            sum += output;
        }

        resultsView.setVisibility(View.VISIBLE);
        primeList.setText(checkedInput.toString());
        primeSum.setText(String.valueOf(sum));
    }
}