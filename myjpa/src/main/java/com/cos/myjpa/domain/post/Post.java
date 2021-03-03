package com.cos.myjpa.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//테이블 자동생성
public class Post {
	@Id //pk설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Table,auto_increment,Sequence
	private Long id;
	@Column(length = 60,nullable = false)
	private String title;
	@Lob    //대용량 데이터
	private String content;
	
	@CreationTimestamp //자동으로 현재 시간이 들어감.
	private LocalDateTime createDate;
}
