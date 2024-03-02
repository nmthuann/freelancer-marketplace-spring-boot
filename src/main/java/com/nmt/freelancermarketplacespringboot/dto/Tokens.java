package com.nmt.freelancermarketplacespringboot.dto;

public record Tokens(
        String accessToken,
        String refreshToken
) {}