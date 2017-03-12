#include<stdio.h>
int main()
{
    
    int n;
    scanf("%d",&n);
    int a[n],i,min=1002;
    for(i=0;i<n;i++)
    {
                    scanf("%d",&a[i]);
                    if(a[i]>0 && a[i]<min)
                    min=a[i];
    }
    int flag=1;
    while(flag)
    {
               flag=0;
               int new_min=min,c=0;;
               min=10002;
               for(i=0;i<n;i++)
               {
                               if(a[i]!=0)
                               {
                               a[i]-=new_min;
                               c++;
                               }
                               if(a[i]>0)
                               flag=1;
                               
                               if(a[i]>0 && a[i]<min)
                               min=a[i];
               }
               printf("%d\n",c);
    }
    return 0;
}
                               
                    
