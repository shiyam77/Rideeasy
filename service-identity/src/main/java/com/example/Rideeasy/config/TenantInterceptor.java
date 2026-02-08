package com.example.Rideeasy.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.lang.Nullable;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader("X-Tenant-ID");

        if (tenantId != null && !tenantId.isEmpty()) {
            TenantContext.setTenantId(tenantId);
            return true;
        } else {
            // Optional: Reject requests without a tenant header
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("X-Tenant-ID header is missing");
            return false;
        }
    }

    @Override
    public void afterCompletion(jakarta.servlet.http.HttpServletRequest request,
                                jakarta.servlet.http.HttpServletResponse response,
                                Object handler,
                                @org.springframework.lang.Nullable Exception ex) throws Exception {
        TenantContext.clear();
    }
}