package com.address.book.app.controller;

import com.address.book.app.entity.Person;
import com.address.book.app.service.PersonService;
import com.address.book.app.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ModelAndView savePerson(@ModelAttribute("personForm") PersonVO personVO) {
        Person person = personVO.deepCopyToPerson();
        Person savedPerson = personService.savePerson(person);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("{id}")
    public ResponseEntity findPerson(@PathVariable Integer id) {
        Person person = personService.findPerson(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping("edit/{id}")
    public ModelAndView editPerson(@PathVariable Integer id) {
        Person person = personService.findPerson(id);
        ModelAndView editModel = new ModelAndView();

        PersonVO personVO = PersonVO.deepCopyFromPerson(person);
        editModel.addObject("personForm", personVO);
        editModel.addObject("id", personVO.getId());
        editModel.addObject("showHomeBtn", "Y");

        editModel.setViewName("edit");
        return editModel;
    }

    @PostMapping("update/{id}")
    public ModelAndView updatePerson(@PathVariable Integer id, @ModelAttribute("personForm") PersonVO personVO) {
        Person person = personVO.deepCopyToPerson();
        Person updatedPerson = personService.updatePerson(person);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("delete/{id}")
    public ModelAndView deletePerson(@PathVariable Integer id) {
        boolean result = personService.removePerson(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("search")
    public ModelAndView findPersonBySearchKey(HttpServletRequest request) {
        String searchKey = request.getParameter("personName");
        ModelAndView indexModel = new ModelAndView();
        List<Person> persons = StringUtils.hasLength(searchKey) ? personService.findPersonsSearchKey(searchKey) : Collections.EMPTY_LIST;
        indexModel.addObject("searchEmpty", persons.isEmpty()?"Y":"N");
        indexModel.addObject("contacts", persons);
        PersonVO personVO = new PersonVO();
        indexModel.addObject("personForm", personVO);
        indexModel.addObject("showHomeBtn", "Y");

        indexModel.setViewName("index");
        return indexModel;
    }
}
