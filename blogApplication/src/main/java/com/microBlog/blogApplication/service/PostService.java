package com.microBlog.blogApplication.service;

import com.microBlog.blogApplication.entity.Post;
import com.microBlog.blogApplication.payload.PostDto;
import com.microBlog.blogApplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    public PostDto savePost(PostDto postDto) {

        String postId= UUID.randomUUID().toString();
        postDto.setId(postId);
        Post post=mapToEntity(postDto);
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        Post newPost = postRepository.save(post);
        PostDto dto=mapToDto(post);

        dto.setId(newPost.getId());
       dto.setTitle(newPost.getTitle());
       dto.setDescription(newPost.getDescription());
       dto.setContent(newPost.getContent());
        return dto;
    }

    private PostDto mapToDto(Post post) {
        PostDto dto =new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post=new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    public List<Post> listAllPosts() {
        List<Post> newPosts = postRepository.findAll();
        return newPosts;
    }

    public Post getByPostId(String postId) {
       Optional<Post> byId = postRepository.findById(postId);
       return byId.get();
  }



}
