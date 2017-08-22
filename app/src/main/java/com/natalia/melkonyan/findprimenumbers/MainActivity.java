package com.natalia.melkonyan.findprimenumbers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start_computation)
    Button computationBtn;

    @BindView(R.id.et_prime_numbers)
    EditText userPrimeNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        computationBtn.setOnClickListener(v -> {
            checkInputString(userPrimeNumbers.getText().toString());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkInputString(String input) {
        List<Integer> checkedUserInput = new ArrayList<>();

        if (input.isEmpty()) {
            Toast.makeText(this, "Empty string", Toast.LENGTH_SHORT).show();
        } else {
            String[] separated = input.split(" ");
            for (int index = 0; index < separated.length; index++) {
                separated[index] = separated[index].replaceAll(",", "");

                if (android.text.TextUtils.isDigitsOnly(separated[index])) {
                    checkedUserInput.add(Integer.parseInt(separated[index]));
                }
            }

            if (checkedUserInput.isEmpty()) {
                Toast.makeText(this, "No numbers entered", Toast.LENGTH_SHORT).show();
            } else {

                Collections.sort(checkedUserInput);

                /*Collections.sort(checkedUserInput, new Comparator<Integer>() {
                    @Override
                    public int compare(String s, String t1) {
                        return s.compareTo(t1);
                    }
                });*/

                startComputation(checkedUserInput);
            }
        }
    }

    private void startComputation(List<Integer> checkedInput) {
//        Toast.makeText(this, "Start computation", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, checkedInput.toString(), Toast.LENGTH_SHORT).show();

        int N = checkedInput.get(checkedInput.size() - 1);

        

        // находим N - наибольшее число в последоваельности
        // методом решета Эратосфена находим все простые числа до N
        // сравниваем найденные с введенными
        // простые выводим и суммируем


    }
}
