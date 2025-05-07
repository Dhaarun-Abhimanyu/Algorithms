import csv
import os
import sys
data=[]
file_path = os.path.join(sys.path[0],input())
with open(file_path,'r') as file:
    c = csv.reader(file)
    next(c)
    for i in c:
        data.append(i)
s=[0 for _ in range(len(data[0])-1)]
g=[['?' for _ in range(len(data[0])-1)]]
sample_g=['?' for _ in range(len(data[0])-1)]
att_val = {}
for i in range(len(data[0])-1):
    att_val[i]=[]
    for row in data:
        if row[i] not in att_val[i]:
            att_val[i].append(row[i])
def check_consistent(data,hypo):
    res=[]
    
    for h in hypo:
        add=True
        for i in range(len(data)):
            for j in range(len(data[0])-1):
                if h[j]=='?':
                    continue
                else:
                    if h[j]!=data[i][j]:
                        if data[i][-1]!='No':
                            add=False
                            break
        if add:
            res.append(h)
    return res

            

for i in range(len(data)):
    new_g=[]
    if data[i][-1]=='No':
        for row in g:
            temp = list(row)
            for j in range(len(data[i])-1):
                for k in att_val[j]:
                    if k!=data[i][j] :
                        row[j]=k
                        if row not in new_g:
                            new_g.append(row)
                        row = list(temp)
        g=check_consistent(data[:i+1],new_g)
    else:
        if 0 in s:
            s=list(data[i][:-1])
        else:
            for k in range(len(data[i])-1):
                if s[k]!=data[i][k]:
                    s[k]='?'
        g=check_consistent(data[:i+1],g)
print(s)
print(g)