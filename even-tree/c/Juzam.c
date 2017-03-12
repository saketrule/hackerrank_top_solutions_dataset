/**
 * Filename:    eventree.c
 * Author:      Benoit Marcot <benoit.marcot@gmail.com.com>
 * Creation:    Tue Aug 14 2012
 *
 * Copyright (c) 2012, Benoit Marcot
 *
 */

#include <stdio.h>

struct                  node{
  int                   weight;
  struct node           *parent;
  struct node           *brothers;
  struct node           *sons;
};

static inline void      init(struct node *x)
{
  x->weight = 1;
  x->parent = NULL;
  x->brothers = NULL;
  x->sons = NULL;
}

static inline void      connect(struct node *x, struct node *y)
{
  y->parent = x;
  y->brothers = x->sons;
  x->sons = y;
}

static int              weight(struct node *x)
{
  struct node           *w = x->sons;

  while (w) {
    x->weight += weight(w);
    w = w->brothers;
  }

  return x->weight;
}

int                     main(void)
{
  int                   i;
  int                   n;
  int                   m;
  int                   c = -1;
  int                   k[2];
  struct node           *root = NULL;

  scanf("%d %d", &n, &m);
  struct node           nodes[n];

  for (i = 0; i < n; i++) {
    init(&nodes[i]);
  }
  for (i = 0; i < m; i++) {
    scanf("%d %d", &k[1], &k[0]);
    connect(&nodes[k[0] - 1], &nodes[k[1] - 1]);
    if (NULL == nodes[k[0] - 1].parent) {
      root = &nodes[k[0] - 1];
    }
  }
  weight(root);
  for (i = 0; i < n; i++) {
    if (!(nodes[i].weight % 2)) {
      c++;
    }
  }
  printf("%d", c);

  return 0;
}

