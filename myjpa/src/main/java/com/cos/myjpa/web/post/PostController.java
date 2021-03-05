package com.cos.myjpa.web.post;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

/**
 * ORM = Object Relation Mapping ( 자바 오브젝트 먼저 만들고 DB에 테이블을 자동생성 , 자바 오브젝트로 연관관계를
 * 맺을 수 있음 ) JPA = Java 오브젝트를 영구적으로 저장하기 위한 인터페이스(함수)
 * 
 *
 */

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;
	private final EntityManager em;

	@PostMapping("/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {

		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		}

		Post post = postSaveReqDto.toEntity();
		post.setUser(principal);
		Post postEntity = postRepository.save(post);

		// 실패 => Exception 을 탄다.
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		//d옵셔널 get(), orElseGet(), orElseThrow()
		Post  postsEntity =postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을수 없습니다.");
					});
		
		PostRespDto postRespDto = new PostRespDto(postsEntity);
		
		return new CommonRespDto<>(1,"성공",postRespDto);
		//MessageConverter가 모든 getter를 다호출해서 JSON으로 만들어준다.
	}
	
	@GetMapping("/post")
	public CommonRespDto<?> findAll(){
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1,"성공",postsEntity);
	}
	
	@PutMapping("/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id,@RequestBody PostUpdateReqDto postUpdateReqDto){
		
//		Post p = new Post();
//		em.persist(p);
//		em.createNamedQuery("select * from post");
		
		//영속화
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		Post postUpdateEntity = postRepository.save(postEntity); 
		//더티 체킹을 사용해야 하는데 그러려면 @Service 만들어야 가능함
		
		return new CommonRespDto<>(1,"성공",postUpdateEntity);
	}
	
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> deleteById(@PathVariable Long id){
		postRepository.deleteById(id);
		return new CommonRespDto<>(1,"성공",null);
	}
}
