import java.util.Random;

public class matrix
{
    
    private double[][] data;
    private int rows, cols;

    public matrix(int r, int c)
    {
        data = new double[rows][cols];

        rows = r;
        cols = c;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public double get(int r, int c)
    {
        return data[r][c];
    }

    public void set(int r, int c, double val)
    {
        data[r][c] = val;
    }

    public int getCols()
    {
        return cols;
    }

    public int getRows()
    {
        return rows;
    }

    public void add(double scalar)
    {
        for (int i  = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i][j] += scalar;
            }
        }
    }

    public void add(matrix m)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i][j] += m.get(i, j);
            }
        }
    }

    public matrix subtract(matrix other)
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(j, i, data[i][j] - other.get(i, j));
            }
        }

        return temp;
    }

    public matrix transpose()
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(j, i, data[i][j]);
            }
        }

        return temp;
    }

    public void multiply(double scalar)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i][j] *= scalar;
            }
        }
    }

    public matrix multiply(matrix other)
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(j, i, data[i][j] * other.get(i, j));
            }
        }

        return temp;
    }

    public matrix dot(matrix other)
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                double sum = 0;

                for (int k = 0; k < cols; k++)
                {
                    sum += data[i][k] * other.get(k, j);
                }

                temp.set(i, j, sum);
            }
        }

        return temp;
    }

    public matrix oneDtoTwoD(double[] other)
    {
        matrix temp = new matrix(other.length, 1);

        for (int i = 0; i < other.length; i++)
        {
            temp.set(i, 0, other[i]);
        }

        return temp;
    }

    public void fromArray(double[] arr)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                data[i][j] = arr[j + i * cols];
            }
        }
    }

    public double[] toArray()
    {
        double[] temp = new double[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp[j + i * cols] = data[i][j];
            }
        }

        return temp;
    }

    public matrix addBias()
    {
        matrix temp = new matrix(rows + 1, 1);

        for (int i = 0; i < rows; i++)
        {
            temp.set(i, 0, data[i][0]);
        }

        temp.set(rows, 0, 1);

        return temp;
    }

    public matrix activate()
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(i, j, sigmoid(data[i][j]));
            }
        }

        return temp;
    }

    public double sigmoid(double n)
    {
        return 1.0 / (1 + Math.pow(Math.E, -n));
    }

    public matrix dsigmoid()
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(i, j, data[i][j] * (1 - data[i][j]));
            }
        }

        return temp;
    }

    public void mutate(double rate)
    {

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (Math.random() < rate)
                {
                    data[i][j] = Math.random() * 2 - 1;
                }
            }
        }
    }

    public matrix crossover(matrix other)
    {
        matrix child = new matrix(rows, cols);

        int c = (int) Math.random() * cols;
        int r = (int) Math.random() * rows;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if ((i < r) || (i == r && j < c))
                {
                    child.set(i, j, data[i][j]);
                }
                else
                {
                    child.set(i, j, other.get(i, j));
                }
            }
        }

        return child;
    }

    public matrix clone()
    {
        matrix temp = new matrix(rows, cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                temp.set(i, j, data[i][j]);
            }
        }

        return temp;
    }
}