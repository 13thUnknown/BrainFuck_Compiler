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
                        list.add(new Oper(Oper.Type.IN));
                        break;
                    }
                    case ',':
                    {
                        list.add(new Oper(Oper.Type.OUT));
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
}