
public class Main {

    public static void main(String[] args)
    {
        String fileName="C:\\BrainFuck.txt";
        System.out.println("Application started!");
        BrainFuckWorker worker=new BrainFuckWorker();
        worker.start(fileName);

    }
}
