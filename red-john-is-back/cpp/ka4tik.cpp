/*
 *Kartik Singal @ ka4tik
 */
#include <bits/stdc++.h>
using namespace std;
#define s(x) scanf("%d",&x)
vector<int> primes;
int mark[1111111];
int a[100];
int main()
{
    //freopen("in","r",stdin);
    //freopen("out","w",stdout);
       primes.push_back(2);
       for(int i=3;i<1000000;i+=2)
       {
           if(!mark[i])
           {
               primes.push_back(i);
               for(int j=2*i;j<1000000;j+=i)
               {
                   mark[j]=1;
               }
           }
       }
       //for(int i=0;i<100;i++)
       //{
           //cout<<primes[i]<<endl;
       //}
    
    int N;
    a[1]=1;
    a[2]=1;
    a[3]=1;
    a[4]=2;
    a[5]=3;
    a[6]=4;
    for(int i=7;i<=44;i++)
    {
        a[i]=a[i-1]+a[i-4];
        //cout<<a[i]<<endl;
    }
    int test;
    s(test);
    while(test--)
    {
        int n;
        s(n);
        int ans=0;
        for(int i=2;i<=a[n];i++)
        {
            if(binary_search(primes.begin(),primes.end(),i))
                    ans++;
        }
        cout<<ans<<endl;
    }
                    


    return 0;
}

