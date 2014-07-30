import os,sys

fp=open("out.csv","r")
fp1=open("out1.csv","w")

for line in fp:
    if(float(line.split(',')[2])>=1.359967):
        print(line)
        fp1.write(line)

fp.close()
fp1.close()
