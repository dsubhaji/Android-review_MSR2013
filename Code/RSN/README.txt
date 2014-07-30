FOLLOWING ARE THE STEPS FOR ANY LDA BASED NETWORK GENERATION
-------------------------------------------------------------

Step 1:

Create a csv with three fields using the query.

Step 2:

Run the scala code with the tmt.0.4 program .Before it edit the file name in scala program.

Step 3:

Run the java code after editing with right file parameter in the program.

Step 4:

Find the threshold value as 65percentile of the KL distribution from out.csv.
Use R for this as below

fp = read.csv("out.csv",header = TRUE,sep = ",")
> quantile(fp$colname,.95)
     95% 
3.483977

Run the KL.py to get desired edge list which have greater or equal to threshold value.

Step 5:

Run the Rsn_query.py to generate RSN.net
  