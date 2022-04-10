package com.address.book.app.controller;

import com.address.book.app.entity.Person;
import com.address.book.app.service.PersonService;
import com.address.book.app.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping(value="/")
public class IndexController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ModelAndView goIndex() {
        List<Person> persons = personService.findPerson();
        ModelAndView indexModel = new ModelAndView();

        PersonVO personVO = new PersonVO();
        indexModel.addObject("personForm", personVO);
        indexModel.addObject("contacts", persons);
        indexModel.setViewName("index");

        return indexModel;
    }
}
