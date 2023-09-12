package com.example.lv5.controller;

import com.example.lv5.dto.StatusResponseDto;
import com.example.lv5.security.UserDetailsImpl;
import com.example.lv5.service.LikeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/post/{id}/like")
    public ResponseEntity<?> toggleLikePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal){
        return likeService.toggleLikePost(id, principal.getUser());
    }

    @PostMapping("/comment/{id}/like")
    public ResponseEntity<?> toggleLikeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
        return likeService.toggleLikeComment(id, principal.getUser());
    }
}
