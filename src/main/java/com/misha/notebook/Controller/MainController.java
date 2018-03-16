package com.misha.notebook.Controller;


import com.misha.notebook.Entity.Note;
import com.misha.notebook.Service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/notebook")
public class MainController {

    @Autowired
    private NoteServiceImpl noteService;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView getAddForm(){
        return new ModelAndView("add");
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addNote(String message){
        if(!message.isEmpty()){
            noteService.addNote(new Note(message));
        }
        return "redirect:/notebook";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("notes",noteService.findAllOrOrderByDateAsc());
        return "index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteNote(@RequestParam Long id){
        noteService.deleteNote(id);
        return "redirect:/notebook";
    }

}


