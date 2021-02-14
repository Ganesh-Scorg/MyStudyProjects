package org.gs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MagicSquare
{
	private int matrixsquare[][] = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}};
	private int lengthofsquareside = 3;
	private List<Integer> missingNum = new LinkedList<>();
	private List<Integer> repeatedNum = new LinkedList<>();
	private int middleindex;
	private int middlelement;
	private int rowandcolsum;
	private int replacementcost;
	
	MagicSquare()
	{
		init();
	}
	
	MagicSquare(int lengthofsquareside, int[][] matrixsquare)
	{
		this.lengthofsquareside = lengthofsquareside;
		this.matrixsquare = matrixsquare;
		init();
	}
	
	void init()
	{
		for (int i = 1; i <= lengthofsquareside * lengthofsquareside; i++)
		{
			missingNum.add(i);
		}
		
		rowandcolsum = lengthofsquareside * (lengthofsquareside * lengthofsquareside + 1) / 2;
		
		if (lengthofsquareside % 2 != 0)
		{
			middleindex = lengthofsquareside / 2;
			middlelement = rowandcolsum / lengthofsquareside;
		}
	}
	
	public int[][] getMatrixsquare()
	{
		return matrixsquare;
	}
	
	public int getLengthofsquareside()
	{
		return lengthofsquareside;
	}
	
	public int getReplacementcost()
	{
		return replacementcost;
	}
	
	public void printMatrix()
	{
		for (int row = 0; row < lengthofsquareside; row++)
		{
			for (int col = 0; col < lengthofsquareside; col++)
			{
				System.out.print(" " + matrixsquare[row][col]);
			}
			
			System.out.println();
		}
	}
	
	public void convertMartixToMagicMatrix() throws Exception
	{
		int rowsum[] = new int[lengthofsquareside];
		int colsum[] = new int[lengthofsquareside];
		
		ArrayList<Integer> sqaurelist = new ArrayList<>();
		
		for (int i = 0; i < lengthofsquareside; i++)
		{
			rowsum[i] = 0;
			colsum[i] = 0;
			for (int j = 0; j < lengthofsquareside; j++)
			{
				rowsum[i] += matrixsquare[i][j];
				colsum[i] += matrixsquare[j][i];
				
				if (sqaurelist.contains(matrixsquare[i][j]))
				{
					repeatedNum.add(matrixsquare[i][j]);
				}
				else
				{
					sqaurelist.add(matrixsquare[i][j]);
				}
			}
		}
		
		missingNum.removeAll(sqaurelist);
		
		System.out.println();
		System.out.print("All Row Totals");
		for (int i = 0; i < lengthofsquareside; i++)
		{
			System.out.print(" " + rowsum[i]);
		}
		System.out.println();
		System.out.print("All Column Totals");
		for (int i = 0; i < lengthofsquareside; i++)
		{
			System.out.print(" " + colsum[i]);
		}
		
		System.out.println();
		System.out.println("Missing Numbers" + missingNum);
		System.out.println("Repeated Numbers" + repeatedNum);
		
		for (int i = 0; i < lengthofsquareside; i++)
		{
			for (int j = 0; j < lengthofsquareside; j++)
			{
				if (middleindex != 0 && i == j && j == middleindex)
				{
					if (matrixsquare[i][j] != middlelement)
					{
						replacementcost = replacementcost + Math.abs(middlelement - matrixsquare[i][j]);
						
						matrixsquare[i][j] = middlelement;
						updateSum(rowsum, colsum);
					}
					continue;
				}
				
				if ((rowsum[i] != rowandcolsum && colsum[j] != rowandcolsum) &&
					repeatedNum.contains(matrixsquare[i][j]))
				{
					
					Integer change = rowandcolsum - (colsum[j] - matrixsquare[i][j]);
					
					if (!(change >= 1 && change <= (lengthofsquareside * lengthofsquareside)))
					{
						throw new Exception("Invalid Square Matrix");
					}
					
					replacementcost = replacementcost + Math.abs(change - matrixsquare[i][j]);
					
					if (missingNum.contains(change))
					{
						matrixsquare[i][j] = change;
						missingNum.remove(change);
						repeatedNum.remove(change);
					}
					else
					{
						matrixsquare[i][j] = change;
						repeatedNum.add(change);
					}
					
					updateSum(rowsum, colsum);
				}
			}
			
		}
		System.out.println("==================Print Converted Magic Matrix=================");
		printMatrix();
	}
	
	public void updateSum(int rowsum[], int colsum[])
	{
		for (int i = 0; i < lengthofsquareside; i++)
		{
			rowsum[i] = 0;
			colsum[i] = 0;
			for (int j = 0; j < lengthofsquareside; j++)
			{
				rowsum[i] += matrixsquare[i][j];
				colsum[i] += matrixsquare[j][i];
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		
		System.out.println("==================Magical Matrix Program=================");
		System.out.println("Matrix rules as follow:");
		System.out.println(
			"1. User will be asked to enter length of a square size e.g. length = 3 will create square of size 3*3 having values 1 to sqaure(3)=9");
		System.out.println(
			"2. The sum of any row, column, or diagonal of length n is always equal to the same number");
		System.out.println(
			"3. We can convert any digit a to any other digit b in the range [1,9] at cost of |a - b|. Given s, convert it into a magic square at minimal cost. Print this cost on a new line.");
		
		MagicSquare s = new MagicSquare();
		System.out.println("Current Input Matrix");
		s.printMatrix();
		s.convertMartixToMagicMatrix();
		
		System.out
			.println("Square Matrix converted to Magic Square at the cost of " + s.replacementcost);
		
		System.out.println("===================================");
		
		int fourby4matrx[][] = {{16, 5, 3, 13}, {5, 11, 10, 8}, {9, 7, 6, 12}, {4, 14, 15, 1}};
		
		MagicSquare s2 = new MagicSquare(4, fourby4matrx);
		System.out.println("Current Input Matrix");
		s2.printMatrix();
		s2.convertMartixToMagicMatrix();
		
		System.out
			.println("Square Matrix converted to Magic Square at the cost of " + s2.replacementcost);
		
		System.out.println("===================================");
		
		int fiveby5matrix[][] = {{9, 3, 22, 16, 15}, {2, 21, 20, 14, 8}, {25, 19, 13, 7, 1},
			{18, 12, 6, 12, 24}, {11, 10, 4, 23, 17}};
		
		MagicSquare s3 = new MagicSquare(5, fiveby5matrix);
		System.out.println("Current Input Matrix");
		s3.printMatrix();
		s3.convertMartixToMagicMatrix();
		
		System.out
			.println("Square Matrix converted to Magic Square at the cost of " + s3.replacementcost);
	}
	
}
