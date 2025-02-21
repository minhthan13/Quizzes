package com.quizz.services;

import java.util.List;

import com.quizz.dtos.quiz.QuizDTO;

public interface QuizService {
    QuizDTO getQuizWithQuestions(Long quizId);

    List<QuizDTO> getQuizzess();
}
