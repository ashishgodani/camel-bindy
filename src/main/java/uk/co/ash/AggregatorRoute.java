package uk.co.ash;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggregatorRoute extends RouteBuilder {

    @Autowired
    private EmployeeRequestProcessor employeeRequestProcessor;

    @Override
    public void configure() throws Exception {

        getContext().setTracing(true);

        DataFormat bindy = new BindyFixedLengthDataFormat(Employee.class);

        rest("/employees").description("Employee REST service")
                .consumes("application/json")
                .produces("application/json")

                .post("/{id}").type(Employee.class)
                .to("direct:compose-employee");

        from("direct:compose-employee")
                .unmarshal().json(JsonLibrary.Jackson, Employee.class)
                .process(employeeRequestProcessor)
                .to("direct:export-employee");


        from("direct:export-employee")
                .marshal(bindy)
                .to("file:/Users/ashishgodani/Documents/projects/trialNerror/camel-bindy/");

    }
}
