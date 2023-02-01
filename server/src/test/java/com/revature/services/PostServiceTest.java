package com.revature.services;

import com.revature.models.Post;
import com.revature.models.PostType;
import com.revature.models.User;
import com.revature.repositories.PostRepository;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Test
    void testGetAllPostsSuccess() {
        //creating test user
        User mockUser = new User("test.com","password","John","Doe", "JDoe");
        //creating empty ArrayList
        List<Post> expectedList = new ArrayList<Post>();
        //creating test post for ArrayList
        Post expectedPost = new Post(1,"This is a test post","image.com",new ArrayList<>(), mockUser, PostType.Top, 1);
        //adding post to array
        expectedList.add(expectedPost);
        //instantiating a postService
        PostService postService = new PostService(postRepository);
        //testing Repository method, when findAll is mocked it should return our expectedList
        when(postRepository.findAll()).thenReturn(expectedList);
        //using the post Service method and saving the result as an ArrayList
        List<Post> resultList = postService.getAll();
        //verify that findAll actually ran
        verify(postRepository).findAll();
        //using asserEquals to compare the expected size (1) to the actual result size
        assertEquals(1, resultList.size());
        //using assertEquals to compare the expectedPost from our expectedList to the 1st post returned from
        //our actual result list
        assertEquals(expectedPost, resultList.get(0));
    }


    @Test
    void upsert() {
    }

    @Test
    void getAllTopPostsSuccess() {
        User mockUser = new User("test.com","password","John","Doe", "JDoe");
        //creating empty ArrayList
        List<Post> expectedList = new ArrayList<Post>();
        //creating test post for ArrayList
        Post expectedPost = new Post(1,"This is a test post","image.com",new ArrayList<>(), mockUser, PostType.Top, 1);
        //adding post to array
        expectedList.add(expectedPost);
        //instantiating a postService
        PostService postService = new PostService(postRepository);
        when(postRepository.findAllByPostType(PostType.Top)).thenReturn(expectedList);
        //using the post Service method and saving the result as an ArrayList
        List<Post> resultList = postService.getAllTop();
        //verify that findAllByType actually ran
        verify(postRepository).findAllByPostType(PostType.Top);
        //using asserEquals to compare the expected size (1) to the actual result size
        assertEquals(1, resultList.size());
        //using assertEquals to compare the expectedPost from our expectedList to the 1st post returned from
        //our actual result list
        assertEquals(expectedPost, resultList.get(0));

    }
    @Test
    void getAllTopFail() {
        User mockUser = new User("test.com", "password", "John", "Doe", "JDoe");
        //creating empty ArrayList
        List<Post> expectedList = new ArrayList<Post>();
        //creating test post for ArrayList
        Post expectedPost = new Post(1, "This is a test post", "image.com", new ArrayList<>(), mockUser, PostType.Top, 1);
        //adding post to array
        expectedList.add(expectedPost);
        //instantiating a postService
        PostService postService = new PostService(postRepository);
        when(postRepository.findAllByPostType(PostType.Top)).thenReturn(expectedList);
        //using the post Service method and saving the result as an ArrayList
        List<Post> resultList = postService.getAllTop();
        //verify that findAllByType actually ran
        verify(postRepository).findAllByPostType(PostType.Top);
        //using asserEquals to compare the expected size (1) to the actual result size
        assertNotEquals(0, resultList.size());
        //using assertEquals to compare the expectedPost from our expectedList to the 1st post returned from
        //our actual result list
        assertNotEquals(new Post(), resultList.get(0));
    }

    @Test
    void findById() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void getFeedForUser() {
    }
}