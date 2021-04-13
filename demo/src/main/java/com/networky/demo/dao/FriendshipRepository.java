package com.networky.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networky.demo.entities.Friendship;
import com.networky.demo.entities.Status;
import com.networky.demo.entities.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

	List<Friendship> findByStatusAndIdSender(Status status, User user);
	
	@Query("SELECT a from Friendship a where a.status.id=:status and a.idSender.id=:user")
	List<Friendship> findAllByStatusAndIdSenderHibernate(@Param(value="status") Integer status,@Param(value="user") Integer user);
	
//	@Query("SELECT a from Friendship a where a.status=:status and a.idSender=:user")
//	List<Friendship> findAllByStatusAndIdSenderHibernateEntity(@Param(value="status") Status status,@Param(value="user") User user);

}
