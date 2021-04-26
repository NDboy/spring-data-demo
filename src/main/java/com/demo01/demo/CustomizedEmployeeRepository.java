package com.demo01.demo;

import java.util.List;

public interface CustomizedEmployeeRepository {

    List<Employee> findByNameStartingWith(String namePrefix);
    /*CustomizedEmployeeRepository interface--
    itt tudunk olyan metódusokat definiálni, amelyek nem szerepelnek a Spring Data repositoryban, implementálni
    a CustomizedEmployeeRepositoryImpl osztályban kell, és örökíteni kell az EmployeeRepositoryban, hogy ezeket a metódusokat is elérjük*/

}
