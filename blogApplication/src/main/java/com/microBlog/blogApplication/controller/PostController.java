package com.microBlog.blogApplication.controller;

import com.microBlog.blogApplication.entity.Post;
import com.microBlog.blogApplication.payload.PostDto;
import com.microBlog.blogApplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    public PostService postService;

    //    http://localhost:8081/api/posts
    @PostMapping
    public ResponseEntity<?> createPost(@Valid@RequestBody PostDto postDto, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, return the error messages
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    http://localhost:8081/api/posts

    @GetMapping
    public List<Post> listAllPosts(){
        List<Post> posts=postService.listAllPosts();
        return posts;
    }


    //    http://localhost:8081/api/posts/{postId}
    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        Post post = postService.getByPostId(postId);
        return post;
    }
    //    http://localhost:8081/api/posts/{postId}/comments
//    @GetMapping("/{postId}/comments")
////    @CircuitBreaker(name= "commentBreaker", fallbackMethod="commentFallback")
//    public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId){
//        PostDto postDto=postService.getPostWithComments(postId);
//        return  new ResponseEntity<>(postDto,HttpStatus.OK);
//    }

//    public ResponseEntity<PostDto> commentFallback(String postId,Exception ex){
//        System.out.println("fallback is executed because service is down:" +ex.getMessage());
//        ex.printStackTrace();
//        PostDto dto= new PostDto();
//        dto.setPostId("1234");
//        dto.setTitle("Service Down");
//        dto.setContent("service down");
//        dto.setDescription("service down");
//
//        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
//    }
    }
