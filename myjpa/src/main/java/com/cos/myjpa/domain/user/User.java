package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id //pk설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Table,auto_increment,Sequence
	private Long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp //자동으로 현재 시간이 들어감.
	private LocalDateTime createDate;
}
