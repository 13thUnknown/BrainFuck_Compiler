import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
  public static void interpretation(List<Oper> list) {
    char[] arr = new char[30000];
    int i = 15000;
    int index=0;
    List<Circle> queue = new ArrayList<Circle>();
    while (index<list.size()) {
      switch (list.get(index).getType()) {
        case FORWARD: {
          i += list.get(index++).times;
          break;
        }
        case BACK: {
          i -= list.get(index++).times;
          break;
        }
        case ADD: {
          arr[i] += list.get(index++).times;
          break;
        }
        case MINUS: {
          arr[i] -= list.get(index++).times;
          break;
        }
        case IN: {
          while (list.get(index).times-- > 0) {
            try {
              arr[i] = (char) System.in.read();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          index++;
          break;
        }
        case OUT: {
          while (list.get(index).times-- > 0) {
            System.out.print(arr[i]);
          }
          index++;
          break;
        }
        case WHILE: {
          queue.add(new Circle(index, list.get(index).times));
          index++;
          break;
        }
        case END: {
          if (arr[i]==0) {
            queue.remove(queue.size()-1);
            index++;
            break;
          }
          else {
            index=queue.get(queue.size()-1).index+1;
            break;
          }
        }
      }
    }
  }
}
