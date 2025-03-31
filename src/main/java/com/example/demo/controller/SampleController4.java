package com.example.demo.controller;

import jakarta.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;
@Controller
public class SampleController4 {
    // GET/numbers/list : 루트 페이지(합계, 평균)
    // GET/numbers/add : 숫자 추가 화면
    // POST//numbers/add : 숫자를 추가하고 루트 페이지로 이동

    // 정수를 저장하는 ArrayList를 생성
    // 자바에서 참조변수는 항상 부모

   private List<Integer> numbers = new ArrayList<>();
    private List<Double> doubles = new ArrayList<>();
    // numbers에 값을 3개 추가 -> 생성자는 스프링이 사용
    // 우리가 컨트롤러의 객체를 생성한 적이 있나? 없다 -> @Controller의 객체는 스프링이 생성(제어의 역전)
    @PostConstruct
    private void init(){
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
    }
    @PostConstruct
    private void initDouble(){
        doubles.add(10.1);
        doubles.add(20.2);
        doubles.add(30.3);
    }

    @GetMapping("/numbers/list")
    public ModelAndView list(){
        int hap = 0;
        //파이썬 for i in numbers : -> 자바에서는 향상된 for
        for (Integer i: numbers) {
            hap+=i;
        }
        double avg =(double)hap/numbers.size();
        return  new ModelAndView( "numbers/list").addObject("hap",hap)
                .addObject("avg",avg);
    }
    @PostMapping("/numbers/add")
    public ModelAndView add(int num){
        numbers.add(num);
        return new ModelAndView("redirect:/numbers/list");
    }

    @GetMapping("/double/list")
    public ModelAndView doubleList() {
        double doubleHap = 0;
        double doubleAvg = 0;
        for (Double i : doubles) {
            doubleHap = +i;
            doubleAvg = doubleHap / doubles.size();
        }
        return new ModelAndView("double/list").addObject("doubleHap", doubleHap)
                .addObject("doubleAvg", doubleAvg);
    }
    @PostMapping("double/add")
    public ModelAndView doubleAdd(double num){
        doubles.add(num);
        return new ModelAndView("redirect:/double/list");
    }
}
