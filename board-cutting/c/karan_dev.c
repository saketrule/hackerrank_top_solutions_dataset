#include<stdio.h>
long long int a[1000001],b[1000001],temp[1000001];

void mergeSort(long long int [],long long int,long long int,long long int);

void partition(long long int arr[],long long int low,long long int high){

    long long int mid;

    if(low<high){
	 mid=(low+high)/2;
	 partition(arr,low,mid);
	 partition(arr,mid+1,high);
	 mergeSort(arr,low,mid,high);
    }
}

void mergeSort(long long int arr[],long long int low,long long int mid,long long int high){

    long long int i,m,k,l;

    l=low;
    i=low;
    m=mid+1;

    while((l<=mid)&&(m<=high)){

	 if(arr[l]>=arr[m]){
	     temp[i]=arr[l];
	     l++;
	 }
	 else{
	     temp[i]=arr[m];
	     m++;
	 }
	 i++;
    }

    if(l>mid){
	 for(k=m;k<=high;k++){
	     temp[i]=arr[k];
	     i++;
	 }
    }
    else{
	 for(k=l;k<=mid;k++){
	     temp[i]=arr[k];
	     i++;
	 }
    }

    for(k=low;k<=high;k++){
	 arr[k]=temp[k];
    }
}





int main()
{
	long long int m,n,i,j,t,x,y,mul,c1,c2;
	scanf("%lld",&t);
	while(t--)
	{
		scanf("%lld%lld",&m,&n);
		m--;n--;
		for(i=0;i<m;i++)
			scanf("%lld",&a[i]);
		for(i=0;i<n;i++)
			scanf("%lld",&b[i]);
		partition(a,0,m-1);
		partition(b,0,n-1);
		i=0;j=0;x=1;y=1,mul=0;c1=0;c2=0;
		while(1)
		{


			if(c1==0 && c2==1)
            {
				mul=(mul+(a[i]*y))%1000000007;
                i++;
                if(i==m)
                    break;
            }
			else if(c1==1 && c2==0)
            {
				mul=(mul+(b[j]*x))%1000000007;
                //printf("bi=%d x",b[j],x);

                j++;
                if(j==n)
                    break;
            }
			else
            {
                if(a[i]>=b[j] && c1==0 && c2==0)
                {
                    x++;
                    mul=(mul+(a[i]*y))%1000000007;
                 //   printf("yo=%d ",mul);
                    i++;
                    if(i==m)
                        c1=1;

                }
                if(a[i]<b[j] && c2==0 && c1==0)

                {
                    y++;
                    mul=(mul+(b[j]*x))%1000000007;
                   // printf("%d ",mul);
                    j++;
                    if(j==n)
                        c2=1;


                }
			 }
                  // printf("mul=%d ",mul);


		}


		printf("%lld\n",mul);
	}
	return 0;
}
