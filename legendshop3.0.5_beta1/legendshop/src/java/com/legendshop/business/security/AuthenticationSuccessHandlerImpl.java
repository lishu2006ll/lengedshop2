/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.service.LoginHistoryService;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.model.entity.Basket;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.CookieUtil;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    
	/** The support sso. */
	private boolean supportSSO = false;
	

	/** The login history service. */
	private LoginHistoryService loginHistoryService;
	
	
	/** The basket dao. */
	private BasketDao basketDao;
	
	/* (non-Javadoc)
     * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

		HttpSession session = request.getSession();
    	String username = ((UserDetail)authentication.getPrincipal()).getUsername();
    	
		// Place the last username attempted into HttpSession for views
		session.setAttribute(Constants.USER_NAME, username);
		
    	//remove login error count
    	clearTryLoginCount(request);
		// 处理登录历史
		loginHistoryService.saveLoginHistory(username, request.getRemoteAddr());
		// 增加session登录信息
		
		// 处理在session中的购物车
		Map<String, Basket> basketMap = (Map<String, Basket>) session.getAttribute(
				Constants.BASKET_KEY);
		if (basketMap != null) {
			// 保存进去数据库
			for (Basket basket : basketMap.values()) {
				basketDao.saveToCart(basket.getProdId(), basket.getPic(), username, basket.getShopName(), basket
						.getBasketCount(), basket.getAttribute(), basket.getProdName(), basket.getCash(), basket
						.getCarriage());
			}
			session.setAttribute(Constants.BASKET_KEY, null);
		}

		if (supportSSO) {
			// 增加BBS的登录用户 SSO to club
			CookieUtil.addCookie(response, Constants.CLUB_COOIKES_NAME, username);
		}
    	
    	
    	
    	super.onAuthenticationSuccess(request, response, authentication);
    }
    
    private  void clearTryLoginCount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(AuthenticationFailureHandlerImpl.TRY_LOGIN_COUNT);
    }
    
    public void setSupportSSO(boolean supportSSO) {
		this.supportSSO = supportSSO;
	}


	public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
		this.loginHistoryService = loginHistoryService;
	}

	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
}