package com.networky.demo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.PostRepository;
import com.networky.demo.dao.UserRepository;
import com.networky.demo.dtos.PostDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Categoria;
import com.networky.demo.entities.Post;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.PostMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.services.interfaces.CategoriaService;
import com.networky.demo.services.interfaces.PostService;
import com.networky.demo.services.interfaces.UserService;

import io.jsonwebtoken.Jwts;

@Service
public class PostServiceImpl implements PostService {

	private String SIGNATURE = "networky";

	private final CategoriaService categoriaService;

	private final PostRepository postRepository;
	

	@Autowired
	private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

	@Autowired
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

	@Autowired
	public PostServiceImpl(CategoriaService categoriaService, PostRepository postRepository) {
		this.categoriaService = categoriaService;
		this.postRepository = postRepository;
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
		System.out.println("getPost " + userDTOtoEntity.toString());
		List<PostDTO> listPostDTO = new ArrayList<PostDTO>();
		try {
			List<Post> getPostFromDb = postRepository.findPostByIdUser(userDTOtoEntity);

			for (Post post : getPostFromDb) {

				PostDTO postDto = postMapper.postEntityToPostDTO(post);
				listPostDTO.add(postDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPostDTO;
	}


	@Transactional
	@Override
	public void savePost(PostDTO post) {
		String nomeCategoria = post.getCategoriaPost();
		Categoria categoriaPost = categoriaService.getCategoria(nomeCategoria);
		System.out.println(categoriaPost.toString());
		Post newPost = null; 
		newPost = postMapper.postDtoToPostEntity(post);
		newPost.setCategoriaPost(categoriaPost); 
		postRepository.save(newPost);
	}

//	@Transactional
//	@Override
//	public void deletePost(PostDTO post) {
//		Post deletePost = postMapper.postDtoToPostEntity(post);
//		postRepository.deleteByIdUser(deletePost.getAutorePost());
//	}

	@Override
	public List<PostDTO> getPosts(HttpServletRequest httpRequest) {
		String decodeBearer = httpRequest.getHeader("Authentication");
		String decodeToken = decodeBearer.replace("Bearer", "");
		Integer idAccount = (Integer) Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(decodeToken).getBody().get("id");
		List<Post> listPost = postRepository.findAllPostById(idAccount);

		List<PostDTO> returnList = postMapper.postEntityListToDTO(listPost);

		return returnList;
	}


}
