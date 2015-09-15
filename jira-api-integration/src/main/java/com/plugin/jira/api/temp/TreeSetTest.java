package com.plugin.jira.api.temp;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


class Person implements Comparable<Person>{
	
	Person(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	private String name;
	private Integer id;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int compareTo(Person other) {
		return this.id.compareTo(other.id);
	}
	
}


public class TreeSetTest {

	
	public static void main(String[] args) {
		generateStaticData();
	}
	
	private static Set<Person> generateStaticData(){
		long beforeTime = System.currentTimeMillis();
		Set<Person> set = new TreeSet<Person>();
		for(int i=0;i<10_00_000;i++){
			set.add(new Person(i, "This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. This is long name. "+i));
		}
		
		System.out.println("Time taken : "+ ((System.currentTimeMillis() - beforeTime)/1000));
		return null;
	}
}
