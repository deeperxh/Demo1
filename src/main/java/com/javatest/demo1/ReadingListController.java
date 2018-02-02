package com.javatest.demo1;

import antlr.collections.impl.LList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    private static  final String reader="craig";

    private ReadlinesRepository readlinesRepository;

    @Autowired
    public ReadingListController(ReadlinesRepository readlinesRepository) {
        this.readlinesRepository = readlinesRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        System.out.println("开始");
        model.addAttribute("books", new Book());
        return "readingList";
    }
    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)

    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList=readlinesRepository.findByReader(reader);
        if(readingList!=null)
        {
            model.addAttribute("books",readingList);

        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)

    public String addToReadingList(@PathVariable( "reader")String reader,Book book)
    {
        book.setReader(reader);
        readlinesRepository.save(book);
        return "redirect:/readingList/{reader}";
    }


}
