import { Component, OnInit } from '@angular/core';

import { ApiService } from 'src/app/core/api/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public users: Array<any> = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.get('/user').subscribe(res => {
      this.users = res;
    }, error => {
      // posso usar um handler, mas por questão de tempo vou deixar assim!
      console.error(error);
    });
  }

  public deleteUser(user: any): void {
    this.users.splice(this.users.indexOf(user), 1);

    this.apiService.delete(`/user/${user.id}`).subscribe(() => {
    }, error => {
      console.error(error);
    });
  }
}
