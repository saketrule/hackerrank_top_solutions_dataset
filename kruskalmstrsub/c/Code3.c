#include <stdio.h>
#include <stdlib.h>
void MM(int x,int y);
int QQ(int x,int y);
void sort_a3(int*a,int*b,int*c,int size);
void merge3(int*a,int*left_a,int*right_a,int*b,int*left_b,int*right_b,int*c,int*left_c,int*right_c,int left_size,int right_size);
int u[4500000],v[4500000],w[4500000],p[3000];

int main(){
  int N,M,S,i;
  long long ans=0;
  scanf("%d%d",&N,&M);
  for(i=0;i<M;i++)
    scanf("%d%d%d",u+i,v+i,w+i);
  for(i=0;i<N;i++)
    p[i]=i;
  sort_a3(w,u,v,M);
  for(i=0;i<M;i++)
    if(!QQ(u[i]-1,v[i]-1)){
      MM(u[i]-1,v[i]-1);
      ans+=w[i];
    }
  printf("%lld",ans);
  return 0;
}
void MM(int x,int y){
  int p1,p2;
  p1=x;
  while(p[p1]!=p1)
    p1=p[p1];
  while(p[x]!=x){
    p2=p[x];
    p[x]=p1;
    x=p2;
  }
  p2=y;
  while(p[p2]!=p2)
    p2=p[p2];
  if(p2!=p1)
    p[p2]=p1;
  while(p[y]!=y){
    p2=p[y];
    p[y]=p1;
    y=p2;
  }
  return;
}
int QQ(int x,int y){
  int p1,p2,ans;
  p1=x;
  while(p[p1]!=p1)
    p1=p[p1];
  ans=p1;
  while(p[x]!=x){
    p2=p[x];
    p[x]=p1;
    x=p2;
  }
  p1=y;
  while(p[p1]!=p1)
    p1=p[p1];
  ans=(ans==p1);
  while(p[y]!=y){
    p2=p[y];
    p[y]=p1;
    y=p2;
  }
  return ans;
}
void sort_a3(int*a,int*b,int*c,int size){
  if (size < 2)
    return;
  int m = (size+1)/2,i;
  int *left_a,*left_b,*left_c,*right_a,*right_b,*right_c;
  left_a=(int*)malloc(m*sizeof(int));
  right_a=(int*)malloc((size-m)*sizeof(int));
  left_b=(int*)malloc(m*sizeof(int));
  right_b=(int*)malloc((size-m)*sizeof(int));
  left_c=(int*)malloc(m*sizeof(int));
  right_c=(int*)malloc((size-m)*sizeof(int));
  for(i=0;i<m;i++){
    left_a[i]=a[i];
    left_b[i]=b[i];
    left_c[i]=c[i];
  }
  for(i=0;i<size-m;i++){
    right_a[i]=a[i+m];
    right_b[i]=b[i+m];
    right_c[i]=c[i+m];
  }
  sort_a3(left_a,left_b,left_c,m);
  sort_a3(right_a,right_b,right_c,size-m);
  merge3(a,left_a,right_a,b,left_b,right_b,c,left_c,right_c,m,size-m);
  free(left_a);
  free(right_a);
  free(left_b);
  free(right_b);
  free(left_c);
  free(right_c);
  return;
}
void merge3(int*a,int*left_a,int*right_a,int*b,int*left_b,int*right_b,int*c,int*left_c,int*right_c,int left_size,int right_size){
  int i = 0, j = 0;
  while (i < left_size|| j < right_size) {
    if (i == left_size) {
      a[i+j] = right_a[j];
      b[i+j] = right_b[j];
      c[i+j] = right_c[j];
      j++;
    } else if (j == right_size) {
      a[i+j] = left_a[i];
      b[i+j] = left_b[i];
      c[i+j] = left_c[i];
      i++;
    } else if (left_a[i] <= right_a[j]) {
      a[i+j] = left_a[i];
      b[i+j] = left_b[i];
      c[i+j] = left_c[i];
      i++;
    } else {
      a[i+j] = right_a[j];
      b[i+j] = right_b[j];
      c[i+j] = right_c[j];
      j++;
    }
  }
  return;
}
