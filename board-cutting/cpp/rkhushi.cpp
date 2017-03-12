#include<iostream>
#include <algorithm>
using namespace std;
long long mo=1000000007;
long long int arn[100000],arm[100000];
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
              int n,m;
              cin>>n>>m;
              for(int i=0;i<n-1;i++)
              cin>>arn[i];
              for(int i=0;i<m-1;i++)
              cin>>arm[i];
              sort(arn,arn+n-1);
              sort(arm,arm+m-1);
              int i=n-2,j=m-2,r=1,c=1;
              long long cou=0;
              bool f1=0,f2=0;
              while(1)
              {
                      //cout<<"11";
                      if(f1==0 && f2==0)
                      {
                          if(arn[i]>=arm[j])
                          {
                                           cou+=(arn[i]*c);
                                           if(cou>mo)
                                           cou=cou%mo;
                                           r++;
                                           i--;
                          }
                          else if(arn[i]<arm[j])
                          {
                               cou+=(arm[j]*r);
                               if(cou>mo)
                               cou=cou%mo;
                               c++;
                               j--;
                          }
                      }
                      else if(f1==1)
                      {
                               cou+=(arm[j]*r);
                               if(cou>mo)
                               cou=cou%mo;
                               c++;
                               j--;
                      }
                      else if(f2==1)
                      {
                           cou+=(arn[i]*c);
                           if(cou>mo)
                           cou=cou%mo;
                           r++;
                           i--;     
                      }
                      if(i==-1)
                      f1=1;
                      if(j==-1)
                      f2=1;
                      if(f1==1 && f2==1)
                      break;                     
              }
              
            cout<<cou<<endl; 
    }
    return 0;
}
