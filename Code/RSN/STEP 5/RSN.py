import mysql.connector

db = mysql.connector.connect(user='root', password='',host='127.0.0.1',database='android_review_msr2013')
cursor = db.cursor()

filename='RSN.net'
fp1 = open( filename, "w" )
cm1='select distinct ReviewId from set_r'
cursor.execute(cm1)
rs1 = cursor.fetchall()
vert='*Vertices '+ str(len(rs1)) + '\n'
fp1.write(vert)
pid=[]
i=0
for row1 in rs1:
   i=i+1
   str1= str(i) + ' "' + str(row1[0])+'" ' + 'box\n'
   fp1.write(str1)
fp1.write('*Edges\n')
sql = "select D.id1,C.id as id2,D.kldistance from set_r C natural join(select A.id as id1,B.ReviewId2 as ReviewId,B.kldistance from set_r A natural join (select ReviewId1 as ReviewId,ReviewId2,kldistance from set_rsn_kldistance) B) D"
cursor.execute(sql)
results = cursor.fetchall()
for row in results:
   f1=row[0]
   f2=row[1]
   f3=row[2]
   edge= str(f1)+' ' + str(f2)+' '+ str(f3) +'\n'
   fp1.write(edge)
fp1.close()
db.close()
