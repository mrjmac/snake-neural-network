public class population {

    private snake[] pop;

    private int generation;
    private int best;
    private int bestSnakeInd;

    private int allTimeBest;

    private snake bestSnake;

    public population(int size)
    {
        generation = 1;
        best = 3;
        bestSnakeInd = 1;
        allTimeBest = 0;

        bestSnake = null;

        pop = new snake[size];

        for (int i = 0; i < size; i++)
        {
            pop[i] = new snake();
        }
    }

    public void update()
    {
        for (int i = 0; i < pop.length; i++)
        {
            if (pop[i].getAlive())
            {
                pop[i].update(i == bestSnakeInd);
            }
        }

        findBest();
    }

    public boolean done()
    {
        for (snake curr : pop)
        {
            if (curr.getAlive())
            {
                return false;
            }
        }

        return true;
    }

    public void fitness()
    {
        for (snake curr : pop)
        {
            curr.findFitness();
        }
    }

    public void geneticAlgorithim()
    {
        fitness();
        naturalSelection();
    }

    public void naturalSelection()
    {
        snake[] newPop = new snake[pop.length];

        findBestSnake();
        newPop[0] = bestSnake.clone();

        for (int i = 1; i < pop.length; i++)
        {
            snake p1 = findSnake();
            snake p2 = findSnake();

            snake child = p1.crossover(p2);
            child.mutate(0.15);

            newPop[i] = child;
        }

        pop = newPop.clone();
        generation += 1;
        best = 3;
    }

    public snake findSnake()
    {
        double sum = 0;

        for (int i = 0; i < pop.length; i++)
        {
            sum += pop[i].getFitness();
        }

        double target = Math.floor(Math.random() * sum);
        sum = 0;

        for (int i = 0; i < pop.length; i++)
        {
            sum += pop[i].getFitness();
            if (sum > target)
            {
                return pop[i];
            }
        }

        return pop[0];
    }

    public void findBestSnake()
    {
        double max = 0;
        int maxInd = 0;

        for (int i = 0; i < pop.length; i++)
        {
            if (pop[i].getFitness() > max)
            {
                max = pop[i].getFitness();
                maxInd = i;
            }
        }

        bestSnake = pop[maxInd].clone();
    }

    public void findBest()
    {

        int max = 0;
        int maxInd = 0;

        int i = 0;

        for (snake curr: pop)
        {
            if (curr.getAlive() && curr.getSize() > max)
            {
                max = curr.getSize();
                maxInd = i;
            }  
            i++;
        }

        best = max;
        bestSnakeInd = maxInd;

        if (best > allTimeBest)
        {
            allTimeBest = best;
        }
    }

    public int getGen()
    {
        return generation;
    }

    public int getAllTimeBest()
    {
        return allTimeBest;
    }
}
