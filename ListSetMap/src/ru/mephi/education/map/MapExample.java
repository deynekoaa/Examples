package ru.mephi.education.map;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import ru.mephi.education.collections.Student;

public class MapExample {

	public static void main(String[] args) {
		Map<Student, Integer> map = new HashMap<>();
		Set<Student> students = new HashSet<>();
		Student.addStudents(students);

		Random rnd = new Random();
		for (Student student : students) {
			map.put(student, (rnd.nextInt() % 3) + 3);
		}
		printMap(map);

		map = new TreeMap<>(map);
		printMap(map);

		Map<Student, Integer> sortedMap = new TreeMap<>(
				new Student.StudentComparator());
		sortedMap.putAll(map);
		printMap(sortedMap);

		printCollection(sortedMap.values());
		printCollection(sortedMap.keySet());

		int i = 0;
		for (Entry<Student, Integer> entry : sortedMap.entrySet()) {
			entry.getKey().setId(i++);
			entry.setValue(5);
		}
		printMap(sortedMap);
		printMap(map);

		map.put(new Student("Гарри Поттер", 14), 5);
		map.put(new Student("Гермиона Грэйнджер", 12), 5);
		map.put(new Student("Рон Уизли", 13), 5);
		printMap(map);
	}

	public static void printMap(Map<Student, Integer> map) {
		System.out.println(Arrays.toString(map.entrySet().toArray()));
	}

	public static void printCollection(Collection coll) {
		System.out.println(Arrays.toString(coll.toArray()));
	}

}
