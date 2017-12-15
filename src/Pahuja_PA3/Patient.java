package Pahuja_PA3;

public class Patient implements Comparable<Patient> {
	public String name;
	public int id;
	public int color;

	public Patient(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String toString() {
		return "Patient Details: " + name + " (" + id + ")";
	}

	/*
	 * Compares two patients based on id
	 */
	@Override
	public int compareTo(Patient p) {
		if (this.id > p.id) {
			return 1;

		} else if (this.id < p.id) {
			return -1;
		}
		return 0;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * gets ID
	 */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
