import java.util.Scanner;

public class test {
    public static void main(String[] args)
    {
        population test = new population(2000);

        while (true)
        {
            if (!test.done())
            {
                test.update();
                System.out.println(test.getAllTimeBest());
            }
            else
            {
                test.geneticAlgorithim();
            }
        }
        
    }
}
