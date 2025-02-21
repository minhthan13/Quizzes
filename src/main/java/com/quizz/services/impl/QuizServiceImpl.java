package com.quizz.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.quizz.dtos.quiz.QuizDTO;
import com.quizz.entities.Quizzes;
import com.quizz.repositories.QuizRepository;
import com.quizz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper) {
	this.quizRepository = quizRepository;
	this.modelMapper = modelMapper;
    }

    @Override
    public QuizDTO getQuizWithQuestions(Long quizId) {
	Quizzes quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

	QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);

	// Limit to 10 questions if needed
	if (quizDTO.getQuestions().size() > 10) {
	    quizDTO.setQuestions(quizDTO.getQuestions().subList(0, 10));
	}

	return quizDTO;
    }

    @Override
    public List<QuizDTO> getQuizzess() {
	return modelMapper.map(quizRepository.findAll(), new TypeToken<List<QuizDTO>>() {
	}.getType());

    }
}
