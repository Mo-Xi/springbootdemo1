package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

//    public List<QuestionDTO> list() {
//        List<Question> questionList = questionMapper.list();
//        for (Question question : questionList) {
//            User user = userMapper.findByAccountId(question.getCreator());
//        }
//    }
}
