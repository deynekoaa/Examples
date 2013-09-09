package ru.mephi.education.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import ru.mephi.education.collections.Student;

public class SetExample {

	public static void main(String[] args) {

		Set<Student> students = new HashSet<>();
		Student.addStudents(students);
		printSet(students);

		students = new TreeSet<>(students);
		printSet(students);

		Set<Student> studentsSorted = new TreeSet<>(
				new Student.StudentComparator());
		studentsSorted.addAll(students);
		printSet(studentsSorted);

		Iterator<Student> iterator = studentsSorted.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			iterator.next().setId(i++);
		}
		printSet(studentsSorted);
		printSet(students);

		if (studentsSorted.containsAll(students)) {
			System.out.println("Sets equals");
		}

	}

	public static void printSet(Set set) {
		System.out.println(Arrays.toString(set.toArray()));
	}

}
