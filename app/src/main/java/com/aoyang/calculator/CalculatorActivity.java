package com.aoyang.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

  private Spinner mSpinnerQ;
  private Spinner mSpinnerP;
  private EditText nEdit;
  private EditText oEdit;
  private TextView mResult;
  private double n;
  private double o;
  private Integer p;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);

    mSpinnerQ = findViewById(R.id.spinner_q);
    mSpinnerP = findViewById(R.id.spinner_p);
    nEdit = findViewById(R.id.value_n);
    oEdit = findViewById(R.id.value_o);
    mResult = findViewById(R.id.result);

    initSpinnerQ();
    initSpinnerP();
    findViewById(R.id.calculator).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator();
      }
    });
  }

  private void initSpinnerQ() {
    Integer[] arr = {0, 1};
    //创建ArrayAdapter对象
    ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
    // 为适配器设置下拉列表下拉时的菜单样式，有好几种样式，请根据喜好选择
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // 将适配器添加到下拉列表上
    mSpinnerQ.setAdapter(adapter);
  }

  private void initSpinnerP() {
    Integer[] arr = {0, 1};
    //创建ArrayAdapter对象
    ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
    // 为适配器设置下拉列表下拉时的菜单样式，有好几种样式，请根据喜好选择
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // 将适配器添加到下拉列表上
    mSpinnerP.setAdapter(adapter);
  }


  private void calculator() {
    try {
      n = Double.valueOf(nEdit.getText().toString());
    } catch (Exception e) {
      //do noting
    }
    if (n < 0) {
      mResult.setText("N值输入错误,不能输入负数");
      return;
    }
    try {
      o = Double.valueOf(oEdit.getText().toString());
    } catch (Exception e) {
      //do noting
    }
    //校验值
    if (o <= 0) {
      mResult.setText("O值输入错误，不能输入负数和0");
      return;
    }
    Integer q = (Integer) mSpinnerQ.getSelectedItem();
    p = (Integer) mSpinnerP.getSelectedItem();
    if (q == 0) {
      qIsZero();
    } else {
      qNotZero();
    }
  }

  /**
   * Q:矩形还是圆形。矩形为1，圆形为0
   * P:曲线高度(单位:mm)
   * N:拉出值(单位:mm)
   * O:净空高度(单位:mm)
   * P=0			O5>4500,O5<4550			04-16-1
   * P=0			O5>4550,O5<4600			04-016-2
   * P=0			O5>4600,O5<4650			04-18-1
   * P=0			O5>4650,O5<4700			04-018-2
   * P=0			O5>4700,O5<4750			04-18-3
   * P=0			O5>4750,O5<4800			04-018-4
   * P=0			O5>4800					04-014A
   * P!=0		O5>4500,O5<4550			04-16-3
   * P!=0		O5>4550,O5<4600			04-016-4
   * P!=0		O5>4600,O5<4650			04-18-5
   * P!=0		O5>4650,O5<4700			04-018-6
   * P!=0		O5>4700,O5<4750			04-18-7
   * P!=0		O5>4750,O5<4800			04-018-8
   * P!=0		O5>4800					04-015A
   */
  private void qNotZero() {
    String result = "大哥，出错了";
    if (p == 0) {
      if (o > 4500 && o < 4550) {
        result = "04-16-1";
      } else if (o > 4550 && o < 4600) {
        result = "04-016-2";
      } else if (o > 4600 && o < 4650) {
        result = "04-18-1";
      } else if (o > 4650 && o < 4700) {
        result = "04-018-2";
      } else if (o > 4700 && o < 4750) {
        result = "04-18-3";
      } else if (o > 4750 && o < 4800) {
        result = "04-018-4";
      } else if (o > 4800) {
        result = "04-014A";
      }
    } else if (p == 1) {
      if (o > 4500 && o < 4550) {
        result = "04-16-3";
      } else if (o > 4550 && o < 4600) {
        result = "04-016-4";
      } else if (o > 4600 && o < 4650) {
        result = "04-18-5";
      } else if (o > 4650 && o < 4700) {
        result = "04-018-6";
      } else if (o > 4700 && o < 4750) {
        result = "04-18-7";
      } else if (o > 4750 && o < 4800) {
        result = "04-018-8";
      } else if (o > 4800) {
        result = "04-015A";
      }
    }
    mResult.setText(result);
  }

  /**
   * Q:矩形还是圆形。矩形为1，圆形为0
   * P:曲线高度(单位:mm)
   * N:拉出值(单位:mm)
   * O:净空高度(单位:mm)
   * <p>
   * 1. q为0
   * 1.1  不输入N
   * P=0          	O5>4600,O5<4650		04-008-1
   * P=0 		 	O5>4650,O5<4700		04-008-2
   * P=0			O5>4700,O5<4750		04-008-3
   * P=0			O5>4750,O5<4800		04-008-4
   * P=0			O5>4800				04-014A
   * P!=0			O5>4600,O5<4650		04-008-5
   * P!=0			O5>4650,O5<4700		04-008-6
   * P!=0			O5>4700,O5<4750		04-008-7
   * P!=0			O5>4750,O5<4800		04-008-8
   * P!=0			O5>4800				04-015A
   * 1.2  输入N<200
   * P=0   N<200  	O5>4450,O5<4500		04-006-3
   * P=0   N<200  	O5>4500,O5<4550		04-006-5
   * P=0   N<200  	O5>4550,O5<4600		04-006-7
   * P!=0  N<200  	O5>4450,O5<4500		04-006-11
   * P!=0  N<200  	O5>4500,O5<4550		04-006-13
   * P!=0  N<200  	O5>4550,O5<4600		04-006-15
   * 1.3  输入N>200
   * P=0   N>200  	O5>4450,O5<4500		04-006-4
   * P=0   N>200  	O5>4500,O5<4550		04-006-6
   * P=0   N>200  	O5>4550,O5<4600		04-006-8
   * P!=0  N>200  	O5>4450,O5<4500		04-006-12
   * P!=0  N>200  	O5>4500,O5<4550		04-006-14
   * P!=0  N>200  	O5>4550,O5<4600		04-006-16
   */
  private void qIsZero() {
    String result = "大哥，出错了";
    if (n == 0) {
      //不输入N

      if (p == 0) {
        if (o > 4600 && o < 4650) {
          result = "04-008-1";
        } else if (o > 4650 && o < 4700) {
          result = "04-008-2";
        } else if (o > 4700 && o < 4750) {
          result = "04-008-3";
        } else if (o > 4750 && o < 4800) {
          result = "04-008-4";
        } else if (o > 4800) {
          result = "04-014A";
        }
      } else if (p == 1) {
        if (o > 4600 && o < 4650) {
          result = "04-008-5";
        } else if (o > 4650 && o < 4700) {
          result = "04-008-6";
        } else if (o > 4700 && o < 4750) {
          result = "04-008-7";
        } else if (o > 4750 && o < 4800) {
          result = "04-008-8";
        } else if (o > 4800) {
          result = "04-015A";
        }
      }
    } else if (n < 200) {
      //输入的n<200
      if (p == 0) {
        if (o > 4450 && o < 4500) {
          result = "04-006-3";
        } else if (o > 4500 && o < 4550) {
          result = "04-006-5";
        } else if (o > 4550 && o < 4600) {
          result = "04-006-7";
        }
      } else if (p == 1) {
        if (o > 4450 && o < 4500) {
          result = "04-006-11";
        } else if (o > 4500 && o < 4550) {
          result = "04-006-13";
        } else if (o > 4550 && o < 4600) {
          result = "04-006-15";
        }
      }
    } else if (n > 200) {
      if (p == 0) {
        if (o > 4450 && o < 4500) {
          result = "04-006-4";
        } else if (o > 4500 && o < 4550) {
          result = "04-006-6";
        } else if (o > 4550 && o < 4600) {
          result = "04-006-8";
        }
      } else if (p == 1) {
        if (o > 4450 && o < 4500) {
          result = "04-006-12";
        } else if (o > 4500 && o < 4550) {
          result = "04-006-14";
        } else if (o > 4550 && o < 4600) {
          result = "04-006-16";
        }
      }
    }
    mResult.setText(result);
  }

}
