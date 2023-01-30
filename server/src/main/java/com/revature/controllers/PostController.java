package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.revature.annotations.Authorized;
import com.revature.models.Post;
import com.revature.services.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class PostController {

	private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
    	return ResponseEntity.ok(this.postService.getAll());
    }
    
    @Authorized
    @PutMapping
    public ResponseEntity<Post> upsertPost(@RequestBody Post post) {
    	return ResponseEntity.ok(this.postService.upsert(post));
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Post>> getAllTopPosts() {
        return ResponseEntity.ok(this.postService.getAllTop());
    }

    // Testing Method: Create/Add A Post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(this.postService.upsert(post));
    }

    // Testing Method: Get a Post By Id
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);

        if (!postOptional.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(postOptional.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok("this post was deleted");
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<Post> addPostLikes(@PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);
        if(!postOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Post post = postOptional.get();
        post.setLikes(post.getLikes() + 1);
        return ResponseEntity.ok(postService.upsert(post));
    }

    @PutMapping("/{id}/unlike")
    public ResponseEntity<Post> removePostLikes(@PathVariable int id) {
        Optional<Post> postOptional = postService.findById(id);
        if(!postOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Post post = postOptional.get();
        post.setLikes(post.getLikes() - 1);
        return ResponseEntity.ok(postService.upsert(post));
    }
    @PostMapping("editPost/{id}")
    public ResponseEntity<Post> editPost(@PathVariable int id, @RequestBody String editString) {
        Optional<Post> postOptional = postService.findById(id);
        if(!postOptional.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        Post newPost = postOptional.get();
        newPost.setText(editString);

        return ResponseEntity.ok(this.postService.upsert(newPost));
    }
}
