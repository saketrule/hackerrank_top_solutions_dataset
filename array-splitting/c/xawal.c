#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
void game(long long int a[],int ,int b[],int ,int* ,int low,int high,int );
int partition(long long int a[],int ,int i,int j); 
void foo(int b[],int ,int* ,int );
int main() {
    
    int T,t=1,n,i,score,j,b[1000000],max;
    long long int a[1000000];
   
   
   scanf("%d",&T);
    while(t<=T)
        {
        
        scanf("%d",&n);
        for(i=0;i<n;i++)
            {
            scanf("%lld",&a[i]);
        }
        for(i=0;i<n;i++)
            {
            b[i]=0;
        }
        j=0;
        game(a,n,b,n,&j,0,n-1,0);
        t++;
        max=b[0];
        for(i=1;i<n;i++)
            {
            if(b[i]>max)
                {
                max=b[i];
            }
        }
        printf("%d\n",max);
        
        
    }
    
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
void game(long long int a[],int n,int b[],int m,int *pos,int low,int high,int retval)
    {
    int p;
    p=partition(a,n,low,high);
    if(p!=-1)
        {
       
            game(a,n,b,m,pos,low,p,retval+1);
            game(a,n,b,m,pos,p+1,high,retval+1);
        }
    
    else
        {
        foo(b,m,pos,retval);
    }
        
}
int partition(long long int a[],int n,int i,int j)
    {
    long long int sum_lt=a[i],sum_rt=a[j];
    int p=-1;
    while(i<j)
        {
        if(sum_lt<sum_rt)
            {
            i++;
            sum_lt+=a[i];
        }
        else
            {
            if(sum_lt==sum_rt && i==j-1)
                {
                p=i;
                i++;
            }
            else
                {
                j--;
                sum_rt+=a[j];
            }
        }
    }
    return p;
}
void foo(int b[],int m,int *pos,int retval)
    {
    int i;
    i=*pos;
    b[i]=retval;
    *pos+=1;
    
}

