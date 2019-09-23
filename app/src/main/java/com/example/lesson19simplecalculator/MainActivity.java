package com.example.lesson19simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1;
    final int MENU_EXIT_ID = 2;

    EditText etNum1, etNum2;

    Button btnAdd, btnMult, btnSub, btnDiv;

    TextView tvResult;

    String oper = "", strResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим элементы

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnDiv = findViewById(R.id.btnDiv);
        btnMult = findViewById(R.id.btnMult);

        tvResult = findViewById(R.id.tvResult);

        btnDiv.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1, num2, result = 0;

        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(etNum1.getText().toString()) || TextUtils.isEmpty(etNum2.getText().toString()))
            return;

        // читаем EditText и заполняем переменные числами
        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        // определяем нажатую кнопку и выполняем соответствующую операцию
        // в oper пишем операцию, потом будем использовать в выводе
        switch (v.getId()) {
            case R.id.btnAdd:
                result = num1 + num2;
                oper = "+";
                break;
            case R.id.btnSub:
                result = num1 - num2;
                oper = "-";
                break;
            case R.id.btnMult:
                result = num1 * num2;
                oper = "*";
                break;
            case R.id.btnDiv:
                // case divided by zero
                if (num2 == 0) {
                    Toast.makeText(this, "Cant divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                oper = "/";
                break;
        }
        // формируем строку вывода
        strResult = num1 + " " + oper + " " + num2 + " = " + result;
        tvResult.setText(strResult);
    }

    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_EXIT_ID, 0, "Exit");
        return super.onCreateOptionsMenu(menu);
    }

    // обработка нажатий на пункты меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RESET_ID:
                // очищаем поля
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_EXIT_ID:
                // выход из приложения
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
