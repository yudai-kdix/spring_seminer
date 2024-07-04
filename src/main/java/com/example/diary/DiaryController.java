package com.example.diary;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("diaries", diaryService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAdd() {
        return "add";
    }

    @PostMapping("/save")
    public String postSave(@RequestParam("title") String title, @RequestParam("content") String content) {
        diaryService.save(new DiaryEntity(title, content, LocalDateTime.now()));
        return "add";
    }

    @GetMapping("/delete")
    public String getDelete(@RequestParam("id") int id) {
        diaryService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam Integer id, Model model) {
        Optional<DiaryEntity> diary = diaryService.findById(id);
        diary.ifPresent(value -> model.addAttribute("diary", value));
        return diary.isPresent() ? "edit" : "redirect:/";
    }

    @PostMapping("/update")
    public String postUpdate(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("content") String content) {
        Optional<DiaryEntity> diary = diaryService.findById(id);
        if (diary.isPresent()) {
            DiaryEntity diaryEntity = diary.get();
            diaryEntity.setTitle(title);
            diaryEntity.setContent(content);
            diaryService.save(diaryEntity);
        }
        return "redirect:/";
    }
}
