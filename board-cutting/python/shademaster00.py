t = int(raw_input())
for x in range(t):
    who_cares = raw_input()
    costs = []
    total = 0
    mult = [1,1]

    for i in range(2):
        costs += [(int(a),i) for a in raw_input().split()]

    for cut in reversed(sorted(costs)):
        total += (cut[0] * mult[cut[1]]) % (10**9 + 7)
        total %= (10**9 + 7)
        mult[not cut[1]] += 1

    print total
    

