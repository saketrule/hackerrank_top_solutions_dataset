#include<bits/stdc++.h>

using namespace std;
static int M[10001][10001];
static int D[10001];
bool V[10000];
int N;
long long answer;
int min(){
    int min=INT_MAX;
    int index;
	int i;
    for( i=1;i<=N;i++){
        if(!V[i] && D[i]<min){
            min=D[i];
            index=i;
        } 
    }
    return index;  
}

void DJ(int src){
    for(int i=1;i<N;i++){
        int u=min();
        
        V[u]=true;
        for(int j=1;j<=N;j++){
            if(!V[j] && M[u][j]!=INT_MAX &&  D[j]>M[u][j]){
                D[j]=M[u][j];
               }
        }
    }   
}


int min(int x, int y){
  if(x>y)
		return y;
	else 
		return x;
}

int main() {
  
//	freopen("prim.txt","r",stdin); 
        int K;
        cin>>N>>K;
        int i,j,k,l;
        
        for(i=1;i<=N;i++){
            for(j=1;j<=N;j++){
		if(i==j)
		  M[i][j]=0;
            M[i][j]=INT_MAX;
           }
        }
    
        for(i=1;i<=K;i++){
            cin>>j>>k>>l;
            M[j][k]=M[k][j]=min(l,M[j][k]);
        }
        
      
        int S;
        cin>>S;
         for(i=1;i<=N;i++){
             D[i]=INT_MAX;           
        }
        answer=0;
        D[S]=0;
        memset(V,false,sizeof(V));
        DJ(S);
        for(i=1;i<=N;i++)
	         answer+=D[i];    	
        cout<<answer<<endl;
    
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    return 0;
}