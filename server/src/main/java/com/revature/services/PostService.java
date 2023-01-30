package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.PostType;
import org.springframework.stereotype.Service;

import com.revature.models.Post;
import com.revature.repositories.PostRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> getAll() {
		return this.postRepository.findAll();
	}

	public Post upsert(Post post) {
		return this.postRepository.save(post);
	}

	public List<Post> getAllTop() {
		return postRepository.findAllByPostType(PostType.Top);
	}

	public Optional<Post> findById(int id) {
		return postRepository.findById(id);
	}

	@Transactional
	public void deletePost(int id) { this.postRepository.deleteById(id);}
}
