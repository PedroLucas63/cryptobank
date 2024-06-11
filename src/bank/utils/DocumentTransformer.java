package bank.utils;

public class DocumentTransformer {
   public static String transform(String document) {
      return document.replace(".", "").replace("-", "").replace("/", "");
   }
}
