package br.com.main;

import javax.persistence.Persistence;

public class MainTest {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("MyPU");
	}
}
