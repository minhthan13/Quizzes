package com.quizz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.quizz.entities.Quizzes;

public interface QuizRepository extends CrudRepository<Quizzes, Long>, PagingAndSortingRepository<Quizzes, Long> {
}
