package cn.wsg.hello.designpattern.filter;

import java.util.List;

public interface Criteria {

    List<Person> meetCriteria(List<Person> persons);
}
