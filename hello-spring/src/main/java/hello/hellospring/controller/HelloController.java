package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 메인 컨트롤러
@Controller
public class HelloController {

    // @GetMapping -> 어노테이션으로 HTTP GET 요청을 처리하는 메서드를 맵핑하는 어노테이션이다.
    // Mapping("/뒤에 들어갈 링크") -> 링크를 Get 하면 메소드가 실행 된다.
    // localhost:8080/{link} -> 'link'를 GetMapping에서 찾아본 후 없다면 resource/static에서 찾아본다.
    @GetMapping("hello")
    public String hello(Model model) {

        // attributeName은 변수(속성) 이름, attributeValue는 변수 값을 의미한다.
        // GetMapping 뒤 링크를 Get 한 후 model에 addAttribute를 하여 return 값에 적용시킨다. (model(data: Hello!!))
       model.addAttribute("data", "Hello!!!!!!!!!!!!!!!!!!!!!!!!");

        // return으로 반환된 값을 이름으로 갖는 html을 실행한다. resource:templates/+{ViewName}+'.html'
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @requestParam -> Ctrl + P 누르면 Parameter가 뜬다.
    // name, value, required 등을 넣어야 하고 디폴트 값을 알 수 있다.(required = True는 값을 받겠다는 의미다.)
    // http://localhost:8080/hello-mvc?name= 에 매개변수를 넣으면 된다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // @ResponseBody를 하면 view라는 template을 이용 안하고 return 값 그대로 출력된다.(resources/templates/~를 이용 안 한다.)
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    // @ResponseBody은 HttpMessageConverter(JsonConverter, StringConverter)한테 전달해준다. 만약 @ResponseBody가 없다면 viewResolver한테 전달한다.(위의 방식을 말함.)
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 객체 반환 + @ResponseBody 할 경우 JSON 타입 형태로 출력한다.
        return hello;
    }

    static class Hello {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
