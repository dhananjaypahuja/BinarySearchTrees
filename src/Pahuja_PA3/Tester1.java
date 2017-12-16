package Pahuja_PA3;

import java.util.ArrayList;
import java.util.Random;

public class Tester1 {

	public static void main(String[] args) {
		String[] patientNames = { "Parth", "Po", "Guda", "Mohit", "RuLasa", "Wushu", "Stan", "Marietta", "Harayana",
				"Vegeta", "Boo", "Goku", "Vivek", "Hitt", "Jousha", "Zoomer", "Mussaa", "Kojak", "AJax", "Tyler" };

		ArrayList<Patient> test = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			Patient random = new Patient(patientNames[i], rand.nextInt(10000));
			test.add(random);
		}

		System.out.println("RBTree\n");
		RBtree tree = new RBtree(test);
		tree.preOrderPrint();

		System.out.println("\n\nRBTree - Insertion Test\n");
		Patient newPatient = new Patient("Dhananjay", 5100);
		tree.RBinsert(newPatient);
		tree.preOrderPrint();
		
		System.out.println("\n\nRBTree - Searching Test (Pt1)\n");
		Patient s = tree.RBsearch(newPatient);
		tree.printSearchResult(s);
		
		System.out.println("\n\nRBTree - Deletion Test\n");
		tree.RBdelete(newPatient);
		tree.preOrderPrint();
		
		System.out.println("\n\nRBTree - Searching Test (Pt2)\n");
		s = tree.RBsearch(newPatient);
		tree.printSearchResult(s);
	}
}
