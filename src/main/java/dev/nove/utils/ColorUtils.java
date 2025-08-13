package dev.nove.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
   public static String color(Object o) {
      if (o == null) {
         return "";
      } else {
         String goodboy;
         if (o instanceof String) {
            goodboy = (String)o;
         } else {
            goodboy = o.toString();
         }

         if (goodboy != null && !goodboy.isEmpty() && !goodboy.isBlank()) {
            char[] b = goodboy.toCharArray();

            for(int i = 0; i < b.length - 1; ++i) {
               if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                  b[i] = 167;
                  b[i + 1] = Character.toLowerCase(b[i + 1]);
               }
            }

            String m = new String(b);
            Matcher matcher = Pattern.compile("&#([A-Fa-f0-9]{6})").matcher(m);
            StringBuilder buffer = new StringBuilder(m.length() + 32);

            while(matcher.find()) {
               String group = matcher.group(1);
               char var10002 = group.charAt(0);
               matcher.appendReplacement(buffer, "§x§" + var10002 + "§" + group.charAt(1) + "§" + group.charAt(2) + "§" + group.charAt(3) + "§" + group.charAt(4) + "§" + group.charAt(5));
            }

            return matcher.appendTail(buffer).toString();
         } else {
            return "";
         }
      }
   }

   public static List<String> colorList(List<String> list) {
      List<String> colorList = new ArrayList();
      list.forEach((string) -> {
         colorList.add(color(string));
      });
      return colorList;
   }
}
