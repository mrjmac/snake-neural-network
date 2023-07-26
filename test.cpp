#include <bits/stdc++.h>

using namespace std;

int main()
{
    //cout << "test" << "\n";
    vector<vector<int>> board(20, vector<int>(20));

    clock_t now = clock();

    bool alive = true;
    int size = 1;

    while (alive)
    {
        if ((clock() - now) / CLOCKS_PER_SEC > .2)
        {
            for (int i = 0; i < (int) board.size(); i++)
            {
                for (int j = 0; j < (int) board[0].size(); j++)
                {
                    cout << board[i][j] << " ";
                }
                cout << "\n";
            }

            cout << "\n";

            now = clock();
        }
    }

}