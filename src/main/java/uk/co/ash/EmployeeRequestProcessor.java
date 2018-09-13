package uk.co.ash;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequestProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        Employee employee = (Employee) exchange.getIn().getBody();

        // All your fetching logic will go here to invoke another service/s may be to fetch data
        employee.setId(2);

        exchange.getIn().setBody(employee);
    }
}
