package com.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.file.File;


public interface AttachmentRepository extends JpaRepository<File, Long> {

}
