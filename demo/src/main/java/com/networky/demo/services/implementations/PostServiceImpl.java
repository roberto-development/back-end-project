package com.networky.demo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.PostDAO;
import com.networky.demo.dtos.PostDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Post;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.PostMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.services.interfaces.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private final PostDAO postDAO;
	
	@Autowired
	private PostMapper mapper = Mappers.getMapper(PostMapper.class);
	
	@Autowired
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	@Autowired
	public PostServiceImpl(PostDAO postDAO) {
		this.postDAO = postDAO;

	}

	@Transactional
	@Override
	public List<PostDTO> getPost(UserDTO userId) {
		/*
		 * Query<Post> theQuery = getSession().createQuery("from Post where id_user = :id")
								.setParameter("id", id);
		
		List<Post> myPosts = theQuery.list();
	
		return myPosts;
		 */
		User userDTOtoEntity = userMapper.DtoToEntityUser(userId);
		System.out.println(userDTOtoEntity.toString());
		List<PostDTO> listPostDTO = new ArrayList<PostDTO>();
		try {
			List<Post> getPostFromDb = postDAO.findPostByIdUser(userDTOtoEntity);
			
			for (Post post : getPostFromDb) {
				
				PostDTO postDto = mapper.postEntityToPostDTO(post);
				listPostDTO.add(postDto);
				System.out.println(postDto.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return listPostDTO;
	}

	@Transactional
	@Override
	public void savePost(PostDTO post) {
		Post newPost = mapper.postDtoToPostEntity(post);
		postDAO.save(newPost);
	}

	@Transactional
	@Override
	public void deletePost(PostDTO post) {
		Post deletePost = mapper.postDtoToPostEntity(post);
		postDAO.deleteByIdUser(deletePost.getIdUser());
	}
	
	
}
