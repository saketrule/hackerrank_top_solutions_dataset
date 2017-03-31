__author__ = 'Ana'


import sys

def main():
    global values
    inName=''
    outName=''


    if inName is not '' :
        sys.stdin=open(inName,'r')


    if outName is not '':
        sys.stdout=open(outName,'w')


    file=sys.stdin
    n=int(file.readline())
    vals=file.readline().split(' ')
    ints=[]
    for i in range(n):
        ints.append(int(vals[i]))

    vals=file.readline().split(' ')
    p,q=int(vals[0]), int(vals[1])
    maximum=0
    value=0
    ints=sorted(ints)
    for i in range(n-1):
        tmp=(ints[i+1]-ints[i])/2
        if tmp>maximum and ints[i]+tmp>=p and ints[i]+tmp<=q:
            maximum=tmp
            value=ints[i]+tmp

    m1=find_min(p,ints,0, len(ints)-1)
    if m1>=maximum:
        maximum=m1
        value=p
    m1=find_min(q,ints,0,len(ints)-1)
    if m1>maximum:
        maximum=m1
        value=q
    print value



def find_min(i,ints,start, end):
    if i>=ints[end]:
        return i-ints[end]
    if i<=ints[start]:
        return ints[start]-i
    if len(ints)==1:
        return abs(i-ints[0])
    if len(ints)==0:
        return 10**9
    mid=start+(end-start)/2
    diff=abs(i-ints[mid])
    if mid>start and abs(i-ints[mid-1])<=diff:
        return find_min(i,ints,start, mid-1)
    elif mid+1<=end and abs(i-ints[mid+1])<diff:
        return find_min(i,ints,mid+1, end)
    return diff


main()
