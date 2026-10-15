package com.fakeyboard;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
public class MainActivity extends Activity {
 @Override protected void onCreate(Bundle b) {
  super.onCreate(b);
  LinearLayout l = new LinearLayout(this);
  l.setOrientation(LinearLayout.VERTICAL);
  l.setPadding(40,40,40,40);
  Button b1 = new Button(this);
  b1.setText("فعال سازی کیبورد");
  b1.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)));
  Button b2 = new Button(this);
  b2.setText("انتخاب کیبورد");
  b2.setOnClickListener(v -> ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showInputMethodPicker());
  l.addView(b1); l.addView(b2);
  setContentView(l);
 }
}
