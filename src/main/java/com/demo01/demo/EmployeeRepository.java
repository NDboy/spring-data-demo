package com.demo01.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    /*a PagingAndSortingRepository interface és az ősei elég sok statikus metódust tartalmaznak alapból, de ha ez nem lenne elég...*/

    List<Employee> findByNameIgnoreCase(String name); /*az api generálja le a metódus törzset a névben szereplő kulcsszavak alapján :https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation*/

    @Query("select e from Employee e where length(e.name) = :nameLength")
    List<Employee> findByNameLength(@Param("nameLength") int nameLength);





}
