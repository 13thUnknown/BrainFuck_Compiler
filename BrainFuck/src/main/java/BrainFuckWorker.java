import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BrainFuckWorker {
    static void start(String fileName) {
      List<Oper> list = Lexer.analysis(fileName);
      Optimizer.run(list);
      Interpreter.interpretation(list);
    }
}