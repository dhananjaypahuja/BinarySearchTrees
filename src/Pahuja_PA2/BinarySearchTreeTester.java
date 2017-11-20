package Pahuja_PA2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Pahuja_PA2.BinarySearchTree.Node;

public class BinarySearchTreeTester
{
	
	
	public static void main(String[] args)
	{
		String[] patientNames = {"Parth", "Hung", "Pranika", "Monsi", "Ruchika", "Dr. Mike Wu", "Stan", "Marietta", "Hiranya", "Alvin", "Ankit", "Koosha", "Vivek", "Binal", "Jousha", "Shivangi", "Meenal", "Kajal", "Ajay", "Tyler"};
		// randomly generates codes for 20 people 
		//and also sets the code to the names, which are selected randomly
		ArrayList<Patient> test = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 20; i++)
		{
			Patient random = new Patient(patientNames[i], rand.nextInt(10000));
			test.add(random);
		}
		
		System.out.println("Test for Inorder Tree Walk\n");
		BinarySearchTree tree = new BinarySearchTree(test);
		tree.inorderTreeWalk();
		
		
		System.out.println("\n \n Test for Insert\n");
		Patient newPatient = new Patient("BOBY", 900);
		tree.insert(newPatient);  
		tree.inorderTreeWalk();
		
		System.out.println("\n \n Test for Delete \n");
		
		tree.treeDelete(tree.search(newPatient));
		tree.inorderTreeWalk();
		
		
	}
}
