package com.siberhus.okstate.baseconverter;

public enum KeypadButton {
  C("C", true),
  D("D", true),
  E("E", true),
  F("F", true),
  
  EIGHT("8", true),
  NINE("9", true),
  A("A", true),
  B("B", true),
  
  FOUR("4", true),
  FIVE("5", true),
  SIX("6", true),
  SEVEN("7", true),
  
  ZERO("0", true),
  ONE("1", true),
  TWO("2", true),
  THREE("3", true),
  
  HEX("Hex", false),
  DEC("Dec", false),
  OCT("Oct", false),
  CLR("Clear", false)
  ;
  String text;
  boolean number;
  int group;
  
  KeypadButton(String text, boolean number){
    this.text = text;
    this.number = number;
  }
  public String getText(){
    return text;
  }
  public boolean isNumber(){
    return number;
  }
}
