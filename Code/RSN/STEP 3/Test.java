/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.lang.Math.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;





public class Test
{


	public static double findEuclideanDistance(double[] vector1,double[] vector2)
	{

		double euclideanDist = 0.0;
		// check if the sizes of both vectors is same or else return -1
		if (vector1.length != vector2.length)
			return -1;
		for (int i = 1; i < vector1.length; i++)
		{
			euclideanDist = euclideanDist + (vector2[i] - vector1[i]) * (vector2[i] - vector1[i]);
		}
		euclideanDist = Math.sqrt(euclideanDist);
		return euclideanDist;
	}


	public static double KLDivergenceDistance(double[] pointA, double[] pointB)
	{
		double distance = 0.0;
		/*for (int i = 0; i < pointA.length; i++) {
double d = pointA[i] - pointB[i];
distance += d * d;
		}
		return Math.sqrt(distance);*/
				for (int i = 1; i < pointA.length; i++)
		{
					//System.out.print(pointA[i]+",");
			double d = 0;
			if (pointA[i] == 0 || pointB[i] == 0)
			{
				d = 0;
			}
			else
			{
				d = pointA[i] * (Math.log(pointA[i] / pointB[i]));
			}
			distance += d;
		}
		//System.out.println();
		for (int i = 1; i < pointB.length; i++)
		{
			//System.out.print(pointB[i]+",");
			double d = 0;
			if (pointB[i] == 0 || pointA[i] == 0)
			{
				d = 0;
			}
			else
			{
				d = pointB[i] * (Math.log(pointB[i] / pointA[i]));
			}
			distance += d;
		}
		/*System.out.println();
System.out.println(distance);*/
		return distance;
	}
	public void run()
	{
                String csvFile = "C:\\Users\\proshant\\Desktop\\17July14\\RSN-BASEDON-LDA\\STEP3\\document-topic-distributions.csv";
		String csvFile1 = "C:\\Users\\proshant\\Desktop\\17July14\\RSN-BASEDON-LDA\\STEP3\\document-topic-distributions.csv";
		File file = new File("C:\\Users\\proshant\\Desktop\\17July14\\RSN-BASEDON-LDA\\STEP3\\out.csv");//
                
                BufferedReader br = null;
		BufferedReader br1 = null;
		String line = "";
		String line1 = "";
		String cvsSplitBy = ",";
		String cvsSplitBy1 = ",";

		try
		{
                        PrintStream printStream = new PrintStream(new FileOutputStream(file));//
                        System.setOut(printStream);//
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null)
			{
				String[] arr1 = line.split(cvsSplitBy);
				double[] arr3 = new double[arr1.length];
				for (int i = 0; i < arr3.length; i++)
				{
					arr3[i] = Double.parseDouble(arr1[i]);
				}
				br1 = new BufferedReader(new FileReader(csvFile1));
				while ((line1 = br1.readLine()) != null)
				{
					String[] arr2 = line1.split(cvsSplitBy);
					double[] arr4 = new double[arr2.length];
					for (int j = 0; j < arr4.length; j++)
					{
						arr4[j] = Double.parseDouble(arr2[j]);
					}
                                        if (KLDivergenceDistance(arr3, arr4)>=2.4)
					System.out.print(arr1[0] + "," + arr2[0] + "," + KLDivergenceDistance(arr3, arr4) + "," + findEuclideanDistance(arr3, arr4) + "\n");

				}

			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}


	public static void main(String[] args)
	{
		Test obj = new Test();
		obj.run();
	}
}