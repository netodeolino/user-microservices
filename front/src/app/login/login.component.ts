import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ApiService } from '../core/api/api.service';
import { UtilService } from '../core/util/util.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute,
    private router: Router, private apiService: ApiService, private utilService: UtilService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
        return;
    }

    const login: string = this.f.login.value;
    const password: string = this.f.password.value;

    this.loading = true;
    this.apiService.post('/user/login', { login, password })
      .subscribe(res => {
        this.loading = false;
        this.utilService.setToken(res.jwttoken);
        this.router.navigate(['']);
      }, error => {
        this.loading = false;
        console.error(error);
      });
  }
}
