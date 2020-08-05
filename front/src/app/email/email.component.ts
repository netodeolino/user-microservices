import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../core/api/api.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {

  public login: string;
  public form: FormGroup;
  public loading = false;
  public submitted = false;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute,
    private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      message: ['', Validators.required]
    });

    this.login = this.route.snapshot.params.login;

    if (this.login === null || this.login === '') {
      this.router.navigate(['']);
    }
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
        return;
    }

    const login: string = this.login;
    const message: string = this.f.message.value;

    this.loading = true;
    this.apiService.post('/email', { login, message })
      .subscribe(() => {
        this.loading = false;
        this.router.navigate(['']);
      }, error => {
        this.loading = false;
        console.error(error);
      });
  }
}
