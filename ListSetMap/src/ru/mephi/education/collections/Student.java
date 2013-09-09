package ru.mephi.education.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Student implements Comparable<Student> {

	private String name;

	private Integer id;

	public Student(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + "]\n";
	}

	@Override
	public int compareTo(Student o) {
		return id.compareTo(o.id);
	}

	public static void addStudents(Collection<Student> coll) {
		coll.add(new Student("Вишняк Татьяна", 7));
		coll.add(new Student("Волчков Павел", 1));
		coll.add(new Student("Дейнеко Алексей", 2));
		coll.add(new Student("Иванов Дмитрий", 9));
		coll.add(new Student("Кириченко Татьяна", 4));
		coll.add(new Student("Лубенникова Анастасия", 11));
		coll.add(new Student("Остроумов Александр", 6));
		coll.add(new Student("Перцева Екатерина", 0));
		coll.add(new Student("Пономарев Сергей", 10));
		coll.add(new Student("Попова Валерия", 3));
		coll.add(new Student("Семенихин Дмитрий", 8));
		coll.add(new Student("Шамсутдинов Ринат", 5));
	}

	public static class StudentComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
