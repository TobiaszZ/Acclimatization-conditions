package environmentalConditions.controller;

import environmentalConditions.model.EnviromentalData;
import environmentalConditions.model.EnviromentalDataFromFileCsv;
import environmentalConditions.servis.EnvironmentalServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class EnvironmentalController {

    @Autowired
    EnvironmentalServis environmentalServis;

    @RequestMapping("/test")
    public String test() {
        return "testPage";
    }

    @RequestMapping("jsoupTest")
    @ResponseBody
    public String jsoupTest() {

        environmentalServis.saveToFile(new EnviromentalData("1", "2", "3"));
        return environmentalServis.getData() + "\n" + environmentalServis.filterData(environmentalServis.getData());
    }

    @RequestMapping("/monitorData")
    @ResponseBody
    @Scheduled(fixedRate = 1000*60*60)
    public String monitorData() {
        environmentalServis.saveToFile(environmentalServis.filterData(environmentalServis.getData()));
        return "check file environmentalData.txt in main catalog";
    }


    @RequestMapping("/showAllData")
    public String showAllData(Model model){
        List<EnviromentalDataFromFileCsv> List = environmentalServis.openFromFile();
        model.addAttribute("dataList" , List);
        return "showAll";
    }

    @RequestMapping("/showSpecific")
    public String showSpecificData(@RequestParam(name = "od")String from, @RequestParam(name = "do") String to, Model model){
        List<EnviromentalDataFromFileCsv> List = environmentalServis.openFromFile();
        List<EnviromentalDataFromFileCsv> filteredList = environmentalServis.getSpecificData(from,to,List);
        model.addAttribute("dataList",filteredList);
        return "showAll";
    }
    @RequestMapping("/saveSpecific")
    public String saveSpecificData(@RequestParam(name = "od")String from, @RequestParam(name = "do") String to, Model model){
        List<EnviromentalDataFromFileCsv> List = environmentalServis.openFromFile();
        List<EnviromentalDataFromFileCsv> filteredList = environmentalServis.getSpecificData(from,to,List);
        environmentalServis.saveSpecificDataToCsvFile(filteredList, from, to);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "/index";
    }


    @RequestMapping("/")
    public String index(){
        return "/index";
    }

    @RequestMapping("/showForm")
    public String showForm(){
        return "/showForm";
    }

    @RequestMapping("/saveForm")
    public String saveForm(){
        return "/saveForm";
    }


}
