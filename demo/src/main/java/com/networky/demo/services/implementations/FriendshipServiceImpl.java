package com.networky.demo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.FriendshipDAO;
import com.networky.demo.dtos.FriendshipDTO;
import com.networky.demo.entities.Friendship;
import com.networky.demo.entities.Status;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.FriendshipMapper;
import com.networky.demo.services.interfaces.FriendshipService;

@Service
public class FriendshipServiceImpl implements FriendshipService {

	private final FriendshipDAO friendDao;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private FriendshipMapper fMapper = Mappers.getMapper(FriendshipMapper.class);

	@Autowired
	public FriendshipServiceImpl(FriendshipDAO friendDao) {
		this.friendDao = friendDao;
	}

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	@Transactional
	public void sendFriendship(FriendshipDTO friendship) {

		Friendship newFriendshipEntity = fMapper.dtoToFriendshipEntity(friendship);
		friendDao.save(newFriendshipEntity);

	}

	@Override
	public Friendship saveFriendship(User idSender, User idRecipient, Status status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFriendship(FriendshipDTO friendshipDTO) {
		Friendship deleteFriendship = fMapper.dtoToFriendshipEntity(friendshipDTO);
		// TODO Auto-generated method stub
		friendDao.delete(deleteFriendship);
	}


	@Override
	@Transactional
	public List<Friendship> getFriendships(FriendshipDTO friendshipDTO) {
//		
		Friendship findEntity = fMapper.dtoToFriendshipEntity(friendshipDTO);
		List<Friendship> list= new ArrayList<Friendship>();
		try {
			list = friendDao.findAllByStatusAndIdSenderHibernate(findEntity.getStatus().getId(), findEntity.getIdSender().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
