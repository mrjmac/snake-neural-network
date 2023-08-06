public class snake {

    private int size;
    private int[][] board;
    private int hx, hy, lhx, lhy, fx, fy;

    private boolean alive;
    private boolean food;

    private neuralnet nn;
    private double fitness;

    private double[] input;
    private double[] output;

    public snake()
    {
        board = new int[20][20];

        alive = true;
        food = false;

        hx = 10;
        hy = 10;
        lhx = 0;
        lhy = 0;
        size = 3;

        board[10][10] = size;

        for (int i = 0; i < 20; i++)
        {
            board[i][0] = -1;
            board[0][i] = -1;
            board[i][19] = -1;
            board[19][i] = -1;
        }

        do
        {
            fx = (int) Math.floor(Math.random() * 18 + 1);
            fy = (int) Math.floor(Math.random() * 18 + 1);

        } while (board[fx][fy] != 0);

        board[fx][fy] = -2;  

        nn = new neuralnet(16, 12, 4);
        fitness = 0;

        input = new double[16];
        output = new double[4];
    }

    public void update(boolean best)
    {
        // do brain stuff and set maxind
        double[] temp = searchDir(1, 0);
        input[0] = temp[0];
        input[1] = temp[1];

        temp = searchDir(0, 1);
        input[2] = temp[0];
        input[3] = temp[1];

        temp = searchDir(1, 1);
        input[4] = temp[0];
        input[5] = temp[1];

        temp = searchDir(-1, 0);
        input[6] = temp[0];
        input[7] = temp[1];

        temp = searchDir(0, -1);
        input[8] = temp[0];
        input[9] = temp[1];

        temp = searchDir(-1, -1);
        input[10] = temp[0];
        input[11] = temp[1];

        temp = searchDir(1, -1);
        input[12] = temp[0];
        input[13] = temp[1];

        temp = searchDir(-1, 1);
        input[14] = temp[0];
        input[15] = temp[1];

        output = nn.output(input);

        double max = 0;
        int maxind = 0;

        for (int i = 0; i < 4; i++)
        {
            if (output[i] > max)
            {
                max = output[i];
                maxind = i;
            }
        }

        for (int i = 0; i < (int) board.length; i++)
        {
            for (int j = 0; j < (int) board[0].length; j++)
            {
                int curr = board[i][j];

                if (curr == 0)
                {
                    if (best)
                    {
                        System.out.print(" ");
                    }
                }
                else if (curr == -1)
                {
                    if (best)
                    {
                        System.out.print("X");
                    }                    }
                else if (curr == -2)
                {
                    if (best)
                    {
                        System.out.print("F");
                    }
                }
                else if (curr == -3)
                {
                    if (best)
                    {
                        System.out.print("O");
                    }

                    board[i][j] = 0;
                }
                else
                {
                    if (!food)
                    {
                        board[i][j]--;
                    }

                    if (board[i][j] == 0)
                    {
                        lhx = j;
                        lhy = i;
                    }

                    if (best)
                    {
                        System.out.print("O");
                    }
                }
            }

            if (best)
            {
                System.out.print("\n");
            }   
        }

        if (best)
        {
            System.out.print("\n");
        }

        food = false;
                
        switch(maxind)
        {
            case 1:
            {
                if (maxind != 4)
                {
                    maxind = 1;
                }
                break;
            }
            case 2:
            {
                if (maxind != 3)
                {
                    maxind = 2;
                }
                break;
            }
            case 3:
            {
                if (maxind != 2)
                {
                    maxind = 3;
                }
                break;
            }
            case 4:
            {
                if (maxind != 1)
                {
                    maxind = 4;
                }
                break;
            }
        }
                

        if (maxind == 3)
        {
            hx += 1;
        }
        else if (maxind == 2)
        {
            hx -= 1;
        }
        else if (maxind == 1)
        {
            hy -= 1;
        }
        else
        {
            hy += 1;
        }

                
        if (board[hy][hx] == -2)
        {
            size += 1;
            food = true;
                    
            do
            {
                fx = (int) Math.floor(Math.random() * 18 + 1);
                fy = (int) Math.floor(Math.random() * 18 + 1);

            } while (board[fx][fy] != 0);

            board[fx][fy] = -2;
        }
        else if (board[hy][hx] != 0)
        {
            alive = false;
        }
                

        if (food)
        {
            board[hy][hx] = size - 1;
            board[lhy][lhx] = -3;
        }
        else 
        {
            board[hy][hx] = size;
        }
    }

    public void mutate(double val)
    {
        nn.mutate(val);
    }

    public void findFitness()
    {
        fitness = Math.pow(size, 2);
    }

    public double getFitness()
    {
        return fitness;
    }

    public boolean getAlive()
    {
        return alive;
    }

    public neuralnet getnn()
    {
        return nn;
    }

    public void setnn(neuralnet newnn)
    {
        nn = newnn;
    }

    public int getSize()
    {
        return size;
    }

    public snake crossover(snake other)
    {
        snake child = new snake();

        child.setnn(nn.crossover(other.getnn()));

        return child;
    }

    public double[] searchDir(int x, int y)
    {
        boolean finished = false;

        int currX = hx + x;
        int currY = hy + y;
        int distance = 1;

        double[] results = {0.0, 0.0};

        while (!finished && validPos(currX, currY))
        {
            if (board[currX][currY] == -2)
            {
                results[1] = 1;
            }
            else if (board[currX][currY] != 0)
            {
                // compress to 0-1
                results[0] = 1.0 / distance;
                finished = true;
            }

            distance += 1;
            currX += x;
            currY += y;
        }

        return results;
    }

    public boolean validPos(int x, int y)
    {
        return (x >= 0 && x <= 19 && y >= 0 && y <= 19);
    }

    public void setAlive()
    {
        alive = true;
    }

    public snake clone()
    {
        snake clone = new snake();

        clone.setnn(nn.clone());
        clone.setAlive();

        return clone;
    }

    

    
}
