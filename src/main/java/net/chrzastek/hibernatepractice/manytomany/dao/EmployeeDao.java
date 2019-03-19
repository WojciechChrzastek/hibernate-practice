package net.chrzastek.hibernatepractice.manytomany.dao;

import net.chrzastek.hibernatepractice.manytomany.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
  @Query(nativeQuery = true)
  List<Employee> retrieveEmployeesWithGivenName(@Param("LASTNAME") String lastname);
}
