package pl.spring.demo.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.spring.demo.dao.BookDaoImplTest;
import pl.spring.demo.mapper.BookMapperTest;
import pl.spring.demo.mock.BookServiceImplTest;
import pl.spring.demo.service.BookServiceImplCacheTest;
import pl.spring.demo.service.impl.BookServiceImpl;

@RunWith(Suite.class)
@SuiteClasses({ BookDaoImplTest.class, BookMapperTest.class, BookServiceImplTest.class,
		pl.spring.demo.service.BookServiceImplTest.class, BookServiceImplCacheTest.class })
public class AllTests {

}
