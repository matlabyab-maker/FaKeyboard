package com.fakeyboard;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;
public class FaKeyboardService extends InputMethodService {
 boolean isFa = true;
 LinearLayout root;
 String[][] faRows = {{"ض","ص","ث","ق","ف","غ","ع","ه","خ","ح","ج"},{"ش","س","ی","ب","ل","ا","ت","ن","م","ک","گ"},{"ظ","ط","ژ","ز","ر","ذ","د","پ","و","چ","؟"}};
 String[][] enRows = {{"q","w","e","r","t","y","u","i","o","p"},{"a","s","d","f","g","h","j","k","l"},{"z","x","c","v","b","n","m","?","!"}};
 String[] faNums = {"۱","۲","۳","۴","۵","۶","۷","۸","۹","۰"};
 String[] enNums = {"1","2","3","4","5","6","7","8","9","0"};
 @Override public View onCreateInputView() {
  root = new LinearLayout(this);
  root.setOrientation(LinearLayout.VERTICAL);
  root.setBackgroundColor(Color.parseColor("#EEF0F4"));
  root.setPadding(8,8,8,8);
  buildKeyboard();
  return root;
 }
 void buildKeyboard() {
  root.removeAllViews();
  root.addView(makeRow(new String[]{"Paste","Copy","Cut","Undo"}));
  root.addView(makeRow(new String[]{"دکمه ","و ","در ","را ","که "}));
  LinearLayout numRow = makeRow(isFa ? faNums : enNums);
  Button del = makeKey("⌫", Color.parseColor("#FFD6DD"));
  del.setOnClickListener(v -> getCurrentInputConnection().deleteSurroundingText(1,0));
  numRow.addView(del);
  root.addView(numRow);
  String[][] rows = isFa ? faRows : enRows;
  for (String[] r : rows) root.addView(makeRow(r));
  LinearLayout bottom = new LinearLayout(this);
  Button lang = makeKey(isFa ? "FA" : "EN", Color.WHITE);
  lang.setOnClickListener(v -> { isFa = !isFa; buildKeyboard(); });
  Button space = makeKey("Space", Color.WHITE);
  LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0,130,4);
  space.setLayoutParams(p);
  space.setOnClickListener(v -> getCurrentInputConnection().commitText(" ",1));
  Button enter = makeKey("Enter", Color.parseColor("#CFE2FF"));
  enter.setOnClickListener(v -> getCurrentInputConnection().commitText("\n",1));
  bottom.addView(lang); bottom.addView(space); bottom.addView(enter);
  root.addView(bottom);
 }
 LinearLayout makeRow(String[] keys) {
  LinearLayout row = new LinearLayout(this);
  row.setOrientation(LinearLayout.HORIZONTAL);
  for (String k : keys) {
   Button b = makeKey(k, Color.WHITE);
   b.setOnClickListener(v -> getCurrentInputConnection().commitText(k,1));
   row.addView(b);
  }
  return row;
 }
 Button makeKey(String t, int bg) {
  Button b = new Button(this);
  b.setText(t); b.setTextSize(18); b.setBackgroundColor(bg);
  LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0,130,1);
  p.setMargins(4,4,4,4); b.setLayoutParams(p);
  return b;
 }
}
