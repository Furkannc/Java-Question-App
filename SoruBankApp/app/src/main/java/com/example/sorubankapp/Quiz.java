package com.example.sorubankapp;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private long responseCode;
    private List<Result> results;

    @SerializedName("response_code")
    public long getResponseCode() { return responseCode; }


    @SerializedName("results")
    public List<Result> getResults() {
        return results;
    }


    static class Result {
        private String category;
        private String type;
        private String difficulty;
        private String question;
        private String correct_answer;
        private List<String> incorrect_answers;

        @SerializedName("category")
        public String getCategory() {
            return category;
        }

        @SerializedName("type")
        public String getType() {
            return type;
        }


        @SerializedName("difficulty")
        public String getDifficulty() {
            return difficulty;
        }

        @SerializedName("question")
        public String getQuestion() {
            return question;
        }

        @SerializedName("correct_answer")
        public String getCorrectAnswer() {
            return correct_answer;
        }

        @SerializedName("incorrect_answers")
        public List<String> getIncorrectAnswers() {
            return incorrect_answers;
        }

        public List<String> getAllAnswers(){
            List<String> answers=getIncorrectAnswers();
            answers.add(getCorrectAnswer());

            Collections.shuffle(answers);
            return answers;
        }

    }
}