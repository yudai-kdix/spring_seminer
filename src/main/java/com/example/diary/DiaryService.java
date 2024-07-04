package com.example.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepositry diaryRepositry;

    public DiaryEntity save(DiaryEntity diaryEntity) {
        return diaryRepositry.save(diaryEntity);
    }

    public Optional<DiaryEntity> findById(int id) {
        return diaryRepositry.findById(id);
    }

    public List<DiaryEntity> findAll() {
        return diaryRepositry.findAll();
    }
    
    public void deleteById(int id) {
        diaryRepositry.deleteById(id);
    }
}
