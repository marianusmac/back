package com.portfolio.mrn.Repository;

import com.portfolio.mrn.Entity.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//Mio
import org.springframework.stereotype.Repository;

//Mio
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
   
  List<Person> findByNombre(String nombre);
  public abstract ArrayList<Person> findByApellido(String apellido);

  public Person getById(Integer id);
  
}
