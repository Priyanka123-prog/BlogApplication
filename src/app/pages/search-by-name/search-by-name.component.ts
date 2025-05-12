import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostServiceService } from 'src/app/service/post-service.service';

@Component({
  selector: 'app-search-by-name',
  templateUrl: './search-by-name.component.html',
  styleUrls: ['./search-by-name.component.scss']
})
export class SearchByNameComponent {

  result:any=[];
  name:any="";

  constructor(private postService:PostServiceService,private snackBar:MatSnackBar){ }

  searchByName(){
    this.postService.searchByname(this.name).subscribe(res=>{
        this.result=res;
        console.log(this.result);

    },error=>{
      this.snackBar.open("Something Went Wrong !!!","OK");
    })

    
  }
}
