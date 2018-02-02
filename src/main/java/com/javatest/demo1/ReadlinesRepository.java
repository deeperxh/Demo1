package com.javatest.demo1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//<仓库的领域类型，ID属性>
public interface ReadlinesRepository extends JpaRepository <Book,Long>{

    List<Book> findByReader(String reader);
}
