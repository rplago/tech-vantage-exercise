
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  employees: any[] = [];
  skills: any[] = [];
  selectedEmployee: any = null;
  skillForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.skillForm = this.fb.group({
      skillId: [''],
      proficiencyLevel: ['']
    });
  }

  ngOnInit() {
    this.http.get('http://localhost:8080/api/employees').subscribe((data: any) => this.employees = data);
    this.http.get('http://localhost:8080/api/skills').subscribe((data: any) => this.skills = data);
  }

  selectEmployee(employee: any) {
    this.selectedEmployee = employee;
  }

  assignSkill() {
    const assignment = {
      employeeId: this.selectedEmployee.id,
      ...this.skillForm.value
    };

    this.http.post('http://localhost:8080/api/employee-skills', assignment).subscribe(() => {
      alert(`Skill ID ${assignment.skillId} assigned successfully with proficiency level of ${assignment.proficiencyLevel}!`);
    });
  }
}
