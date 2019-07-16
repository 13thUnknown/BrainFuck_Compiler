import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Optimizer {
  public static void run(List<Oper> list) {
    int i=0;
    while ((i+1)<list.size()) {
      if (list.get(i).getType()==list.get(i+1).getType()) {
        list.get(i).times++;
        list.remove(i+1);
      }
      else
        i++;
    }
    log.info("Optimization successful.");
  }
}
