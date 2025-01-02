
# Proyecto 'Reyes' Master Java/JEE

Proyecto final del master de java. Se basa en un sistema que permite crear solicitudes de los ciudadanos a participar en la festividad del día de Reyes de cada CCAA.
 Consta de tres microservicios explicados a continuación:

 #### Esquema para todos los Microservicios:
 ---
    -com.proyecto -> Contiene el arranque de la app
---
    -com.proyecto.controller -> Controlador que expone los recursos (Endpoints adjuntados en fichero 'Endpoints.pdf')
---
    -com.proyecto.model -> Clases modelo y entidades
---
    -com.proyecto.repository -> Repository del recurso
---
    -com.proyecto.service -> Interfac Service e implemtación

## MS-Ccaa
Este microservicio expone los recursos referentes a la información de las Comunidades Autónomas.

#### Repository:
    Ccaa findByNombre(String nombre) -> Obtiene Comunidad por nombre
#### Service:
---
	List<Ccaa> todas() ->  Devuelve una lista con todas las CCAA disponibles
---
	void nuevaComunidad(Ccaa p) -> Alta nueva Comunidad
---
	Ccaa obtenerComunidad(String nombre) -> Comunidad por nombre
---	
	Ccaa obtenerComunidad(Long id) Comunidad por Id


## MS-Participantes
Este microservicio expone los recursos referentes a la información de los participantes
#### Repository:
    Participante findByDni(String dni) -> Obtiene Participante por DNI
#### Service:
---
	List<Participante> todos() -> Lista con todos los participantes
---
	void nuevoParticipante(Participante p) -> Alta de nuevo participante
---
	Participante obtenerParticipante(String dni) -> Participante por DNI
---
	boolean edadValida(LocalDate fechaNacimiento) -> Comprueba si la edad del participante es válida a partir de su fecha de nacimiento. Válida (>= 18 < 75) años

## MS-GestionCliente
Este microservicio expone los recursos referentes a las solicitudes de los participantes para cada Comunidad.

#### Repository:
#### -- Obtiene la gestion a partir de su Id, el participante y la Ccaa solicitada
---
    @Query("FROM Gestion g WHERE  g.gestionId.id = :cod AND g.gestionId.participante = :p   AND g.gestionId.ccaa = :c")

	Gestion comprobarGestion(@Param("cod") Long codGestion, @Param("p") String participanteDni, @Param("c") Long nombreComunidad);
---
#### -- Obtiene la gestión  a partir de su Id.

	@Query("FROM Gestion g WHERE g.gestionId.id =:cod")
	Gestion obtenerGestion(@Param("cod") Long id);
---	
#### -- Obtiene las gestiones a partir del nombre de la comunidad

	@Query("FROM Gestion g WHERE g.gestionId.ccaa =:cod")
	List<Gestion> gestionesPorComunidad(@Param("cod") Long cod);
#### Service:
---
	List<Gestion> todas() -> Muestra todas las solicitudes
---
	void nuevaGestion(Gestion g) -> Alta de nueva solicitud
---

	Gestion modificarGestion(Long id, Gestion g) -> Modificar Solicitud
---
	Gestion comprobarGestion(Gestion g) -> Devuelve una solicitud nula en caso de que no haber existente con el mismo id, participante y Ccaa
---
	Gestion obtenerGestion(Long id) -> Obtiene al solicitud por Id
---
	boolean verificarDatos(Gestion g) -> Método que comunica con MS-Participante y MS-Ccaa para conmprobar si tanto el dni del participante como el código de la comunidad existen para añadir la solicitud
---	
	List<Gestion> gestionesPorComunidad(String comunidad) -> Lista de solicitudes por nombre de la comunidad  indicada. Obtiene recurso de MS-Ccaa


## Tecnologías Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=jpa&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)


## Autores

- [@daviidhache](https://github.com/daviidhache/)
