package com.yukti.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	ConfigReader() {} //configreader constructor ko lock krdiya jisse koi uska object na bna paaye
   private static ResourceBundle rb = ResourceBundle.getBundle("config");
   public static String getValue(String key) {
	   return rb.getString(key);
   }
}
