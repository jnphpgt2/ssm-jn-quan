package org.jnan.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionHandlerInterceptor implements HandlerInterceptor {
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	//������

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();// 1����ʼʱ��
		startTimeThreadLocal.set(beginTime);// �̰߳󶨱�����������ֻ�е�ǰ������߳̿ɼ���
											//����session,ʵ������Ĵ�����
		System.out.println("--------in preHandle2----------");

		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();// 2������ʱ��
		long beginTime = startTimeThreadLocal.get();// �õ��̰߳󶨵ľֲ���������ʼʱ�䣩
		long consumeTime = endTime - beginTime;// 3�����ĵ�ʱ��
		if (consumeTime > 500) {// �˴���Ϊ����ʱ�䳬��500���������Ϊ������
			// TODO ��¼����־�ļ�
			System.out.println(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("--------postHandle---------");

	}

}
