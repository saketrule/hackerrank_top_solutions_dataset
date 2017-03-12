#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


bool mycompare(int i,int j)
{return (i>j);}

void cost_check(long& total_cost)
{
if(total_cost>=1000000007)
{total_cost = total_cost - 1000000007;}

}



int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int testcase_num, x_num, y_num, buff;
    long y_index = 0;
    long x_index = 0;
    long y_cut = 0;
    long x_cut = 0;
    long total_cost = 0;
    int modulo = 1000000007;
    
 
    scanf("%d",&testcase_num);
	vector<long> x_cost;
    vector<long> y_cost;
	
	
	for(int num_case = 1;num_case<=testcase_num;++num_case)
	{	x_cost.clear();
		y_cost.clear();
		y_index = 0;
		x_index = 0;
		y_cut = 0;
		x_cut = 0;
		total_cost = 0;
		
		scanf("%d",&x_num);
		scanf("%d",&y_num);
		--x_num;
		--y_num;
		

		for(int i=0;i<x_num;++i)
		{scanf("%d",&buff);
		 x_cost.push_back(buff);    
		}
		
		for(int i=0;i<y_num;++i)
		{scanf("%d",&buff);
		 y_cost.push_back(buff);    
		}
		
		sort(x_cost.begin(),x_cost.end(),mycompare);
		sort(y_cost.begin(),y_cost.end(),mycompare);
		
		
		while(y_index!=y_cost.size() || x_index!=x_cost.size())
		{
			 if(y_index==y_cost.size())
			{  //cout<<"in 1"<<endl;
			   
			   /*for(int j=0;j<y_cut+1;j++)
			   {total_cost = total_cost +  x_cost[x_index];
			    cost_check(total_cost);						}*/
				total_cost = total_cost + x_cost[x_index]*(y_cut+1);
			    cost_check(total_cost);
			   ++x_cut;
			   ++x_index;
			  
				
			}
			else if(x_index==x_cost.size())
			{  //cout<<"in 2"<<endl;
			
				/*for(int j=0;j<x_cut+1;j++)
			   {total_cost = total_cost +  y_cost[y_index];
			    cost_check(total_cost);						}*/
			total_cost = total_cost + y_cost[y_index]*(x_cut+1);
			    cost_check(total_cost);
				
			
			   ++y_cut;
			   ++y_index;
			
			
			}
			else
			{
				if(x_cost[x_index]>y_cost[y_index])
				{    
			   /*for(int j=0;j<y_cut+1;j++)
			   {total_cost = total_cost +  x_cost[x_index];
			    cost_check(total_cost);						}*/
				
				total_cost = total_cost + x_cost[x_index]*(y_cut+1);
			    cost_check(total_cost);
					++x_cut;
					++x_index;
						
				}
				else
				{   
				
			/*	for(int j=0;j<x_cut+1;j++)
			   {total_cost = total_cost +  y_cost[y_index];
			    cost_check(total_cost);						}*/
				total_cost = total_cost + y_cost[y_index]*(x_cut+1);
			    cost_check(total_cost);
				
					
					++y_cut;
					++y_index;
						
				}
			
			}
			
		}
		
		cout<<total_cost%1000000007<<endl;     
    }
    return 0;
}
