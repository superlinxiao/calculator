package com.aoyang.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void clickFirst(View view) {
    startActivity(new Intent(this, CalculatorActivity.class));
  }

  public void clickThree(View view) {
    Toast.makeText(this, "开发中...", Toast.LENGTH_LONG).show();
  }

  public void clickSecond(View view) {
    Toast.makeText(this, "开发中...", Toast.LENGTH_LONG).show();
  }
}
