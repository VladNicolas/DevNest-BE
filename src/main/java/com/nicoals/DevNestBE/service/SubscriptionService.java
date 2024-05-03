package com.nicoals.DevNestBE.service;


import com.nicoals.DevNestBE.model.PlanType;
import com.nicoals.DevNestBE.model.Subscription;
import com.nicoals.DevNestBE.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);
    boolean isValid(Subscription subscription);
}
