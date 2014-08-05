#import MySQLdb
#db = MySQLdb.connect(host="127.0.0.1", port=3306, user="root", passwd="", db="android_review_msr2013")
#cursor = db.cursor()
import mysql.connector

db = mysql.connector.connect(user='root', password='',host='127.0.0.1',database='final_review')
cursor = db.cursor()

filename='DRCN.net'
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
   print(str1)
   fp1.write(str1)
fp1.write('*Edges\n')
sql = "select F.p1,G.id as p2,F.cnt as cnt from (select E.id as p1,D.a2 as OwnerId,D.cnt as cnt from (select C.a1 OwnerId,C.a2 a2,count(*) as cnt from (select A.a1,B.a2 from (SELECT ReviewId, AuthorId as a1 from comment where AuthorId is not NULL) A natural join (SELECT ReviewId, AuthorId as a2 from comment  where AuthorId is not NULL) B where A.a1<>B.a2)C group by C.a1,C.a2)D natural join set_d E)F natural join set_d G"
cursor.execute(sql)
results = cursor.fetchall()
for row in results:
   f1=row[0]
   f2=row[1]
   f3=row[2]
   edge= str(f1)+' ' + str(f2)+' '+ str(f3) +'\n'
   print(edge)
   fp1.write(edge)
fp1.close()
db.close()

