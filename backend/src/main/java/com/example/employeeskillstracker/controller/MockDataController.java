
package com.example.employeeskillstracker.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MockDataController {

    private List<Map<String, Object>> employees = new ArrayList<>();
    private List<Map<String, Object>> skills = new ArrayList<>();
    private List<Map<String, Object>> employeeSkills = new ArrayList<>();

    public MockDataController() {
        // Initialize mock data
        Map<String, Object> emp1 = Map.of("id", 1, "name", "John Doe", "email", "john.doe@fuel50.com", "jobTitle", "Software Engineer", "department", "Engineering");
        Map<String, Object> emp2 = Map.of("id", 2, "name", "Jane Smith", "email", "jane.smith@fuel50.com", "jobTitle", "Product Manager", "department", "Product");
        employees.add(emp1);
        employees.add(emp2);

        Map<String, Object> skill1 = Map.of("id", 101, "skillName", "Java", "skillCategory", "Backend Development");
        Map<String, Object> skill2 = Map.of("id", 102, "skillName", "Angular", "skillCategory", "Frontend Development");
        skills.add(skill1);
        skills.add(skill2);
    }

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees() {
        return employees;
    }

    @GetMapping("/skills")
    public List<Map<String, Object>> getSkills() {
        return skills;
    }

    @PostMapping("/employee-skills")
    public Map<String, Object> assignSkill(@RequestBody Map<String, Object> assignment) {
        employeeSkills.add(assignment);
        return assignment;
    }

    @GetMapping("/employee-skills")
    public List<Map<String, Object>> getEmployeeSkills(@RequestParam int employeeId) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> es : employeeSkills) {
            if (Objects.equals(es.get("employeeId"), employeeId)) {
                result.add(es);
            }
        }
        return result;
    }
}
