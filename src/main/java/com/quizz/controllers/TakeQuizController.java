package com.quizz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizz.dtos.quiz.QuizDTO;
import com.quizz.services.QuizService;

import java.util.Map;

@Controller
@RequestMapping("/take-quiz")
public class TakeQuizController {

    private final QuizService quizService;

    public TakeQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{quizId}")
    public String takeQuiz(@PathVariable Long quizId, Model model) {
        QuizDTO quiz = quizService.getQuizWithQuestions(quizId);
        if (quiz == null || !"PUBLISHED".equals(quiz.getStatus())) {
            return "redirect:/take-quiz/not-found";
        }
        
        model.addAttribute("quiz", quiz);
        return "take-quiz/index";
    }

    @PostMapping("/submit")
    public String submitQuiz(
            @RequestParam Long quizId,
            @RequestParam Map<String, String[]> formData,
            Model model) {
        return "take-quiz/quiz-result";
    }

    @GetMapping("/not-found")
    public String quizNotFound() {
        return "take-quiz/not-found";
    }
}
