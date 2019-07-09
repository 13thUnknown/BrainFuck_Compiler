import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class BrainFuckWorker
{
    private FileReader fileReader;
    private List<Oper> list;

    public void start(String fileName)
    {
        openRead(fileName);//step 1
        lex();//step 2
        optimization();//step 3
        interpretation();//step 4

    }

    public void openRead(String fileName)
    {
        try
        {
            fileReader = new FileReader(fileName);
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("There is exception: FileNotFoundException");
            System.err.println(e.toString());
            exit(2);
        }
    }


    public void lex()
    {
        int iteration=0;
        int c;
        int OpenClose=0;
        list=new ArrayList<Oper>();
        try
        {
            while ((c=fileReader.read()) != -1)
            {
                switch ((char)c)
                {
                    case '>':
                    {
                        list.add(new Oper(Oper.Type.FORWARD));
                        break;
                    }
                    case '<':
                    {
                        list.add(new Oper(Oper.Type.BACK));
                        break;
                    }
                    case '+':
                    {
                        list.add(new Oper(Oper.Type.ADD));
                        break;
                    }
                    case '-':
                    {
                        list.add(new Oper(Oper.Type.MINUS));
                        break;
                    }
                    case '.':
                    {
                        list.add(new Oper(Oper.Type.OUT));
                        break;
                    }
                    case ',':
                    {
                        list.add(new Oper(Oper.Type.IN));
                        break;
                    }
                    case '[':
                    {
                        list.add(new Oper(Oper.Type.WHILE));
                        OpenClose++;
                        break;
                    }
                    case ']':
                    {
                        list.add(new Oper(Oper.Type.END));
                        OpenClose--;
                        break;
                    }
                    default:
                    {
                        System.out.println("Syntax error. Unknown symbol " + (char)c + " at iteration " + iteration);
                        exit(5);
                    }
                }
                iteration++;
            }
        }
        catch (java.io.IOException e)
        {
            System.out.println("There is exception: IOException");
            System.err.println(e.toString());
            exit(3);
        }
        if (OpenClose!=0)
        {
            System.out.println("Syntax error. '[' and ']' are not equal. ");
            exit(4);
        }
        System.out.println("Lexical analysis is successful.");
        try {
            fileReader.close();
        } catch (IOException e) {
            System.out.println("There is exception: IOException");
            System.err.println(e.toString());
            exit(6);
        }
    }

    public void optimization()
    {
        int i=0;
        while ((i+1)<list.size())
        {
            if (list.get(i).getType()==list.get(i+1).getType())
            {
                list.get(i).times++;
                list.remove(i+1);
            }
            else
                i++;
        }
        System.out.println("Optimization successful.");
    }

    public void interpretation()
    {
        char[] arr = new char[30000];
        int i = 15000;
        int index=0;
        List<Circle> queue = new ArrayList<Circle>();
        while (index!=list.size()-1) {
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
                    if (arr[i]==0)
                    {
                        queue.remove(queue.size()-1);
                        index++;
                        break;
                    }
                    else
                    {
                        index=queue.get(queue.size()-1).index+1;
                        break;
                    }

                }
            }
        }
    }

}