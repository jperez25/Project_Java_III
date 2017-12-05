package login;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Person>{

	@Override
	public int compare(Person per1, Person per2) {
		String name1 = per1.getLname();
		String name2 = per2.getLname();
		
		return name1.compareTo(name2);
	}

}