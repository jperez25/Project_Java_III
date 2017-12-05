package login;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Person> {
	
	@Override
	public int compare(Person per1, Person per2) {
		String name1 = per1.getFname();
		String name2 = per2.getFname();
		
		return name1.compareTo(name2);
	}

}
