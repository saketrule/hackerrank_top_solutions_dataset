#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
struct xy
 {
 long long int num;
 int dim;
 };

struct maxheap
{
    struct xy *arr;
    long long int capacity;
    long long int count;
};
long long int powar(long long int a,long long int n)
{
   long long int  i,x;
	x =1;
	for(i=1;i<=n;i++)
	x = x*a;
	
	return x;
}
struct maxheap* createheap(struct maxheap *h)
{
    
    h = malloc(sizeof(struct maxheap));
    h->arr = malloc(sizeof(struct xy));
    h->capacity = 1;
    h->count = 0;
    return h;
}
int leftchild(struct maxheap *h,int i )
{
	if(2*i+1 >= h->count)
	return -1;
	else
	return 2*i+1;
}
int rightchild(struct maxheap *h,int i)
{
	if(2*i+2 >= h->count)
	return -1;
	else
	return 2*i+2;
}
void doubling(struct maxheap *h)
{
 struct xy *p;
    int i;
    p = h->arr;
    h->arr = malloc(2*sizeof(struct xy)*(h->capacity));
    
    for(i=0;i<h->count;i++)
        h->arr[i] = p[i];
    
    h->capacity = h->capacity*2;
}
void insert(struct maxheap *h,long long int data,int dim)
{
    if(h->count == h->capacity)
        doubling(h);
    struct xy variable;
    variable.num = data;
    variable.dim  = dim;
    long long int i;
    h->arr[h->count] = variable;
    i = h->count;
    struct xy temp;
   
   while(i > 0 && h->arr[i].num > h->arr[(i-1)/2].num)
    {
     temp = h->arr[i];
     h->arr[i]  = h->arr[(i-1)/2];
     h->arr[(i-1)/2] = temp;
        i = (i-1)/2;
    }
    h->count++;
}
struct xy deletemax(struct maxheap *h)
{
	long long int i;
	struct xy var,temp;
	var = h->arr[0];
	
	h->arr[0] = h->arr[h->count-1];
	h->count--;
	i = 0;
	if(h->count == 0 )	
	return var;
	while( (leftchild(h,i)!= -1 && h->arr[i].num < h->arr[leftchild(h,i)].num )||(rightchild(h,i) != -1 && h->arr[i].num < h->arr[rightchild(h,i)].num) )
	{
		if(rightchild(h,i) == -1 || h->arr[leftchild(h,i)].num > h->arr[rightchild(h,i)].num)
		{
			
			temp = h->arr[i];
			h->arr[i] =  h->arr[leftchild(h,i)];
			h->arr[leftchild(h,i)] = temp;
			i = leftchild(h,i);
	   } 
		else
		{   
			
			temp = h->arr[i];
			h->arr[i] =  h->arr[rightchild(h,i)];
			h->arr[rightchild(h,i)] = temp;
	   	i = rightchild(h,i);
		}
				
	}
	return var;
}

long long int mincost(struct maxheap *h)
{
	long long int num_of_horizontal_segments,num_of_vertical_segments;
	num_of_horizontal_segments = num_of_vertical_segments = 1;
	long long int sum = 0;
	struct xy temp;
	long long int yo = powar(10,9) + 7;

    while(h->count != 0)
	{
		temp = deletemax(h);
		if(temp.dim == 0 )
		 {
		 num_of_horizontal_segments++;
		 sum = sum + (temp.num)*(num_of_vertical_segments);
		 }
		else
		 {
		 num_of_vertical_segments++;
		 sum = sum + (temp.num)*(num_of_horizontal_segments);
		 }
		 sum = sum%yo;
		 
	}
   return sum;
}

void fun(long long int A[],int j)
{
	struct maxheap *h;
   h = createheap(h);
 
	long long int m,n,x,y,i;
	scanf("%lld %lld",&m,&n);
   
    
    
    
     for(i=0;i<m-1;i++)
        {
         scanf("%lld",&y);
         insert(h,y,0);
         }
      for(i=0;i<n-1;i++)
        {
        scanf("%lld",&x);
        insert(h,x,1);
         }
   
    
   
    A[j] = mincost(h);
         
    
}
int main() {
    
    int t,i;
    
    scanf("%d",&t);
    long long int A[t];
    for(i=0;i<t;i++)
    fun(A,i);
    
    for(i=0;i<t-1;i++)
    printf("%lld \n",A[i]);
    
    printf("%lld",A[i]);
    
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
