import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PostServiceService } from 'src/app/service/post-service.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit{

  postForm!:FormGroup;
  selectedImage: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  tags:string[]=[];
  constructor(private fb:FormBuilder,
              private router:Router,
              private snackBar:MatSnackBar,
              private postService:PostServiceService){

  }
  ngOnInit(): void {
    this.postForm=this.fb.group({
      name:[null,Validators.required],
      content:[null,[Validators.required,Validators.maxLength(5000)]],
      img:[null,Validators.required],
      postedBy:[null,Validators.required]
    });
  }

  add(event:any){
   const value=(event.value || '').trim();
   if(value){
     this.tags.push(value);
   }
   event.chipInput!.clear();
  }

  remove(tag:any){
    const index=this.tags.indexOf(tag);
    if(index>=0){
       this.tags.splice(index,1);
    }
  }
  onImageSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.selectedImage = file;
  
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(file);
    }
   }
  createPost(){

    const formData = new FormData();
  formData.append('name', this.postForm.value.name);
  formData.append('content', this.postForm.value.content);
  formData.append('postedBy', this.postForm.value.postedBy);
  formData.append('tags', JSON.stringify(this.tags));
  if (this.selectedImage) {
    formData.append('img', this.selectedImage);
  }
    // const data=this.postForm.value;
    // data.tags=this.tags;

    this.postService.createNewPost(formData).subscribe(res=>{
         this.snackBar.open("Post Created Successfully !!!","OK");
         this.router.navigateByUrl("/");
    },error=>{
         this.snackBar.open("Something Went Wrong !!!","OK");
    });
  }

}