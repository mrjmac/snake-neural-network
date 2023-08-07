import java.util.Scanner;

public class test {
    public static void main(String[] args)
    {
        population test = new population(5000);
        //snake debug = new snake();

        while (true)
        {
            if (!test.done())
            {
                //debug.update(true);
                test.update();
                System.out.println("allTimeBest :: " + test.getAllTimeBest());
                //System.out.println("currBest :: " + test.getBest());
                System.out.println("generation :: " + test.getGen());
            }
            else
            {
                test.geneticAlgorithim();
            }
        }
        
    }
}
