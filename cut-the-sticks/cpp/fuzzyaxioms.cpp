#include <iostream>

int main()
{
    int n;
    std::cin >> n;
    
    int counts[1001];
    for (int i = 0; i < 1001; ++i)
    {
        counts[i] = 0;
    }
    
    for (int i = 0; i < n; ++i)
    {
        int a;
        std::cin >> a;
        ++counts[a];
    }
    
    for (int i = 0; i < 1001; ++i)
    {
        if (counts[i] > 0)
        {
            std::cout << n << std::endl;
            n -= counts[i];
        }
    }
    
}
