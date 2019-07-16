import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String fileName="C:\\BrainFuck.txt";
        log.info("Application started!");
        BrainFuckWorker.start(fileName);
    }
}