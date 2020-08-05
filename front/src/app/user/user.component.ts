import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../core/api/api.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public form: FormGroup;
  public userId: string;
  public isAddMode: boolean;
  public loading = false;
  public submitted = false;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute,
    private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      id: ['', []],
      name: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.required]
    });

    let id = this.route.snapshot.params.id;

    if (id) {
      this.userId = id;
      this.isAddMode = false;

      this.apiService.get(`/user/${id}`).subscribe(res => {
        this.form.patchValue(res);
      }, error => {
        console.error(error);
      });
    } else {
      this.isAddMode = true;
    }
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    if (this.isAddMode) {
      this.createUser();
    } else {
      this.updateUser();
    }
  }

  private createUser() {
    const { name, login, email, password } = this.form.value;

    this.apiService.post('/user/save', { name, login, email, password }).subscribe(() => {
      this.loading = false;
      this.router.navigate(['']);
    }, error => {
      this.loading = false;
      console.error(error);
    });
  }

  private updateUser() {
    this.apiService.put(`/user/update/${this.userId}`, this.form.value).subscribe(() => {
      this.loading = false;
      this.router.navigate(['']);
    }, error => {
      this.loading = false;
      console.error(error);
    });
  }
}
