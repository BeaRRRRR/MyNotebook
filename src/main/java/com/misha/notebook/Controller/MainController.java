package com.misha.notebook.Controller;


import com.misha.notebook.Entity.Note;
import com.misha.notebook.Service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/notebook")
public class MainController {

    @Autowired
    private NoteServiceImpl noteService;

    private String sortMethod = "";
    private int pageSize = 30,currentPage = 0;




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
    public String list(Model model,@RequestParam(value = "pagesize",required = false) Integer pageSize,
                       @RequestParam(value = "currentpage",required = false) Integer currentPage){
        if(pageSize!=null) this.pageSize = pageSize;
        if (currentPage!=null) this.currentPage = currentPage;;

        model.addAttribute("notes",sort(sortMethod));
        model.addAttribute("currentPage",currentPage);
        return "index";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteNote(@RequestParam Long id){
        noteService.deleteNote(id);
        return "redirect:/notebook";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editNote(String message,Long id,boolean done){
        if(!message.isEmpty()){
            Note note = noteService.getNoteById(id);
            note.setMessage(message);
            note.setDone(done);
            noteService.updateNote(note);
        }
         return "redirect:/notebook";

    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String getEditForm(@RequestParam Long id,Model model){
        model.addAttribute("id",id);
        model.addAttribute("oldMessage",noteService.getNoteById(id).getMessage());
        return "edit";
    }

    @RequestMapping(value = "/sort",method = RequestMethod.GET)
    public String getSortMethod(@RequestParam String method){
        sortMethod = method;
        return "redirect:/notebook";
    }

    public List<Note> sort(String sortMethod){
        noteService.setCurrentPage(currentPage);
        noteService.setPageSize(pageSize);
        switch (sortMethod){
            case "Date_ASC" : return noteService.findAllOrderByDateAsc();
            case "Date_DESC" : return noteService.findAllOrderByDateDesc();
            case "Done" : return noteService.findByDone(true);
            case "Not_Done" : return noteService.findByDone(false);

            default: return noteService.findAllOrderByDateAsc();
        }

    }

    @RequestMapping(value = "/changeDoneToTrue",method = RequestMethod.GET)
    public String changeDoneToTrue(@RequestParam Long id){
        noteService.getNoteById(id).setDone(true);
        return "redirect:/notebook";
    }

}


