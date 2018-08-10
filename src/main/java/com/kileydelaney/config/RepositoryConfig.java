package com.kileydelaney.config;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Address;
import com.kileydelaney.model.Order;
import com.kileydelaney.model.OrderLine;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Account.class);
        config.exposeIdsFor(Address.class);
        config.exposeIdsFor(Order.class);
        config.exposeIdsFor(OrderLine.class);
    }

}
