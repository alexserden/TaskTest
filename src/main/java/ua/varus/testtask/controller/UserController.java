package ua.varus.testtask.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import ua.varus.testtask.model.Person;
import ua.varus.testtask.model.User;
import ua.varus.testtask.service.PersonService;
import ua.varus.testtask.service.SecurityService;
import ua.varus.testtask.service.UserService;
import ua.varus.testtask.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    private PersonService personService;
    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
         // check the validity of our data
        userValidator.validate(userForm, bindingResult);
        //if errors occur, return to the registration page
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        //if errors occur, return to the registration page
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("listPersonWelcome", this.personService.getAllPerson());
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersonAdmin", this.personService.getAllPerson());

        return "admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person) {
        // if id is zero then create a new record
        // if not then we update the record
        if (person.getId() == 0) {
            this.personService.addPerson(person);
        } else {
            this.personService.updatePerson(person);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        this.personService.removePerson(id);
        return "redirect:/admin";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getBookById(id));
        model.addAttribute("listPerson", personService.getAllPerson());

        return "admin";
    }
}
