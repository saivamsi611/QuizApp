# QUIZ MASTER MOBILE APP

**INTEN ID:** CITS2812
**FULL NAME:** P. Sai Vamsi
**NO. OF WEEKS:** 3 Week
**PROJECT NAME:** Quiz Master Mobile App

## Project Scope

This Quiz Master App helps users test and improve their knowledge through interactive quizzes. The application allows users to select quiz categories, answer multiple-choice questions, view scores, review answers, and track quiz history. Questions and scores are stored locally using Room Database. The application is developed using Kotlin, Jetpack Compose, Room Database, and MVVM Architecture.

## Technologies Used

* Kotlin
* Jetpack Compose
* Room Database
* MVVM Architecture
* StateFlow
* Navigation Compose

## Features

* Category-Based Quizzes
* Multiple Choice Questions
* Score Calculation
* Review Answers
* Score History Tracking
* Room Database Integration
* Dynamic UI Updates

## Core Operations

### Create

Users can add new quiz questions to the Room Database.

### Read

Users can view and attempt quiz questions from selected categories.

### Update

Users can update existing quiz questions and answers.

### Delete

Users can delete quiz questions and clear score history.

### Quiz Attempt

Users can select a category and answer multiple-choice questions.

### Result Evaluation

The application calculates the final score and displays the percentage.

### Score History

Users can view previous quiz attempts and performance records.

## State Management

The application uses StateFlow and Compose State to automatically update the UI whenever questions, scores, or quiz progress change. This ensures a responsive and interactive user experience.

## Screens

### Home Screen

* Start Quiz
* View Score History
* Navigate to Categories

### Category Screen

* Select Quiz Category
* Start Quiz

### Quiz Screen

* Display Questions
* Select Answers
* Navigate Through Questions
* Submit Quiz

### Result Screen

* View Score
* View Percentage
* Review Answers

### Review Screen

* View Correct Answers
* Compare User Answers

### Score History Screen

* View Previous Quiz Scores
* Clear Score History

## Architecture

UI Layer (Jetpack Compose)

↓

ViewModel Layer

↓

Repository Layer

↓

Room Database (DAO)

↓

SQLite Database

## Conclusion

The Quiz Master Mobile App provides an interactive platform for learning and self-assessment. It demonstrates Room Database integration, MVVM architecture, state management, navigation, and modern Android development practices using Jetpack Compose and Kotlin.
