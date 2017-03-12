#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

struct edge {
  int x;
  int y;
  int wgts;
};

struct uSet
{
  struct uSet *parent;
};

struct uSet *Set = NULL;

void Init_uSet(int N)
{
  int i;
  Set = (struct uSet *) malloc((N+1)*sizeof(struct uSet));
  for(i=0; i <=N; i++)
  {
    struct uSet *t = &Set[i];
    t->parent = t;
  }
};

struct uSet *findParent(int x)
{
  struct uSet *t = &Set[x];

  while (t->parent != t)
  {
    t = t->parent;
  }

  return t;
}

int mergeSet(int x, int y)
{
  struct uSet *child1 = &Set[x];
  struct uSet *child2 = &Set[y];
  struct uSet *parent1;
  struct uSet *parent2;
  parent1 = findParent(x);
  parent2 = findParent(y);

  if(parent1 == parent2)
    return 0; // no merger cycle
  parent1->parent = parent2;
  return 1;
}


int mycmp(const void *a, const void *b)
{
  struct edge *aa = (struct edge *) a;
  struct edge *bb = (struct edge *) b;

  if (aa->wgts != bb->wgts)
    return (aa->wgts - bb->wgts);
  else
    return (aa->x+aa->y) - (bb->x+bb->y);
}

int Kruskal(int N, int M, int S, struct edge *link)
{
  int Ans=0;
  int cnt = 0;
  int i = 0;
  struct edge *tmpLink;

  Init_uSet(N);

  while( cnt < N -1)
  {
    tmpLink = &link[i];
    if (mergeSet(tmpLink->x, tmpLink->y) == 1)
    {
      cnt++;
      Ans += tmpLink->wgts;
    }
    i++;
  }

  return Ans;
}

int main() {
  int N, M, S;
  int i;
  int u, v, r;
  struct edge *link = NULL;
  int Ans;
  scanf("%d%d", &N, &M);
  link = (struct edge *) malloc(M*sizeof(struct edge));
  for (i=0; i < M; i++)
  {
    scanf("%d%d%d", &u, &v, &r);
    if (u < v)
      link[i].x = u, link[i].y = v, link[i].wgts = r;
    else
      link[i].x = v, link[i].y = u, link[i].wgts = r;
  }
  scanf("%d", &S);
  qsort(link, M, sizeof(link[0]), mycmp);
  Ans = Kruskal(N, M, S, link);

  printf("%d\n", Ans);

  free(link);
  free(Set);
  return 0;
}
