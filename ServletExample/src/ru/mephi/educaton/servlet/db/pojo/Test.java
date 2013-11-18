package ru.mephi.educaton.servlet.db.pojo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Test
 * 
 */
@Entity
public class Test implements Serializable {

	@Id
	private Integer id;
	private String name;
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
