package auth.controller;

import auth.domain.Employee;
import auth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employees;

    @Autowired
    private RestTemplate rest;

    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> employeeList = StreamSupport.stream(
                this.employees.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return employeeList;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(this.employees.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        employees.save(employee);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employees.delete(employee);

        return ResponseEntity.ok().build();
    }
}
