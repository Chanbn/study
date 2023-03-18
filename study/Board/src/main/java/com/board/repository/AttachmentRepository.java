package com.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.file.boardFile;


public interface AttachmentRepository extends JpaRepository<boardFile, Long> {

}
