#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long int score(long int *array,long int low,long int high)
    {
    long int i,j,suml=0,sumr=0;
    i=low;
    j=high;
    suml = array[i];
    sumr = array[j];
    while(i != j-1)
        {
        if(suml<sumr)
            {
            i++;
            suml += array[i];
        }
        else if(suml>sumr)
            {
            j--;
            sumr += array[j];
        }
        else
            {
            if(i < j-2)
             {
            i++;
            j--;
            suml += array[i];
            sumr += array[j];
            }
            else
                {
                i++;
                suml += array[i];
            }
        }
    
    }
    //printf("%li %li",suml,sumr);
    if(suml == sumr )
        {
        
        return j;
    }
    else
        {
        return 0;
    }
}
long int max(long int a,long int b)
    {
    if(a>b)
        return a;
    else 
        return b; 
}



long int calculate(long int * array,long int low,long int high)
    {
    //printf("INSIDE CALCULATE\n");
    if(low == high)
        return 0;
    else
        {
        long int p,value;
        if((p=score(array,low,high)) != 0)
            {
                
              
            return 1+max(calculate(array,low,p-1),calculate(array,p,high));
            
            return value;
        }
        else
            {
            return 0;
        }
    }
}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    int t;
    long int array[20000],n,count;
    int i,j;
    scanf("%d",&t);
    while(t--)
        {
        count = 0;
        scanf("%li",&n);
        for(i=0 ; i<n ; i++)
            {
                scanf("%li",&array[i]);
                if( array[i] == 0)
                    count++;
        }
        if(count == n)
            printf("%li\n",n-1);
        else
            printf("%li\n",calculate(array,0,n-1));
    }
    return 0;
}
