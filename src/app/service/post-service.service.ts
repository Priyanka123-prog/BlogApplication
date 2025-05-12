import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL= "http://localhost:8080/"
@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private http:HttpClient) { }

  createNewPost(data:FormData):Observable<any>{
        return this.http.post(BASIC_URL+`api/posts`,data);
  }

  getAllPosts():Observable<any>{
    return this.http.get(BASIC_URL+`api/posts`);
}
getPostById(postId:number):Observable<any>{
  return this.http.get(BASIC_URL+`api/posts/${postId}`);
}

likePost(postId:number):Observable<any>{
    return this.http.put(BASIC_URL+ `api/posts/${postId}/like`,{});
}

searchByname(name:string) :Observable<any>{
    return this.http.get(BASIC_URL+ `api/posts/${name}`);
}

}
