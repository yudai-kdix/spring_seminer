package com.example.diary;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface DiaryRepositry extends JpaRepository<DiaryEntity, Integer>{
}
