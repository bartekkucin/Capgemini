package pl.spring.demo.aop;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.Dao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.dao.impl.AbstractDaoImpl;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookDaoAdvisor 
{

	//@Before("execution(* pl.spring.demo.dao.BookDao.save(..))")
	@Before("execution(* pl.spring.demo.dao..*(..))")
	public void before(JoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Object[] objects = joinPoint.getArgs();

		if (hasAnnotation(method, joinPoint.getTarget(), NullableId.class)) {
			checkNotNullId(objects[0]);
			setIdBook(objects[0], joinPoint.getTarget());
		}
	}
	
	private void setIdBook(Object o, Object daoImpl) {
		if (o instanceof IdAware && daoImpl instanceof AbstractDaoImpl) {
			AbstractDaoImpl bookDao = (AbstractDaoImpl) daoImpl;
			((IdAware) o).setId((Long) (bookDao.getSequence().nextValue(bookDao.findAll())));
		}
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		}
	}

	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes())
					.getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}
