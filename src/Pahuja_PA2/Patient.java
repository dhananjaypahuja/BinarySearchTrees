package Pahuja_PA2;

public class Patient implements Comparable<Patient> {
	public String name;
	public int id;

	public Patient(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String toString() {
		return "The Patient Name with code: " + name + " (" + id + ")";
	}

	/*
	 * Compares id to determine which patient has higher priority
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
	 * gets the id of the patient
	 */
	public int getId() {
		return id;
	}

}
