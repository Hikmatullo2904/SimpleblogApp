package com.hikmatullo.edu.controller;

import com.hikmatullo.edu.Repository.PostRepository;
import com.hikmatullo.edu.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private final PostRepository postRepository;

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public String getBlogMain(Model model) {
        Iterable<Post> all = postRepository.findAll();
        model.addAttribute("posts", all);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String getBlogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String addPost(@ModelAttribute("post") Post post) {
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") Long id, Model model) {

        Optional<Post> byId = postRepository.findById(id);
        if(byId.isEmpty()) {
            return "redirect:/blog";
        }
        List<Post> list = new ArrayList<>();
        list.add(byId.get());
        model.addAttribute("posts", list);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String getEditBolg(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id).get();
        model.addAttribute("post", post);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String editBlog(@PathVariable("id") Long id, @ModelAttribute("post") Post post) {
        Post ePost = postRepository.findById(id).get();
        ePost.setTitle(post.getTitle());
        ePost.setAnons(post.getAnons());
        ePost.setViews(post.getViews());
        ePost.setFullText(post.getFullText());
        ePost.setId(id);
        postRepository.save(ePost);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/delete")
    public String deleteBolg(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/blog";
    }
}
