package com.board.aop;

import java.util.Collections;
import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {

	@Autowired
	private TransactionManager transactionManager; // 블로그설명이랑 버전차이로 인해 함수가 바뀜

	private static final String EXPRESSION = "execution(* com.board..service.*Impl.*(..))"; //서비스임플에있는 모든 함수

	@Bean
	public TransactionInterceptor transactionAdvice() {
		//어떤 예외든지 예외가 발생하면 던부 롤백한다.
		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));

		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttribute.setName("*");

		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
		attributeSource.setTransactionAttribute(transactionAttribute);

		return new TransactionInterceptor(transactionManager, attributeSource);
	}

	@Bean
	public Advisor transactionAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}

}