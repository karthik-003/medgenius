package com.medgenius.security;

import com.medgenius.context.PharmacyContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class PharmacyContextFilter extends OncePerRequestFilter {

    private final PharmacyContext pharmacyContext;

    public PharmacyContextFilter(PharmacyContext pharmacyContext) {
        this.pharmacyContext = pharmacyContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String pharmacyIdHeader = request.getHeader("X-PHARMACY-ID");

        if (pharmacyIdHeader != null) {
            pharmacyContext.setPharmacyId(
                    Long.valueOf(pharmacyIdHeader)
            );
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            pharmacyContext.clear();
        }
    }
}
