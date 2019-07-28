package com.mod.admin.listener;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.cloud.context.scope.refresh.RefreshScope;

@Slf4j
@Component
public class ApolloListener {

    @Autowired
    private RefreshScope refreshScope;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {

        for (String changedKey : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(changedKey);
            log.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                    change.getChangeType());

            refreshScope.refresh(changedKey);

        }
    }


}
