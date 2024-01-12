package com.example.board.service;

import com.example.board.entity.Article;
import com.example.board.entity.Board;
import com.example.board.repo.ArticleRepository;
import com.example.board.repo.BoardRepository;
import com.example.board.repo.CommentRepository;
import com.example.board.repo.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final SearchRepository searchRepository;

    // 모든 게시판 목록을 불러오는 메서드
    public List<Board> getBoardAll(){return boardRepository.findAll();}

    // 게시판id에 해당되는 게시판을 불러오는 메서드
    public Board getBoard(Long id){return boardRepository.findById(id).orElse(null);}

    // 모든 게시글들을 내림차순으로 불러오는 메서드
    public List<Article> readBoardAllDesc(){ return articleRepository.findAllByOrderByIdDesc(); }

    // 게시판id에 해당되는 게시글들을 내림차순으로 불러오는 메서드
    public List<Article> readBoardDesc(Long id){ return articleRepository.findByBoardIdOrderByIdDesc(id);}

    // 게시글id에 해당되는 게시글을 불러오는 메서드
    public Article readArticle(Long id){ return articleRepository.findById(id).orElse(null);}

    // 게시글을 만드는 메서드
    public void createArticle(
            String title,
            String content,
            String password,
            Long boardId
    ){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setPassword(password);
        article.setBoardId(boardId);
        articleRepository.save(article);
    }

    // 게시글을 수정하는 메서드
    public void updateArticle(
            Long id,
            String title,
            String content,
            String password,
            Long boardId
    ){
        Article target = articleRepository.findById(id).orElse(new Article());
        target.setTitle(title);
        target.setContent(content);
        target.setPassword(password);
        target.setBoardId(boardId);
        articleRepository.save(target);
    }

    // 게시글을 삭제하는 메서드
    // 게시글 - 댓글은 1:m의 관계로 묶어놓고 orphanRemoval = true 속성을 적용시켜놔서
    // 게시글이 삭제되면 해당 게시글의 댓글도 모두 삭제됩니다.
    public void delete(Long id){
        articleRepository.deleteById(id);
    }

}
