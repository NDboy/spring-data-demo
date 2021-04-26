package com.demo01.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedEmployeeRepositoryImpl implements CustomizedEmployeeRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findByNameStartingWith(String namePrefix) {
        return entityManager.createQuery("select e from Employee e where e.name like :namePrefix", Employee.class)
                .setParameter("namePrefix", namePrefix + "%")
                .getResultList();
    }

    /*CustomizedEmployeeRepository interface--
    itt tudunk olyan metódusokat definiálni, amelyek nem szerepelnek a Spring Data repositoryban, implementálni
    a CustomizedEmployeeRepositoryImpl osztályban kell, és örökíteni kell az EmployeeRepositoryban, hogy ezeket a metódusokat is elérjük*/
}
