package com.quizz.entities;
// Generated Feb 20, 2025, 8:41:28 PM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * UserAnswers generated by hbm2java
 */
@Entity
@Table(name = "user_answers")
public class UserAnswers implements java.io.Serializable {

    private Long userAnswerId;
    private Answers answers;
    private Questions questions;
    private QuizzAttempts quizzAttempts;
    private Boolean isCorrect;
    private Date createdAt;

    public UserAnswers() {
    }

    public UserAnswers(Answers answers, Questions questions, QuizzAttempts quizzAttempts, Boolean isCorrect,
	    Date createdAt) {
	this.answers = answers;
	this.questions = questions;
	this.quizzAttempts = quizzAttempts;
	this.isCorrect = isCorrect;
	this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "user_answer_id", unique = true, nullable = false)
    public Long getUserAnswerId() {
	return this.userAnswerId;
    }

    public void setUserAnswerId(Long userAnswerId) {
	this.userAnswerId = userAnswerId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    public Answers getAnswers() {
	return this.answers;
    }

    public void setAnswers(Answers answers) {
	this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    public Questions getQuestions() {
	return this.questions;
    }

    public void setQuestions(Questions questions) {
	this.questions = questions;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    public QuizzAttempts getQuizzAttempts() {
	return this.quizzAttempts;
    }

    public void setQuizzAttempts(QuizzAttempts quizzAttempts) {
	this.quizzAttempts = quizzAttempts;
    }

    @Column(name = "is_correct")
    public Boolean getIsCorrect() {
	return this.isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
	this.isCorrect = isCorrect;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", length = 19)
    public Date getCreatedAt() {
	return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

}
