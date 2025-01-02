package com.proyecto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@SpringBootTest
class MsParticipantes1ApplicationTests {

	/**
	 * Test para comprobar la conexión del datasource
	 */
	@Autowired 
	private LocalContainerEntityManagerFactoryBean entityManager; 
	@Test
	public void conexionTest() {
	assertNotNull(entityManager, "EntityManagerFactory no debería ser nulo");
	assertNotNull(entityManager.getObject().createEntityManager(), "EntityManager no debería ser nulo"); }

}
