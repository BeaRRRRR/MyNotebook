package com.misha.notebook.Controller;


import com.misha.notebook.Entity.Note;
import com.misha.notebook.Service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/notebook")
public class MainController {

    @Autowired
    private NoteServiceImpl noteService;

    private String sortMethod = "", filterMethod = "";
    private int pageSize = 30, currentPage = 0;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddForm() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNote(String message) {
        if (!message.isEmpty()) {
            noteService.addNote(new Note(message));
        }
        return "redirect:/notebook";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "pagesize", required = false) Integer pageSize,
                       @RequestParam(value = "currentpage", required = false) Integer currentPage) {
        if (pageSize != null) this.pageSize = pageSize;
        if (currentPage != null) this.currentPage = currentPage;
        if (findAllWithFilter().size() < (this.pageSize * this.currentPage)) {
            this.currentPage = 0;
        }
        List<Note> notes = sort(this.filterMethod, this.sortMethod);
        boolean isActive = findAllWithFilter().size() - (this.pageSize * (this.currentPage + 1)) > 0;
        model.addAttribute("notes", notes);
        model.addAttribute("currentPage", this.currentPage);
        model.addAttribute("isActive", isActive);
        return "index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteNote(id);
        if (findAllWithFilter().size() - (this.pageSize * (this.currentPage)) <= 0)
            this.currentPage = this.currentPage - 1;
        return "redirect:/notebook?currentpage=" + this.currentPage;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editNote(String message, Long id, boolean done) {
        if (!message.isEmpty()) {
            Note note = noteService.getNoteById(id);
            note.setMessage(message);
            note.setDone(done);
            noteService.updateNote(note);
        }
        return "redirect:/notebook";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditForm(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("oldMessage", noteService.getNoteById(id).getMessage());
        model.addAttribute("isDone", noteService.getNoteById(id).isDone());
        return "edit";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public String getSortMethod(@RequestParam(value = "filter_method", required = false) String filterMethod,
                                @RequestParam(value = "sort_method", required = false) String sortMethod) {
        if (sortMethod != null) {
            this.sortMethod = sortMethod;
        }
        if (filterMethod != null) {
            this.filterMethod = filterMethod;
        }
        return "redirect:/notebook";
    }

    public List<Note> sort(String filterMethod, String sortMethod) {
        noteService.setCurrentPage(currentPage);
        noteService.setPageSize(pageSize);

        switch (filterMethod) {
            case "Done":
                switch (sortMethod) {
                    case "Date_ASC":
                        return noteService.findByDone(true, new Sort(Sort.Direction.ASC, "date"));
                    case "Date_DESC":
                        return noteService.findByDone(true, new Sort(Sort.Direction.DESC, "date"));


                    default:
                        return noteService.findByDone(true, new Sort(Sort.Direction.ASC, "date"));
                }
            case "Not_Done":
                switch (sortMethod) {
                    case "Date_ASC":
                        return noteService.findByDone(false, new Sort(Sort.Direction.ASC, "date"));
                    case "Date_DESC":
                        return noteService.findByDone(false, new Sort(Sort.Direction.DESC, "date"));


                    default:
                        return noteService.findByDone(false, new Sort(Sort.Direction.ASC, "date"));
                }


            case "All":
            default:
                switch (sortMethod) {
                    case "Date_ASC":
                        return noteService.findAllOrderByDateAsc();
                    case "Date_DESC":
                        return noteService.findAllOrderByDateDesc();


                    default:
                        return noteService.findAllOrderByDateAsc();
                }

        }

    }

    public List<Note> findAllWithFilter() {
        if (this.filterMethod.equals("All")) {
            return noteService.findAll();
        } else if (this.filterMethod.equals("Done")) {
            return noteService.findAll().stream().filter(Note::isDone).collect(Collectors.toList());
        } else if (this.filterMethod.equals("Not_Done")) {
            return noteService.findAll().stream().filter(Note::isDone).collect(Collectors.toList());
        } else {
            return noteService.findAll();
        }
    }


}


