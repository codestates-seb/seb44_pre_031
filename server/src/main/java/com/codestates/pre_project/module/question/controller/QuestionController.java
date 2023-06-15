package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.module.question.dto.QuestionRequestDto;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.service.QuestionService;
import com.codestates.pre_project.module.response.Response;
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
        // TODO: memberId 가져오는 로직 추가
        questionService.createQuestion(memberId, request.toEntity());

        return Response.success();
    }

    @PatchMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateQuestion(@PathVariable("question-id") Long questionId,
                                  @Valid @RequestBody QuestionRequestDto request) {
        questionService.updateQuestion(questionId, request.toEntity());

        return Response.success();
    }

    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestion(@PathVariable("question-id") Long questionId) {
        Question question = questionService.getQuestion(questionId);

        // TODO : 응답 DTO 새로 작성
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
