package Pahuja_PA2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Pahuja_PA2.BinarySearchTree.Node;

public class Tester {

	public static void main(String[] args) {
		String[] patientNames = { "Parth", "Po", "Guda", "Mohit", "RuLasa", "Wushu", "Stan", "Marietta",
				"Harayana", "Vegeta", "Boo", "Goku", "Vivek", "Hitt", "Jousha", "Zoomer", "Mussaa", "Kojak",
				"AJax", "Tyler" };
		
		ArrayList<Patient> test = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			Patient random = new Patient(patientNames[i], rand.nextInt(10000));
			test.add(random);
		}

		System.out.println("Inorder Tree Walk\n");
		BinarySearchTree tree = new BinarySearchTree(test);
		tree.inorderTreeWalk();

		System.out.println("\n \n Insert Element\n");
		Patient newPatient = new Patient("Dhananjay", 900);
		tree.insert(newPatient);
		tree.inorderTreeWalk();

		System.out.println("\n \n Test for Delete \n");

		tree.treeDelete(tree.search(newPatient));
		tree.inorderTreeWalk();

		Hashing hash = new Hashing();
		for (int i = 0; i < 20; i++) {
			Patient random = new Patient(patientNames[i], rand.nextInt(10000));
			hash.insert(random);
		}
		
		System.out.println("Initial HashTable\n");
		hash.printTable();
		
		System.out.println("Insert in HashTable\n");
		Patient newPatient2 = new Patient("Jiren", 909);
		hash.insert(newPatient2);
		System.out.println("Patient " +newPatient2.getName()+" added!");
		hash.printTable();
		
		System.out.println("Deletion in HashTable\n");
		hash.delete(newPatient2);
		System.out.println("Patient " +newPatient2.getName()+" deleted!");
		hash.printTable();
	}
}
