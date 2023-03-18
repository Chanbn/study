package com.board.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.file.boardFile;

public interface FileRepository extends JpaRepository<boardFile, Long> {

}
