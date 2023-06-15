package com.codestates.pre_project.question.controller;

import com.codestates.pre_project.question.dto.QuestionRequestDto;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.service.QuestionService;
import com.codestates.pre_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createQuestion(@Valid @RequestBody QuestionRequestDto request) {
        questionService.createQuestion(request.toEntity());

        return Response.success();
    }

    @PatchMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateQuestion(@PathVariable("question-id") Long questionId,
                                  @Valid @RequestBody QuestionRequestDto request) {
        Question question = questionService.updateQuestion(questionId, request.toEntity());

        return Response.success(question);
    }

    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestion(@PathVariable("question-id") Long questionId) {
        Question question = questionService.getQuestion(questionId);

        return Response.success(question);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestions() {
        List<Question> questions = questionService.getQuestions();

        return Response.success(questions);
    }

    @DeleteMapping("/{question-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteQuestion(@PathVariable("question-id") Long questionId) {
        questionService.deleteQuestion(questionId);

        return Response.success();
    }
}
