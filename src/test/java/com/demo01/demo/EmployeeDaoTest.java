package com.demo01.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EmployeeDaoTest {

//    @Autowired
//    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveThenList() {
        employeeRepository.save(new Employee("John Doe"));

        List<String> names = StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .map(Employee::getName)
                .collect(Collectors.toList());

        assertEquals(List.of("John Doe"), names);
    }

    @Test
    void testPageable() {
        IntStream.range(100,130)
                .forEach(i -> employeeRepository.save(new Employee("John Doe " + i)));

        Page<Employee> page = employeeRepository.findAll(PageRequest.of(2, 10, Sort.by("name")));

        System.out.println(page.getTotalElements());
        System.out.println(page.getNumber());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getContent());

        assertEquals(30, page.getTotalElements());
        assertEquals(2, page.getNumber());
        assertEquals(10, page.getNumberOfElements());
    }

    @Test
    void testFindByNameIgnoreCase() {
        employeeRepository.save(new Employee("John Doe"));
        employeeRepository.save(new Employee("Jack Doe"));


        List<Employee> employees = employeeRepository.findByNameIgnoreCase("john doe");

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());

    }

    @Test
    void testFindByNameLength() {
        employeeRepository.save(new Employee("John Doe"));
        employeeRepository.save(new Employee("John John Doe"));

        assertEquals("John Doe", employeeRepository.findByNameLength(8).get(0).getName());
        /*findByNameLength selectje a metódust megelőző @Query annotáció mögött szerepel*/

    }

    @Test
    void testFindByNameStartingWith() {
        employeeRepository.save(new Employee("John Doe"));

        List<Employee> employees = employeeRepository.findByNameStartingWith("Jo");

        assertEquals("John Doe", employees.get(0).getName());
    }

}