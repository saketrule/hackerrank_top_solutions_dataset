T = input()

while T > 0:
    N = input()
    A = map(int, raw_input().strip().split(' '))

    score = 0

    fsum = A*1
    h = {fsum[0]:0}
    for i in xrange(1,N):
        fsum[i] += fsum[i-1]
        h[fsum[i]] = i
    anchors = {0:True}      # half-points in sum-space

    if fsum[-1]==0:         # if all zeros
        score = N-1
    elif fsum[-1]%2==0:
        offset = fsum[-1]/2
        while anchors:
            for a in anchors.keys():
                if (a+offset) in h:
                    anchors[a+offset] = True
                else:
                    del anchors[a]
            if anchors:     # some offsets succeeded
                score += 1
            if offset%2==0:
                offset /= 2
            else:
                anchors = None

    print score

    T -= 1