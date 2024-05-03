package com.nicoals.DevNestBE.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicoals.DevNestBE.model.PlanType;
import com.nicoals.DevNestBE.model.Subscription;
import com.nicoals.DevNestBE.model.User;
import com.nicoals.DevNestBE.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @SuppressWarnings("unused")
    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {

        Subscription subscription = new Subscription();

        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) throws Exception {

        Subscription subscription = subscriptionRepository.findByUserId(userId);

        if (!isValid(subscription)) {
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionEndDate(LocalDate.now().plusYears(1));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        
        Subscription subscription = subscriptionRepository.findByUserId(userId);

        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        
        if(planType.equals(PlanType.ANNUALLY)) {
            subscription.setSubscriptionEndDate(LocalDate.now().plusYears(1));
        }else{
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {

        if (subscription.getPlanType().equals(PlanType.FREE))
            return true;

        LocalDate endDate = subscription.getSubscriptionEndDate();
        LocalDate currenDate = LocalDate.now();

        return endDate.isAfter(currenDate) || endDate.isEqual(currenDate);
    }
}