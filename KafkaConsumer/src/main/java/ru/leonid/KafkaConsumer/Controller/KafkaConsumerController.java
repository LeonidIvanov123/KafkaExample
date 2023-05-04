package ru.leonid.KafkaConsumer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import ru.leonid.KafkaConsumer.Service.ConsumerService;

@Controller
public class KafkaConsumerController {
    @Autowired
    ConsumerService consumerService;
    @GetMapping("/")
    @ResponseBody
    public Flux<String> getRecords(){
        return consumerService.getCurrentMessages().map(record -> record + "<br>");
    }

    @GetMapping("/data")
    public String publishToThumeleaf(Model model){
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
                new ReactiveDataDriverContextVariable(consumerService.getCurrentMessages(), 15);
        model.addAttribute("messagesfromprod", reactiveDataDriverContextVariable);
        return "data";
    }

}
