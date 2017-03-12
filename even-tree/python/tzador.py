import sys
input = sys.stdin


N, M = map(int, input.readline().split())
E = [tuple(map(lambda a: int(a) - 1, input.readline().split())) for _ in xrange(M)]
C = [0 for _ in xrange(N)]
for a, b in E:
  C[a] += 1
  C[b] += 1
P = [None for _ in xrange(N)]
S = []
for i in xrange(1, N):
  for a, b in E:
    if C[a] == 1 and C[b] > 0:
      P[a] = b
      C[a] -= 1
      C[b] -= 1
      S.append(a)
    elif C[b] == 1 and C[a] > 0:
      P[b] = a
      C[a] -= 1
      C[b] -= 1
      S.append(b)
T = [1 for _ in xrange(N)]
for a in S:
  T[P[a]] += T[a]

R = 0
for i in xrange(N):
  if P[i] is not None and T[i] % 2 == 0:
    R += 1

# print N, M, E, C
# for i in xrange(N):
#   print i + 1, ':', P[i] + 1 if P[i] is not None else None
# print
# for i in xrange(N):
#   print i + 1, ':', T[i], T[i] % 2 == 0

print R
