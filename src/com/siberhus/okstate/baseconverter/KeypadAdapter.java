package com.siberhus.okstate.baseconverter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import static com.siberhus.okstate.baseconverter.KeypadButton.*;

public class KeypadAdapter extends BaseAdapter{

  private Context context;
  private GridView gridView;
  
  private OnClickListener onClickListener;
  
  public KeypadAdapter(Context c, GridView gridView){
    this.context = c;
    this.gridView = gridView;
  }
  
  public void setOnClickListener(OnClickListener listener){
    this.onClickListener = listener;
  }
  
  public void setKeypadMode(int base){
    KeypadButton base10[] = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};
    KeypadButton base8[] = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN};
    for(int i=0; i<KeypadButton.THREE.ordinal(); i++){
      gridView.getChildAt(i).setEnabled(false);
    }
    if(base == 16){
      for(int i=0; i<KeypadButton.THREE.ordinal(); i++){
        gridView.getChildAt(i).setEnabled(true);
      }
    }else if(base == 10){
      for(KeypadButton i : base10){
        gridView.getChildAt(i.ordinal()).setEnabled(true);
      }
    }else if(base == 8){
      for(KeypadButton i : base8){
        gridView.getChildAt(i.ordinal()).setEnabled(true);
      }
    }
  }
  
  @Override
  public int getCount() {
    return KeypadButton.values().length;
  }
  
  @Override
  public Object getItem(int position) {
    return gridView.getChildAt(position);
  }
  
  @Override
  public long getItemId(int position) {
    return 123456+position;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Button button;
    if(convertView == null){
      button = new Button(context);
      KeypadButton keypadButton = KeypadButton.values()[position];
      if(keypadButton==HEX){
        button.setTextColor(Color.RED);
      }
      button.setOnClickListener(this.onClickListener);
      button.setTag(keypadButton);
      button.setText(KeypadButton.values()[position].getText());
    }else{
      button = (Button)convertView;
    }
    
    return button;
  }
  
}
