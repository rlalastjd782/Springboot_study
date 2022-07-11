package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	

		
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		//컨트롤로에서 넘어온 idx가
		if (params.getIdx() == null) { //없다면 글쓰기
			queryResult = boardMapper.insertBoard(params);
		} else {						//있다면 글수정
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBoard(Long idx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
