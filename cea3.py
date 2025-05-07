import numpy as np
import pandas as pd
import os
import sys

file_name = input()
file_path = os.path.join(sys.path[0], file_name)

data = pd.read_csv(file_path)
df = data.values

n_att = len(df[0]) - 1
S = ['0'] * n_att
G_super = [['?' for _ in range(n_att)]]
att_val = {i: list(set(df[:, i])) for i in range(n_att)}

# Check consistency of hypotheses
def check_consistent(data, hypo):
    res = []
    for h in hypo:
        add = True
        for i in range(len(data)):
            for j in range(n_att):
                if h[j] == '?':
                    continue
                if h[j] != data[i][j] and data[i][-1] != 'No':
                    add = False
                    break
            if not add:
                break
        if add:
            res.append(h)
    return res

# Candidate Elimination
for i in range(len(df)):
    new_g = []
    if df[i][n_att] == 'No':
        for row in G_super:
            temp = list(row)
            for j in range(n_att):
                for k in att_val[j]:
                    if k != df[i][j]:
                        row[j] = k
                        if row not in new_g:
                            new_g.append(row)
                        row = list(temp)
        G_super = check_consistent(df[:i+1], new_g)
    else:
        if '0' in S:
            S = list(df[i][:-1])
        else:
            for k in range(n_att):
                if S[k] != df[i][k]:
                    S[k] = '?'
        G_super = check_consistent(df[:i+1], G_super)

print('Final Specific Hypothesis:', S)
print('Final General Hypothesis: \n', G_super)
