package br.com.caelum.livraria.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor {

	@AroundInvoke
	public Object executaLog(InvocationContext contexto) throws Exception {

		long currentTimeMillis = System.currentTimeMillis();

		Object resultado = contexto.proceed();

		long nextTimeMillis = System.currentTimeMillis();

		System.out.println("O método: " + contexto.getMethod().getName() + " demorou : "
				+ (nextTimeMillis - currentTimeMillis) + " para ser executado");

		return resultado;

	}

}
