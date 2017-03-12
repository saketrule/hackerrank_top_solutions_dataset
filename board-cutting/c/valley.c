#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

#define LLU unsigned long long int

typedef struct {
    LLU data;
    int side;
} point;

int my_compare(const void *i1, const void *i2)
{
    point *p1 = (point *)i1;
    point *p2 = (point *)i2;
    
    if(p1->data > p2->data) return -1;
    else return 1;
}
int main() {

    int t;
    LLU answer=0, seg_count[2], m, n, i;
    LLU mod = (LLU)pow(10, 9) + 7;
    point *points, cur_point;
    //printf("mod=%llu %d %d\n", mod, sizeof(int), sizeof(LLU));
    scanf("%d",&t);
    while(t--) {
        scanf("%llu %llu", &m, &n);
        answer = 0;
        seg_count[0] =1;
        seg_count[1] =1;
        points = (point *)malloc(sizeof(point)*(m+n-2));
        
        for(i=0;i<m-1;i++) {
            scanf("%llu", &points[i].data);
            points[i].side = 1;
        }
        for(i=0;i<n-1;i++) {
            scanf("%llu", &points[i+m-1].data);
            points[i+m-1].side = 0;
        }
        qsort(points, m+n-2, sizeof(point), my_compare);
        
        for(i=0;i<m+n-2;i++) {
            cur_point = points[i];
            switch(cur_point.side) {
                case 1:
                       answer+= (seg_count[1] % mod) * (cur_point.data % mod);
                       seg_count[0]++;
                       break;
                case 0:
                       answer+= (seg_count[0] % mod) * (cur_point.data % mod);
                       seg_count[1]++;
                       break;
            }
            answer = answer % mod;
        }
        printf("%llu\n", answer);
        free(points);
    }
    return 0;
}

