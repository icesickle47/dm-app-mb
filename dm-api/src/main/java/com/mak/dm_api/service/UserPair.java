package com.mak.dm_api.service;

public record UserPair(String user1, String user2) {
    public static UserPair canonical(String a, String b) {
        if (a == null || b == null) throw new IllegalArgumentException("user ids cannot be null");
        return (a.compareTo(b) <= 0) ? new UserPair(a, b) : new UserPair(b, a);
    }
}
