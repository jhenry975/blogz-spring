package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
    
    //List<Post> findByAuthor(int authorId);
    
    // TODO - add method signatures as needed
    
    //MY CODE//
    Post findByUid(int uid);
    
    List<Post> findAll();
    
    //Post findByUsername(String username);
    
    List<Post>findByAuthor(User author);
    
    //List<Post> allPostsByUsername(String username);

	//Post findSinglePost(String username, int uid);
	//END MY CODE//
	
}
