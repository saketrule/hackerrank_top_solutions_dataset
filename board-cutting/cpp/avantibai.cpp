#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#define M 1000000007
using namespace std;

///////////////////////////////////////////////
///////////////////////////////////////////////
long partitionFrequencyArray(long  array[],long p, long r)
{
	long x=array[r];
    long i=p-1;
	long temp=0;
	for(int j=p;j<=r-1;j++)
	{
		if(array[j]<=x)
		{
			i=i+1;
			//exchange A[i]with A[j]
			temp=array[i];
			array[i]=array[j];
			array[j]=temp;

		}
	}
	//exchange A[i+1] with A[r]
	temp=array[i+1];
	array[i+1]=array[r];
	array[r]=temp;
	return i+1;
}


void quickSortFrequencies(long  array[],long p, long r)
{
	if(p<r)
	{
		long q=partitionFrequencyArray(array,p,r);
		quickSortFrequencies(array,p,q-1);
		quickSortFrequencies(array,q+1,r);
	}
}

//////////////////////////////////////////
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int t;cin>>t;while(t--)
    {
         long  m,n;cin>>m>>n;
         long arr[m],brr[n];
        for(int i=1;i<m;i++)cin>>arr[i];for(int i=1;i<n;i++)cin>>brr[i];
        long vertical_pieces=1;long horizontal_pieces=1;
        quickSortFrequencies(arr,1,m-1);quickSortFrequencies(brr,1,n-1);
        long h=m-1;long v=n-1;
         long sum=0;
        while(vertical_pieces+horizontal_pieces<m+n)
        {
            if(h>=1&&v>=1)
            {
                if(arr[h]>=brr[v])
                {
                    sum=(sum+arr[h]*vertical_pieces)%M;
                    h--;horizontal_pieces++;
                }
                else
                {
                    sum=(sum+brr[v]*horizontal_pieces)%M;
                    v--;vertical_pieces++;
                }
            }
            else if(h<1)
            {
             while(v>=1){sum=(sum+brr[v]*horizontal_pieces)%M;v--;vertical_pieces++;}   
            }
            else if(v<1)
            {
                while(h>=1){sum=(sum+arr[h]*vertical_pieces)%M;h--;horizontal_pieces++;}                
            }
            
        }
        cout<<sum<<endl;
    }
    return 0;
}
