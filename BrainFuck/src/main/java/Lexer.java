import static java.lang.System.exit;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lexer {
  private static List<Oper> list;
  public static List<Oper> analysis(String fileName) {
    FileReader fileReader = openRead(fileName);
    list=new ArrayList<Oper>();
    int iteration=0;
    int c;
    int openClose=0;
    try {
      while ((c=fileReader.read()) != -1) {
        switch ((char)c) {
          case '>': {
            list.add(new Oper(Oper.Type.FORWARD));
            break;
          }
          case '<': {
            list.add(new Oper(Oper.Type.BACK));
            break;
          }
          case '+': {
            list.add(new Oper(Oper.Type.ADD));
            break;
          }
          case '-': {
            list.add(new Oper(Oper.Type.MINUS));
            break;
          }
          case '.': {
            list.add(new Oper(Oper.Type.OUT));
            break;
          }
          case ',': {
            list.add(new Oper(Oper.Type.IN));
            break;
          }
          case '[': {
            list.add(new Oper(Oper.Type.WHILE));
            openClose++;
            break;
          }
          case ']': {
            list.add(new Oper(Oper.Type.END));
            openClose--;
            break;
          }
          default: {
            log.info("Syntax error. Unknown symbol " + (char)c + " at iteration " + iteration);
            exit(5);
          }
        }
        iteration++;
      }
    }
    catch (java.io.IOException e) {
      log.info("There is exception: IOException");
      log.error(e.toString());
      exit(3);
    }
    if (openClose!=0) {
      log.info("Lexical error: unpaired '[' and ']'.");
      exit(4);
    }
    log.info("Lexical analysis is successful.");
    try {
      fileReader.close();
    } catch (IOException e) {
      log.info("There is exception: IOException");
      log.error(e.toString());
      exit(6);
    }
    return list;
  }
  static FileReader openRead(String fileName) {
    try {
      FileReader fileReader = new FileReader(fileName);
      return fileReader;
    }
    catch (java.io.FileNotFoundException e) {
      log.info("There is exception: FileNotFoundException");
      log.error(e.toString());
      exit(2);
    }
    return null;
  }
}
