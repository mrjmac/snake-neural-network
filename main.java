public class main {
    public static void main(String[] args)
    {

        population test = new population(2000);

        if (!test.done())
        {
            test.update();
        }
        else
        {
            test.geneticAlgorithim();
        }

    }
}
