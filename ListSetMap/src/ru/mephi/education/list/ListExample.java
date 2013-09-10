package ru.mephi.education.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ru.mephi.education.collections.Student;

public class ListExample {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		feelList(list, 13);
		printList(list);

		List<Student> students = new LinkedList<>();
		Student.addStudents(students);
		printList(students);

		Collections.sort(students);
		printList(students);

		Collections.sort(students);
		printList(students);

		Collections.shuffle(students);
		printList(students);

		Comparator<Student> studentComparator = new Student.StudentComparator();

		Collections.sort(students, studentComparator);
		printList(students);

		Collections.sort(students, Collections.reverseOrder(studentComparator));
		printList(students);

		Collections.sort(students, studentComparator);
		for (int i = 0; i < students.size(); ++i) {
			Student student = students.get(i);
			student.setId(i);
		}
		printList(students);

		List<Student> studentsA = students.subList(0, 6);
		List<Student> studentsB = students.subList(6, 12);
		printList(studentsA);
		printList(studentsB);

	}

	@SuppressWarnings("rawtypes")
	public static void printList(List list) {
		System.out.println(Arrays.toString(list.toArray()));
	}

	public static void feelList(List<Integer> list, int size) {
		Random random = new Random();
		for (int i = 0; i < size; ++i) {
			list.add(random.nextInt());
		}
	}

}
