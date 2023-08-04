import java.util.Scanner;

public class snake {

    public static int[] game() throws InterruptedException
    {
        Scanner input = new Scanner(System.in);

        int[][] board = new int[20][20];

        boolean alive = true, food = false;
        char dir = 'u';

        int hx = 10, hy = 10, lhx = 0, lhy = 0, size = 3, reason = 0;

        board[10][10] = size;

        for (int i = 0; i < 20; i++)
        {
            board[i][0] = -1;
            board[0][i] = -1;
            board[i][19] = -1;
            board[19][i] = -1;
        }
        
        int fx, fy;
        do
        {
            fx = (int) Math.floor(Math.random() * 18 + 1);
            fy = (int) Math.floor(Math.random() * 18 + 1);

        } while (board[fx][fy] != 0);

        board[fx][fy] = -2;  

        char temp = 'w';  

        while (alive)
        {
            for (int i = 0; i < (int) board.length; i++)
            {
                for (int j = 0; j < (int) board[0].length; j++)
                {
                    int curr = board[i][j];

                    if (curr == 0)
                    {
                        System.out.print(" ");
                    }
                    else if (curr == -1)
                    {
                        System.out.print("X");
                    }
                    else if (curr == -2)
                    {
                        System.out.print("F");
                    }
                    else if (curr == -3)
                    {
                        System.out.print("O");

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
                        System.out.print("O");
                    }
                }
                System.out.print("\n");
            }

            System.out.print("\n");

            food = false;
            temp = input.nextLine().charAt(0);
                
            switch(temp)
            {
                case 'w':
                {
                    if (dir != 'd')
                    {
                        dir = 'u';
                    }
                    break;
                }
                case 's':
                {
                    if (dir != 'u')
                    {
                        dir = 'd';
                    }
                    break;
                }
                case 'd':
                {
                    if (dir != 'l')
                    {
                        dir = 'r';
                    }
                    break;
                }
                case 'a':
                {
                    if (dir != 'r')
                    {
                        dir = 'l';
                    }
                    break;
                }
            }
                

            if (dir == 'r')
            {
                hx += 1;
            }
            else if (dir == 'l')
            {
                hx -= 1;
            }
            else if (dir == 'u')
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
                reason = board[hy][hx];
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

            Thread.sleep(400);
        }

        return new int[] {size, reason};
    }
}
