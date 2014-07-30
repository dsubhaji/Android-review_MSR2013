import mysql.connector

db = mysql.connector.connect(user='root', password='',host='127.0.0.1',database='android_review_msr2013')
cursor = db.cursor()

filename='DRON.net'
fp1 = open( filename, "w" )
cm1='select distinct OwnerId from set_d'
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
sql = "select H.id1,I.id,H.cnt from (select G.id as id1,F.Own2 as OwnerId,F.cnt from (select E.Own1 as OwnerId,E.Own2,count(*) as cnt from (select D.Own1,C.OwnerId as Own2 from review C natural join (select A.OwnerId as Own1,B.ReviewId2 as ReviewId from review A natural join (SELECT ReviewId1 as ReviewId,ReviewId2 FROM `set_rsn_kldistance`) B) D)E group by E.Own1,E.Own2)F natural join set_d G)H natural join set_d I"
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
