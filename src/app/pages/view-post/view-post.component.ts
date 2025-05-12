import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from 'src/app/service/comment.service';
import { PostServiceService } from 'src/app/service/post-service.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss']
})
export class ViewPostComponent implements OnInit {

   postId=this.activatedRoute.snapshot.params['id'];
   postData:any;
   comments:any;

   commentForm!:FormGroup;
 
   constructor(private postService:PostServiceService,
    private activatedRoute:ActivatedRoute,
    private matSnackbar:MatSnackBar,
    private fb:FormBuilder,
    private commentService:CommentService){}

    ngOnInit(): void {
      console.log(this.postId);
      this.getPostById();

      this.commentForm=this.fb.group({
        postedBy:[null,Validators.required],
        content:[null,Validators.required]
      })
    }

    publishComment(){
      const postedBy=this.commentForm.get('postedBy')?.value;
      const content=this.commentForm.get('content')?.value;

      this.commentService.createComment(this.postId,postedBy,content).subscribe(res=>{
        this.matSnackbar.open("Comment Published Successfully","OK");
        this. getCommentsByPost();
      },error=>{
        this.matSnackbar.open("Something Went Wrong!!!","OK");
      })
    }

    getCommentsByPost(){
      this.commentService.getAllCommentsByPost(this.postId).subscribe(res=>{
        this.comments=res;
      },error=>{
        this.matSnackbar.open("Something Went Wrong!!!","OK");
      });
    }

    getPostById(){
      this.postService.getPostById(this.postId).subscribe(res=>{
          this.postData=res;
          console.log(res);
          this.getCommentsByPost();
      },error=>{
        this.matSnackbar.open("Something Went Wrong!!!","OK");
      });
    }

    likePost(){
      this.postService.likePost(this.postId).subscribe(response=>{
      this.matSnackbar.open("Post Liked Successfully","OK");
      this.getPostById();
      },error=>{
        this.matSnackbar.open("Something Went Wrong!!!","OK");
      })
    }
}
