package com.example.article_project.service;


import com.example.article_project.entity.Board;
import com.example.article_project.entity.BoardOfList;
import com.example.article_project.repository.BoardOfListRepository;
import com.example.article_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;
    private final BoardOfListRepository boardOfListRepository;
    public void create(
            String title,
            String password,
            String contents,
            Long boardOfListId
    ) {
        Board board = new Board();
        board.setBoardTitle(title);
        board.setBoardPassword(password);
        board.setBoardContents(contents);

        Optional<BoardOfList> optionalBoardOfList
                = boardOfListRepository.findById(boardOfListId);

        board.setBoardList(optionalBoardOfList.orElse(null));
        boardRepository.save(board);
    }

    public List<Board> findAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board readArtcle(Long id) {
        Optional<Board> optionalBoard
                = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

    public void update(Long id, String title, String contents, String password) {
        Board board = new Board();
        board.setId(id);
        board.setBoardTitle(title);
        board.setBoardContents(contents);
        board.setBoardPassword(password);
        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1 ;
        int pageLimit = 3; // 한 페이지에 몇개의 게시글 볼건지
        Page<Board> boardEntities =
        boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        Page<Board> boards = boardEntities.map(board -> new Board());
        return boards;
    }
}
