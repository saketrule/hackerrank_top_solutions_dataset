N = int (raw_input ())
L = [int (i) for i in raw_input ().split ()]
while (len (L)):
    print len (L)
    L2 = []
    for i in L:
        if i - min (L) > 0:
            L2.append (i - min (L))
    L = L2