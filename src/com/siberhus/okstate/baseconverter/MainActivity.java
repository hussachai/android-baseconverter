package com.siberhus.okstate.baseconverter;

import java.math.BigInteger;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
  
  private static final String TAG = "MainActivity";
  
  private GridView keypadGrid;
  private KeypadAdapter keypadAdapter;
  private int lastMode = 16;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    keypadGrid = (GridView)findViewById(R.id.keypadGrid);
    keypadAdapter = new KeypadAdapter(this, keypadGrid);
    keypadGrid.setAdapter(keypadAdapter);
    
    keypadAdapter.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        TextView displayTxt = (TextView)findViewById(R.id.displayTxt);
        String number = displayTxt.getText().toString();
        Button btn = (Button) v;
        Log.d(TAG, "Button "+btn.getTag()+" clicked");
        KeypadButton keypadBtn = (KeypadButton)btn.getTag();
        Button hexBtn = (Button)keypadGrid.getChildAt(KeypadButton.HEX.ordinal());
        Button decBtn = (Button)keypadGrid.getChildAt(KeypadButton.DEC.ordinal());
        Button octBtn = (Button)keypadGrid.getChildAt(KeypadButton.OCT.ordinal());
        if(keypadBtn.isNumber()){
          if(number.equals("0")){
            displayTxt.setText(keypadBtn.getText());
          }else{
            displayTxt.append(keypadBtn.getText());
          }
        }else{
          if(keypadBtn == KeypadButton.HEX){
            btn.setTextColor(Color.RED);
            decBtn.setTextColor(Color.BLACK);
            octBtn.setTextColor(Color.BLACK);
            keypadAdapter.setKeypadMode(16);
            displayTxt.setText(convertNumber(number, 16));
          }else if(keypadBtn == KeypadButton.OCT){
            btn.setTextColor(Color.RED);
            hexBtn.setTextColor(Color.BLACK);
            decBtn.setTextColor(Color.BLACK);
            keypadAdapter.setKeypadMode(8);
            displayTxt.setText(convertNumber(number, 8));
          }else if(keypadBtn == KeypadButton.DEC){
            btn.setTextColor(Color.RED);
            hexBtn.setTextColor(Color.BLACK);
            octBtn.setTextColor(Color.BLACK);
            keypadAdapter.setKeypadMode(10);
            displayTxt.setText(convertNumber(number, 10));
          }else{
            displayTxt.setText("0");
          }
        }
      }
      
      private String convertNumber(String number, int base){
        if(number!=null && !number.equals("")){
          BigInteger value = new BigInteger(number, lastMode);
          number = value.toString(base);
          number = number.toUpperCase(Locale.US);
        }
        lastMode = base;
        return number;
      }
    });
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  
}
