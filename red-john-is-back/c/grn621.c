#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
int p[217287];
int count[217287];
int cal(int ,int);
int main() {
    int i,j,t,n,l,s,m,g,sum,temp;
    scanf("%d",&t);
    for(i=2;i<=467;i++)
    {
        if(p[i]==0)
        {
          j=i;
        while(j<=217286)
        {
            j=j+i;
            p[j]++;
        }
        }
    }
    count[1]=0;
    count[2]=1;
    temp=1;
    for(i=3;i<=217286;i++)
    {
        
        if(p[i]==0)
        {
            temp++;
        }
        count[i]=temp;
    }
/*
for(i=1;i<=100000;i++)
    {
printf("I= %d num=%d\n",i,count[i]);

     }*/
    while(t)
    {
        scanf("%d",&n);
        l=n/4;
        s=n%4;
        g=l+s;
        sum=0;
        while(l)
        {
            sum=sum+cal(l+s,s);
           // printf("VAlue of %d C %d =  %d\n",(l+s),s,cal(l+s,s));
            l--;
            s=s+4;
        }
        sum=sum+1;
       // printf("Ways %d\n",sum);
        printf("%d\n",count[sum]);
        t--;
    }
        return 0;
}
    int cal(int n,int r)
    {
        int val=1;
        int k;
        if((n-r)<r)
         r=n-r;
         k=r;
        while(r)
        {
            val=val*n;
            r--;
            n--;
        }
        while(k)
        {
            val=val/k;
            k--;
        }
       return val;
    }

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    

