import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostServiceService } from 'src/app/service/post-service.service';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.scss']
})
export class ViewAllComponent implements OnInit{

  allPosts:any[]=[];
  constructor(private postService:PostServiceService,private snackBar:MatSnackBar){

  }

  ngOnInit(): void {
    this.getAllPosts();
  }
  getAllPosts(){
    this.postService.getAllPosts().subscribe(res=>{
        console.log(res);
        this.allPosts=res;
    },error=>{
     this.snackBar.open("Something Went Wrong!!!!","OK");
    })
  }
}
