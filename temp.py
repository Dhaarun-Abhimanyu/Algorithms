import os, sys
import csv
import numpy as np
import pandas as pd
file_name = input()
file_path = os.path.join(sys.path[0], file_name)

data = {
    'X': [],
    'Y': []
}

df = pd.read_csv(file_name, header=None, names=['X', 'Y'])
        
x = df[['X']]
y = df[['Y']]

xm = 0
ym = 0
for i,j in zip(x['X'], y['Y']):
    xm += i
    ym += j
xm /= len(x)
ym /= len(y)
cov = 0
var = 0

for i,j in zip(x['X'], y['Y']):
    cov += (i - xm) * (j - ym)
    var += (i - xm)**2
    
cov /= len(x)
var /= len(x)

b1 = cov/var
b0 = ym - b1*xm
    
print('Coefficents: b0:','%.4f'%b0,' b1:','%.4f'%b1)    

