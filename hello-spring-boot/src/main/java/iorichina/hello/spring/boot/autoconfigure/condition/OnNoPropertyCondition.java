package iorichina.hello.spring.boot.autoconfigure.condition;

import iorichina.hello.spring.boot.annotation.ConditionalOnNoProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class OnNoPropertyCondition extends SpringBootCondition {
    private static final Logger logger = LoggerFactory.getLogger(OnNoPropertyCondition.class);

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(ConditionalOnNoProperty.class.getName());

        Object attributeName = attributes.getFirst("name");
        Object attributeValue = attributes.getFirst("value");

        ConditionOutcome conditionOutcome = null;
        if (attributeName != null) {
            String name = attributeName.toString();
            String value = context.getEnvironment().getProperty(name);
            logger.info("{}={}", name, value);

            if (value != null) {
                if (attributeValue == null || "".equals(attributeValue.toString())) {
                    conditionOutcome = new ConditionOutcome(false, "property [" + name + "] is set");
                } else if (value.equalsIgnoreCase(attributeValue.toString())) {
                    conditionOutcome = new ConditionOutcome(false, "property [" + name + "]'s value [" + attributeValue + "] is set");
                }
            }
        }

        if (null == conditionOutcome) {
            conditionOutcome = new ConditionOutcome(true, "none get properties");
        }
        logger.info("{}={},{}", attributeName, attributeValue, conditionOutcome.isMatch());
        return conditionOutcome;
    }

}
