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
G = ['?'] * n_att
G_super = []

# Initialize S1
for i in range(0, n_att):
    S[i] = df[1][i]

for i in range(0, len(df)):

    if df[i][n_att] == 'Yes':
        for j in range(0, n_att):
            if S[j] != df[i][j]:
                S[j] = '?'

        G_super = [g for g in G_super if all(g[j] == '?' or g[j] == S[j] for j in range(n_att))]

    if df[i][n_att] == 'No':
        for j in range(0, n_att):
            if df[i][j] != S[j] and S[j] != '?':
                G[j] = S[j]

                # Check if a similar G exists
                found_similar = False
                for existing_G in G_super:
                    if all((existing_G[k] == G[k] or existing_G[k] == '?' or G[k] == '?') for k in range(n_att)):
                        found_similar = True
                        break

                if not found_similar:
                    G_super.append(G.copy())
                else:
                    # Generate combinations by flipping one attribute at a time
                    for k in range(n_att):
                        if G[k] != '?':
                            new_G = G.copy()
                            new_G[k] = '?'
                            if new_G not in G_super:
                                G_super.append(new_G)

                G = ['?'] * n_att

print('Final Specific Hypothesis:', S)
print('Final General Hypothesis: \n', G_super)
