#include <bits/stdc++.h>
#include <curses.h>

using namespace std;

int main()
{
    // set up board and timer
    vector<vector<int>> board(20, vector<int>(20));
    clock_t now = clock();
   
    // setup game state
    bool alive = true, food = false;
    char dir = 'u';
    int hx = 10, hy = 10, lhx = 0, lhy = 0, size = 3;

    // place snake head
    board[10][10] = size;

    // fill in walls
    for (int i = 0; i < 20; i++)
    {
        board[i][0] = -1;
        board[0][i] = -1;
        board[i][19] = -1;
        board[19][i] = -1;
    }
    
    // place first food
    int fx, fy;
    do
    {
        fx = rand() % 18 + 1;
        fy = rand() % 18 + 1;

    } while (board[fx][fy] != 0);

    board[fx][fy] = -2;  

    char temp = 'w';  

    // main game loop
    while (alive)
    {
        if ((clock() - now) / CLOCKS_PER_SEC > .4)
        {
            // print board
            for (int i = 0; i < (int) board.size(); i++)
            {
                for (int j = 0; j < (int) board[0].size(); j++)
                {
                    int curr = board[i][j];

                    if (curr == 0)
                    {
                        cout << " ";
                    }
                    else if (curr == -1)
                    {
                        cout << "X";
                    }
                    else if (curr == -2)
                    {
                        cout << "F";
                    }
                    else if (curr == -3)
                    {
                        cout << "O";

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
                        cout << "O";
                    }
                }
                cout << "\n";
            }

            cout << "\n";

            food = false;

            cin >> temp;
            
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
                    fx = rand() % 18 + 1;
                    fy = rand() % 18 + 1;

                } while (board[fx][fy] != 0);

                board[fx][fy] = -2;
            }
            else if (board[hy][hx] != 0)
            {
                alive = false;
                break;
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

            now = clock();
        }
    }

}