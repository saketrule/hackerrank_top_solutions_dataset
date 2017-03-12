#include <iostream>
#include <algorithm>
using namespace std;

int n,a[10000],i,j,m,k;
main()
{
	
	 cin>>n; 
	  m=n;
	  
	 for (i=1;i<=n;i++)
	  cin>>a[i];
	  
	  sort(a+1,a+n+1);
	   
	   
	   for (i=1;i<=n;i++)
	    {
	      if (a[i]==0) m--; else
		  {
		  	 cout<<m<<endl; k=a[i];
		  	 for (j=i;j<=n;j++)
		  	  a[j]-=k;
		  	  m--;
		  }	
	    }
	  
	
	
}