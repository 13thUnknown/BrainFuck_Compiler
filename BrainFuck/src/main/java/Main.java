import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String fileName="C:\\BrainFuck.txt";
        BasicConfigurator.configure();
        log.info("Application started!");
        BrainFuckWorker.start(fileName);
    }
}