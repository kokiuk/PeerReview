package com.example.article_project.controller;

import com.example.article_project.dto.CommentDTO;
import com.example.article_project.entity.Board;
import com.example.article_project.entity.CommentEntity;
import com.example.article_project.repository.BoardRepository;
import com.example.article_project.repository.CommentRepository;
import com.example.article_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        Long result = commentService.save(commentDTO);
        if (result != null) {
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id) {
        CommentDTO deleteDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }

    ;

    @PostMapping("/delete/{id}")
    public ResponseEntity<List<CommentDTO>> deleteComment(
            @PathVariable("id") Long id,
            @RequestParam("password") String password
    ) {
        CommentEntity deletedComment = commentRepository.findById(id).orElse(null);
        if (deletedComment == null) {
            return ResponseEntity.notFound().build();
        }

        Board board = deletedComment.getBoard();
        if (!password.equals(deletedComment.getCommentWriter())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        commentRepository.deleteById(id);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        List<CommentDTO> updatedComments = commentService.findAll(board.getId());
        return ResponseEntity.ok(updatedComments);
    }
}
