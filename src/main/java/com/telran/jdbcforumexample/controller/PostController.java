package com.telran.jdbcforumexample.controller;

import com.telran.jdbcforumexample.repository.PostEntity;
import com.telran.jdbcforumexample.repository.PostsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
    PostsRepository repository;

    public PostController(PostsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("posts")
    public List<PostDto> getAllPosts(){
        return repository.getAllPosts()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("posts/{id}")
    public PostDto getById(@PathVariable("id")int id){
        return toDto(repository.getPostById(id));
    }

    private PostDto toDto(PostEntity entity) {
        PostDto dto = new PostDto();
        dto.id = entity.getId();
        dto.content = entity.getContent();
        dto.dateTime = entity.getDateTime().toLocalDateTime();
        dto.userName = entity.getUserName();
        return dto;
    }


}
