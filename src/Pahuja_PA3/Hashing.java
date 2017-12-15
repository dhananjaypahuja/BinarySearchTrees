package Pahuja_PA3;

import java.util.Iterator;
import java.util.LinkedList;

public class Hashing {
	private final static int SIZE = 11;

	private LinkedList<Patient>[] table = (LinkedList<Patient>[]) new LinkedList[SIZE];

	public void insert(Patient value) {
		int hash = (value.getId() % 11);
		if (table[hash] == null) {
			table[hash] = new LinkedList<>();
		}
		LinkedList<Patient> hashHead = table[hash];
		hashHead.add(value);
	}

	public boolean search(Patient value) {
		int hash = (value.getId() % 11);
		LinkedList<Patient> hashHead = table[hash];
		if (hashHead != null) {
			Iterator<Patient> i = hashHead.iterator();
			while (i.hasNext()) {
				if (i.next().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void get(Patient value) {
		if (search(value) != false) {
			System.out.println("Patient " + value.getName() + " found!");
		} else
			System.out.println("Patient " + value.getName() + " not found!");
	}

	public boolean delete(Patient value) {
		int hash = (value.getId() % 11);
		LinkedList<Patient> bucket = table[hash];
		if (bucket != null) {
			Iterator<Patient> it = bucket.iterator();
			while (it.hasNext()) {
				if (it.next().equals(value)) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	
	public void printTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < SIZE; i++)
            System.out.print(table[i] +" \n");
        System.out.println();
    }

}